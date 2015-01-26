import API.Authorization;
import API.Me;
import API.Response;
import Entity.Employee;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/*
1. Получение информации
        1.1 Положительные тесты
        + 1.1.1 Получение информации о пользователе
        + 1.1.2 Редактирование фамилии и имени (отчество пустое)
        + 1.1.3 Редактирование флага "ишу/не ищу работу" = true
        + 1.1.4 Редактирование флага "ишу/не ищу работу" = false
        + 1.1.5 Передача некорректного строкового значения во флаг "ищу/не ищу работу"
        + 1.1.6 Передача численного значения во флаг "ищу/не ищу работу"
*/

public class TestGetPositive {
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
     * 1.1.1 Получение информации о пользователе (соискателе)
     */
    @Test
    public void getInfo() throws Exception {
        // Test actions
        Response response = Me.get(user);
        Employee employee = new Employee(response);

        // Test assertions
        assertEquals(200, response.getCode());

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