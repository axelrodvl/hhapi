package API;

import Web.Xpath;
import Selenium.Tools;
import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthJSONAccessTokenResponse;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;
import java.util.Date;

public class Authorization {
    // ChromeDriver settings
    private static final String CHROME_DRIVER_NAME = "chromedriver.exe";
    private static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";

    // OAuth 2.0 application info
    private static final String USER_AGENT = "axelrodvlTest/1.0 (axelrodvl@gmail.com)";
    private static final String AUTH_URI = "https://m.hh.ru/oauth/authorize";
    private static final String TOKEN_LOCATION_URI = "https://m.hh.ru/oauth/token";
    private static final String CLIENT_ID = "GQQ5T3DIOSM9ALCALH3FURUC9ADNFV5IVS8KJHLHIGFLE11QHINM2NGG4SCD78P5";
    private static final String CLIENT_SECRET = "LFEE5IMDBEED2TP2IPH1DN00VTM3JMJ1CB25QDMAHLVPID0MH2CG1TSHQPD3JM6J";
    private static final String REDIRECT_URI = "https://fasdfasdfasdfasddfasdfasdfasdfasdfasd";

    // Token properties
    private String accessToken = null;
    private Long expiresIn = null;
    private String refreshToken = null;
    private Long tokenStartTimeInSec = null;
    private boolean tokenForceSet = false;

    /**
     * Retrieving authorization based on client's login/password.
     * @param clientLogin
     * @param clientPassword
     * @throws Exception
     */
    public Authorization(String clientLogin, String clientPassword) throws Exception {
        // Importing ChromeDriver
        System.setProperty(CHROME_DRIVER_PROPERTY, CHROME_DRIVER_NAME);

        // Retrieving authorization code and access token, based on it
        setAccessToken(getAuthorizationCode(clientLogin, clientPassword));
    }

    /**
     * Force set of already taken access token
     * @param accessToken
     * @throws Exception
     */
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
        Tools.getLoadedElement(driver, 5000, Xpath.LoginWindow.editLogin).sendKeys(clientLogin);
        Tools.getLoadedElement(driver, 5000, Xpath.LoginWindow.editPassword).sendKeys(clientPassword);
        Tools.getLoadedElement(driver, 5000, Xpath.LoginWindow.buttonLogin).click();
        // Providing access to application, if user not authorized app before
        try {
            if (Tools.getLoadedElement(driver, 1000, Xpath.AccessApproval.textQuestion).isDisplayed())
                Tools.getLoadedElement(driver, 1000, Xpath.AccessApproval.buttonConfirm).click();
        } catch (Exception ex) {
            System.out.println("User already confirmed access to application");
        }

        // Taking authorization code and closing browser
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
        // Creating authorization HTTP client
        OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());

        // Creating authorization request URI
        OAuthClientRequest request = OAuthClientRequest
                .tokenLocation(TOKEN_LOCATION_URI)
                .setClientId(CLIENT_ID)
                .setClientSecret(CLIENT_SECRET)
                .setRedirectURI(REDIRECT_URI)
                .setGrantType(GrantType.AUTHORIZATION_CODE)
                .setCode(authorizationCode)
                .buildBodyMessage();
        OAuthJSONAccessTokenResponse response = oAuthClient.accessToken(request);
        accessToken = response.getAccessToken();
        expiresIn = response.getExpiresIn();
        refreshToken = response.getRefreshToken();
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

    /**
     * Access token getter
     * @return Access Token
     * @throws Exception
     */
    public String getAccessToken() throws Exception {
        if(!tokenForceSet) {
            Long currentTimeInSec = new Date().getTime() / 1000;
            if (currentTimeInSec >= tokenStartTimeInSec + expiresIn)
                updateAccessToken(refreshToken);
        }

        return accessToken;
    }
}
