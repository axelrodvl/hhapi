package API;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthBearerClientRequest;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthResourceResponse;
import org.apache.oltu.oauth2.common.OAuth;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * Class for making requests at https://api.hh.ru/me
 */
public class Me {
    private static final String API_URI = "https://api.hh.ru/me";
    public static final String USER_AGENT = "axelrodvlTest/1.0 (axelrodvl@gmail.com)";

    /**
     * Making post request with @postParams
     * @param auth Authorization, required to make requests
     * @param postParams Request parameters
     * @return Response object (response body and code)
     * @throws Exception
     */
    public static OAuthResourceResponse post(Authorization auth, String postParams) throws Exception {
        // Creating authorization HTTP client
        OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());

        // Creating token-signed request
        OAuthClientRequest signedPostRequest = new OAuthBearerClientRequest(API_URI)
                .setAccessToken(auth.getAccessToken())
                .buildQueryMessage();
        signedPostRequest.setHeader("Authorization", "Bearer " + auth.getAccessToken());
        signedPostRequest.setHeader("Content-Type", "application/x-www-form-urlencoded");
        signedPostRequest.setBody(postParams);

        return oAuthClient.resource(signedPostRequest, OAuth.HttpMethod.POST, OAuthResourceResponse.class);
    }

    /**
    * Making get request without params
        * @param auth Authorization, required to make requests
        * @return Response object (response body and code)
        * @throws Exception
    */
    public static OAuthResourceResponse get(Authorization auth) throws Exception {
        // Creating authorization HTTP client
        OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());

        // Creating token-signed request
        OAuthClientRequest signedGetRequest = new OAuthBearerClientRequest(API_URI)
                .setAccessToken(auth.getAccessToken())
                .buildQueryMessage();
        signedGetRequest.setHeader("Authorization", "Bearer " + auth.getAccessToken());
        signedGetRequest.setHeader("Content-Type", "application/x-www-form-urlencoded");

        return oAuthClient.resource(signedGetRequest, OAuth.HttpMethod.GET, OAuthResourceResponse.class);
    }
}
