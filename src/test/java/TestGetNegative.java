import API.Authorization;
import API.Me;
import API.Response;
import Entity.Employee;
import Entity.ErrorDescription;
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
        String testAccessToken = "HE408GUDS84KVRG0KMRBT9FPO3QHBH3VOFSRCCT41MEJRRE446KV23EMNCN4JSFC";
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
        ErrorDescription expectedError = new ErrorDescription();
        expectedError.description = "Forbidden";
        expectedError.errors = new HashMap<String, String>();
        expectedError.errors.put("bad_authorization", "oauth");

        // Test action
        // Trying to get access with incorrect token
        Response response = Me.get(incorrectUser);
        ErrorDescription error = new ErrorDescription(response);

        // Trying to get access with correct token
        Response apiGetResponse = Me.get(user);
        Employee employee = new Employee(apiGetResponse);

        // Test assertions
        assertEquals(403, response.getCode());
        assertEquals(expectedError, error);
    }
}
