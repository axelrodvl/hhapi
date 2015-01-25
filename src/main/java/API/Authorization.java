package API;

import Web.Xpath;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;
import java.util.Date;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class Authorization {
    // ChromeDriver settings
    private static final String CHROME_DRIVER_NAME = "chromedriver";
    private static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";

    // OAuth 2.0 application info
    private static final String USER_AGENT = "axelrodvlTest/1.0 (axelrodvl@gmail.com)";
    private static final String AUTH_URI = "https://m.hh.ru/oauth/authorize";
    private static final String TOKEN_LOCATION_URI = "https://m.hh.ru/oauth/token";
    private static final String CLIENT_ID = "GQQ5T3DIOSM9ALCALH3FURUC9ADNFV5IVS8KJHLHIGFLE11QHINM2NGG4SCD78P5";
    private static final String CLIENT_SECRET = "LFEE5IMDBEED2TP2IPH1DN00VTM3JMJ1CB25QDMAHLVPID0MH2CG1TSHQPD3JM6J";

    // Token properties
    private String accessToken = null;
    private Long expiresIn = null;
    private String refreshToken = null;
    private Long tokenStartTimeInSec = null;
    private boolean tokenForceSet = false;

    public Authorization(String clientLogin, String clientPassword) throws Exception {
        // Importing ChromeDriver
        System.setProperty(CHROME_DRIVER_PROPERTY, CHROME_DRIVER_NAME);

        // Retrieving authorization code and access token, based on it
        setAccessToken(getAuthorizationCode(clientLogin, clientPassword));
    }

    public Authorization(String accessToken) throws Exception {
        this.accessToken = accessToken;
        tokenForceSet = true;
    }

    /**
     * Returns OAuth 2.0 authorization code
     * @return authorization code
     */
    private String getAuthorizationCode(String clientLogin, String clientPassword) throws Exception {
        // Creating authorization request URI
        OAuthClientRequest oAuthClientRequest = OAuthClientRequest
                .authorizationLocation(AUTH_URI)
                .setClientId(CLIENT_ID)
                .setResponseType("code")
                .buildQueryMessage();

        // Open web-browser with request URI, providing login/password and getting authorization code
        WebDriver driver = new ChromeDriver();
        driver.get(oAuthClientRequest.getLocationUri());
        driver.findElement(By.xpath(Xpath.LoginWindow.editLogin)).sendKeys(clientLogin);
        driver.findElement(By.xpath(Xpath.LoginWindow.editPassword)).sendKeys(clientPassword);
        driver.findElement(By.xpath(Xpath.LoginWindow.buttonLogin)).click();
        TimeUnit.MILLISECONDS.sleep(1000);

        try {
            if (driver.findElement(By.xpath(Xpath.AccessApproval.textQuestion)).isDisplayed())
                driver.findElement(By.xpath(Xpath.AccessApproval.buttonConfirm)).click();
        } catch (Exception ex) {
            System.out.println("User already confirmed access to application");
        }

        String authorization_code = driver.getTitle().substring(52 ,116);

        driver.quit();

        return authorization_code;
    }

    /**
     * Exchanging authorization code to access token
     * @param authorizationCode May be retrieved by getAuthorizationCode()
     * @throws Exception
     */
    private void setAccessToken(String authorizationCode) throws Exception {
        String authParams =
                "grant_type=authorization_code" +
                "&client_id=" + CLIENT_ID +
                "&client_secret=" + CLIENT_SECRET +
                "&code=" + authorizationCode;

        URL url = new URL(TOKEN_LOCATION_URI);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        byte[] postData = authParams.getBytes(Charset.forName("UTF-8"));
        int postDataLength = postData.length;

        connection.setRequestMethod("POST");
        connection.setRequestProperty("User-Agent", USER_AGENT);
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestProperty("charset", "utf-8");
        connection.setRequestProperty("Content-Length", Integer.toString(postDataLength));
        connection.setUseCaches(false);
        connection.setDoInput(true);
        connection.setDoOutput(true);

        //Send request
        DataOutputStream wr = new DataOutputStream(
                connection.getOutputStream ());
        wr.write(postData);
        wr.flush();
        wr.close();

        //Get Response
        InputStream is = connection.getInputStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        String line;
        StringBuffer response = new StringBuffer();
        while((line = rd.readLine()) != null) {
            response.append(line);
            response.append('\r');
        }
        rd.close();

        JSONParser parser = new JSONParser();
        JSONObject responseJSON = (JSONObject) parser.parse(response.toString());

        accessToken = (String) responseJSON.get("access_token");
        expiresIn = (Long) responseJSON.get("expires_in");
        refreshToken = (String) responseJSON.get("refresh_token");
        String tokenType = (String) responseJSON.get("token_type");

        if(!tokenType.equals("bearer"))
            throw new Exception("Authorization type is changed.");
        tokenStartTimeInSec = new Date().getTime() / 1000;
    }

    /**
     * Mock-method of updating outdated access token.
     * This case isn't reachable at standard settings of OAuth,
     * which provides more than 24-hours access token lifetime,
     * while tests can't lasts more than 2 hours.
     * @param refreshToken
     * @return
     * @throws Exception
     */
    private String updateAccessToken(String refreshToken) throws Exception {
        throw new Exception("Access Token is outdated. Check settings of OAuth server.");
    }

    public String getAccessToken() throws Exception {
        if(!tokenForceSet) {
            Long currentTimeInSec = new Date().getTime() / 1000;
            if (currentTimeInSec >= tokenStartTimeInSec + expiresIn)
                updateAccessToken(refreshToken);
        }

        return accessToken;
    }
}
