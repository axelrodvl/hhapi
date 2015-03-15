import API.Authorization;
import API.HeadHunterAPI;
import API.Method;
import Entity.Employee;
import org.apache.oltu.oauth2.client.response.OAuthResourceResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/*
1. Получение информации
        1.1 Положительные тесты
        + 1.1.1 Получение информации о пользователе (соискателе)
*/

public class TestGetPositive {
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
     * 1.1.1 Получение информации о пользователе (соискателе)
     */
    @Test
    public void getInfo() throws Exception {
        // Test actions
        OAuthResourceResponse response = HeadHunterAPI.get(user, Method.me);
        Employee employee = new Employee(response);

        // Test assertions
        assertEquals(200, response.getResponseCode());

        assertNotNull(employee.id);
        assertNotNull(employee.last_name);
        assertNotNull(employee.first_name);
        assertNotNull(employee.email);
        assertNotNull(employee.resumes_url);
        assertNotNull(employee.negotiations_url);
        assertNotNull(employee.resumes_url);

        assertFalse(employee.is_admin);
        assertFalse(employee.is_employer);

        assertTrue(employee.is_applicant);
        assertTrue(employee.new_resume_views >= 0);
        assertTrue(employee.unread_negotiations >= 0);
    }
}