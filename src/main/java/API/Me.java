package API;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

public class Me {
    private static final String API_URI = "https://api.hh.ru/me";
    public static final String USER_AGENT = "axelrodvlTest/1.0 (axelrodvl@gmail.com)";

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

        //Send request
        DataOutputStream wr = new DataOutputStream(
                connection.getOutputStream ());
        wr.write(postData);
        wr.flush();
        wr.close();

        //Get Response
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
