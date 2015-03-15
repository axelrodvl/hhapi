import API.Authorization;
import API.Me;
import API.Response;
import Entity.Employee;
import org.apache.oltu.oauth2.client.response.OAuthResourceResponse;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/*
2. Редактирование информации
        2.1 Положительные тесты
            2.1.1 Редактирование фамилии, имени и отчества
            2.1.2 Редактирование фамилии, имени и отчества (значения со знаком "-")
            2.1.3 Редактирование фамилии и имени (отчество пустое)
            2.1.4 Редактирование флага "ишу/не ищу работу" = true
            2.1.5 Редактирование флага "ишу/не ищу работу" = false
            2.1.6 Передача некорректного строкового значения во флаг "ищу/не ищу работу"
            2.1.7 Передача численного значения во флаг "ищу/не ищу работу"
*/

public class TestPostPositive {
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
     * 2.1.1 Редактирование фамилии, имени и отчества
     */
    @Test
    public void setNameFull() throws Exception {
        // Test data
        final String expectedLastName = "Селезнев";
        final String expectedFirstName = "Иван";
        final String expectedMiddleName = "Иосифович";

        // Test actions
        Me.post(user,
            "last_name=" + expectedLastName +
            "&first_name=" + expectedFirstName +
            "&middle_name=" + expectedMiddleName);
        OAuthResourceResponse response = Me.get(user);
        Employee employee = new Employee(response);

        // Test assertions
        assertEquals(200, response.getResponseCode());
        assertEquals(employee.first_name, expectedFirstName);
        assertEquals(employee.last_name, expectedLastName);
        assertEquals(employee.middle_name, expectedMiddleName);
    }

    /**
     * 2.1.2 Редактирование фамилии, имени и отчества (значения со знаком "-")
     */
    @Test
    public void setNameFullPunctuationMark() throws Exception {
        // Test data
        final String expectedLastName = "Селезнев-Абдулаев";
        final String expectedFirstName = "Ахмат-хаджи";
        final String expectedMiddleName = "Ахмат-хаджи";

        // Test actions
        Me.post(user,
                "last_name=" + expectedLastName +
                        "&first_name=" + expectedFirstName +
                        "&middle_name=" + expectedMiddleName);
        OAuthResourceResponse response = Me.get(user);
        Employee employee = new Employee(response);

        // Test assertions
        assertEquals(200, response.getResponseCode());
        assertEquals(employee.first_name, expectedFirstName);
        assertEquals(employee.last_name, expectedLastName);
        assertEquals(employee.middle_name, expectedMiddleName);
    }

    /**
     * 2.1.3 Редактирование фамилии и имени (отчество пустое)
     */
    @Test
    public void setNameFirstAndLast() throws Exception {
        // Test data
        final String expectedLastName = "Селезнев";
        final String expectedFirstName = "Иван";

        // Test actions
        Me.post(user,
            "last_name=" + expectedLastName +
            "&first_name=" + expectedFirstName +
            "&middle_name=");
        OAuthResourceResponse response = Me.get(user);
        Employee employee = new Employee(response);

        // Test assertions
        assertEquals(200, response.getResponseCode());
        assertNull(employee.middle_name);
    }

    /**
     * 2.1.4 Редактирование флага "ишу/не ищу работу" = true
     */
    @Test
    public void setIsInSearchTrue() throws Exception {
        // Test data
        final String expectedFlag = "true";

        // Test actions
        Me.post(user, "is_in_search=" + expectedFlag);
        OAuthResourceResponse response = Me.get(user);
        Employee employee = new Employee(response);

        // Test assertions
        assertEquals(200, response.getResponseCode());
        assertTrue(employee.is_in_search);
    }

    /**
     * 2.1.5 Редактирование флага "ишу/не ищу работу" = false
     */
    @Test
    public void setIsInSearchFalse() throws Exception {
        // Test data
        final String expectedFlag = "false";

        // Test actions
        Me.post(user, "is_in_search=" + expectedFlag);
        OAuthResourceResponse response = Me.get(user);
        Employee employee = new Employee(response);

        // Test assertions
        assertEquals(200, response.getResponseCode());
        assertFalse(employee.is_in_search);
    }

    /**
     * 2.1.6 Передача некорректного строкового значения во флаг "ищу/не ищу работу"
     */
    @Test
    public void setIsInSearchIncorrectString() throws Exception {
        // Test data
        final String expectedFlag = "asdfasdf";

        // Test actions
        Me.post(user, "is_in_search=" + expectedFlag);
        OAuthResourceResponse response = Me.get(user);
        Employee employee = new Employee(response);

        // Test assertions
        assertEquals(200, response.getResponseCode());
        assertFalse(employee.is_in_search);
    }

    /**
     * 2.1.7 Передача численного значения во флаг "ищу/не ищу работу"
     */
    @Test
    public void setIsInSearchIncorrectStringNumbers() throws Exception {
        // Test data
        final String expectedFlag = "0";

        // Test actions
        Me.post(user, "is_in_search=" + expectedFlag);
        OAuthResourceResponse response = Me.get(user);
        Employee employee = new Employee(response);

        // Test assertions
        assertEquals(200, response.getResponseCode());
        assertFalse(employee.is_in_search);
    }
}
