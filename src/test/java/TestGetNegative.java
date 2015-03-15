import API.Authorization;
import API.Me;
import Entity.Employee;
import Entity.ErrorDescription;
import org.apache.oltu.oauth2.client.response.OAuthResourceResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

/*
1. Получение информации
        1.2 Отрицательные тесты
        + 1.2.1 Попытка доступа с некорректным accessToken
*/

public class TestGetNegative {
    static Authorization user;

    @BeforeClass
    public static void authentication() throws Exception {
        // Using already taken access token
        String testAccessToken = "RQ607OVQJDSDHOSUG0GN3JM2MA9J7LA03C5FKUVTRDIK8SRL477TR8DQU3LA59JF";
        user = new Authorization(testAccessToken);

        // Creating new access token and retrieving authorization at new user
        //user = new Authorization("axelrodvl.test@gmail.com", "789456123");
    }

    /**
     * 1.2.1 Попытка доступа с некорректным accessToken
     */
    @Test
    public void incorrectAccessToken() throws Exception {
        String incorrectAccessToken = "abc";
        Authorization incorrectUser = new Authorization(incorrectAccessToken);

        // Test data
        String expectedError = "java.io.IOException: Server returned HTTP response code: 403 for URL: https://api.hh.ru/me?access_token=abc";

        // Trying to get access with correct token
        OAuthResourceResponse apiGetResponse = Me.get(user);
        Employee employee = new Employee(apiGetResponse);

        // Test action
        // Trying to get access with incorrect token
        try {
            OAuthResourceResponse response = Me.get(incorrectUser);
        } catch (Exception ex) {
            // Test assertions
            assertEquals(expectedError, ex.getMessage());
        }


    }
}
