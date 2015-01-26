package API;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Class for making requests at https://api.hh.ru/me
 */
public class Me {
    private static final String API_URI = "https://api.hh.ru/me";
    public static final String USER_AGENT = "axelrodvlTest/1.0 (axelrodvl@gmail.com)";

    /**
     * Making get request without params
     * @param auth Authorization, required to make requests
     * @return Response object (response body and code)
     * @throws Exception
     */
    public static Response get(Authorization auth) throws Exception {
        URL url = new URL(API_URI);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", USER_AGENT);
        connection.setRequestProperty("Authorization", "Bearer " + auth.getAccessToken());

        int responseCode = connection.getResponseCode();
        InputStream is;

        if(responseCode < 400)
            is = connection.getInputStream();
        else
            is = connection.getErrorStream();

        BufferedReader in = new BufferedReader(new InputStreamReader(is));
        String inputLine;
        StringBuffer responseBody = new StringBuffer();

        while ((inputLine = in.readLine()) != null)
            responseBody.append(inputLine);
        in.close();

        return new Response(responseCode, responseBody.toString());
    }

    /**
     * Making post request with @postParams
     * @param auth Authorization, required to make requests
     * @param postParams Request parameters
     * @return Response object (response body and code)
     * @throws Exception
     */
    public static Response post(Authorization auth, String postParams) throws Exception {
        URL url = new URL(API_URI);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        byte[] postData = postParams.getBytes(Charset.forName("UTF-8"));
        int postDataLength = postData.length;

        connection.setRequestMethod("POST");
        connection.setRequestProperty("User-Agent", USER_AGENT);
        connection.setRequestProperty("Authorization", "Bearer " + auth.getAccessToken());
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestProperty("charset", "utf-8");
        connection.setRequestProperty("Content-Length", Integer.toString(postDataLength));
        connection.setUseCaches(false);
        connection.setDoInput(true);
        connection.setDoOutput(true);

        DataOutputStream wr = new DataOutputStream(
                connection.getOutputStream ());
        wr.write(postData);
        wr.flush();
        wr.close();

        int responseCode = connection.getResponseCode();
        InputStream is;

        if(connection.getResponseCode() < 400)
            is = connection.getInputStream();
        else
            is = connection.getErrorStream();

        BufferedReader in = new BufferedReader(new InputStreamReader(is));
        String inputLine;
        StringBuffer responseBody = new StringBuffer();
        while ((inputLine = in.readLine()) != null)
            responseBody.append(inputLine);
        in.close();

        return new Response(responseCode, responseBody.toString());
    }
}
