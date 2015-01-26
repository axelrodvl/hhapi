import API.Authorization;
import API.Me;
import API.Response;
import Entity.*;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

/*
2. Редактирование информации
        2.2 Отрицательные тесты
        + 2.2.1 Пустое ФИО
        + 2.2.2 Пустое имя
        + 2.2.3 Пустая фамилия
        + 2.2.4 Только фамилия
        + 2.2.5 Только имя
        + 2.2.6 Только отчество
        + 2.2.7 Только фамилия и отчество
        + 2.2.8 Только имя и отчество
        + 2.2.9 Редактирование имени вместе с флагом "ищу/не ищу работу"
        + 2.2.10 Попытка доступа с некорректным accessToken
*/

public class TestPostNegative {
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
     * 2.2.1 Пустое ФИО
     */
    @Test
    public void setNameEmptyFullName() throws Exception {
        // Test data
        final String expectedLastName = "Селезнев";
        final String expectedFirstName = "Иван";
        final String expectedMiddleName = "Иосифович";

        // Test action
        // Setting correct name
        Me.post(user,
                "last_name=" + expectedLastName +
                        "&first_name=" + expectedFirstName +
                        "&middle_name=" + expectedMiddleName);
        // Trying to set incorrect name
        Response response = Me.post(user,
                "last_name=" +
                "&first_name=" +
                "&middle_name=");

        // Getting name, which shouldn't be updated
        // while incorrect request and be equal
        // to already sent correct name
        Response apiGetResponse = Me.get(user);
        Employee employee = new Employee(apiGetResponse);

        // Test assertions
        assertEquals(400, response.getCode());
        assertNotNull(employee.first_name);
        assertNotNull(employee.middle_name);
        assertNotNull(employee.last_name);
    }

    /**
     * 2.2.2 Пустое имя
     */
    @Test
    public void setNameEmptyFirstName() throws Exception {
        // Test data
        final String expectedLastName = "Селезнев";
        final String expectedFirstName = "Иван";
        final String expectedMiddleName = "Иосифович";

        // Test action
        // Setting correct name
        Me.post(user,
            "last_name=" + expectedLastName +
            "&first_name=" + expectedFirstName +
            "&middle_name=" + expectedMiddleName);
        // Trying to set incorrect name
        Response response = Me.post(user,
                "last_name=" + expectedLastName +
                        "&first_name=" +
                        "&middle_name=" + expectedMiddleName);

        // Getting name, which shouldn't be updated
        // while incorrect request and be equal
        // to already sent correct name
        Response apiGetResponse = Me.get(user);
        Employee employee = new Employee(apiGetResponse);

        // Test assertions
        assertEquals(400, response.getCode());
        assertNotNull(employee.first_name);
    }

    /**
     * 2.2.3 Пустая фамилия
     */
    @Test
    public void setNameEmptyLastName() throws Exception {
        // Test data
        final String expectedLastName = "Селезнев";
        final String expectedFirstName = "Иван";
        final String expectedMiddleName = "Иосифович";

        // Test action
        // Setting correct name
        Me.post(user,
                "last_name=" + expectedLastName +
                "&first_name=" + expectedFirstName +
                "&middle_name=" + expectedMiddleName);
        // Trying to set incorrect name
        Response response = Me.post(user,
                "last_name=" +
                "&first_name=" + expectedFirstName +
                "&middle_name=" + expectedMiddleName);

        // Getting name, which shouldn't be updated
        // while incorrect request and be equal
        // to already sent correct name
        Response apiGetResponse = Me.get(user);
        Employee employee = new Employee(apiGetResponse);

        // Test assertions
        assertEquals(400, response.getCode());
        assertNotNull(employee.last_name);
    }

    /**
     * 2.2.4 Только фамилия
     */
    @Test
    public void setNameOnlyLastName() throws Exception {
        // Test data
        final String expectedLastName = "Селезнев";
        final String expectedFirstName = "Иван";
        final String expectedMiddleName = "Иосифович";

        // Test action
        // Setting correct name
        Me.post(user,
                "last_name=" + expectedLastName +
                "&first_name=" + expectedFirstName +
                "&middle_name=" + expectedMiddleName);
        // Trying to set incorrect name
        Response response = Me.post(user,
                "last_name=" + expectedLastName +
                "&first_name=" +
                "&middle_name=");

        // Getting name, which shouldn't be updated
        // while incorrect request and be equal
        // to already sent correct name
        Response apiGetResponse = Me.get(user);
        Employee employee = new Employee(apiGetResponse);

        // Test assertions
        assertEquals(400, response.getCode());
        assertNotNull(employee.last_name);
        assertNotNull(employee.first_name);
        assertNotNull(employee.middle_name);
    }

    /**
     * 2.2.5 Только имя
     */
    @Test
    public void setNameOnlyFirstName() throws Exception {
        // Test data
        final String expectedLastName = "Селезнев";
        final String expectedFirstName = "Иван";
        final String expectedMiddleName = "Иосифович";

        // Test action
        // Setting correct name
        Me.post(user,
                "last_name=" + expectedLastName +
                "&first_name=" + expectedFirstName +
                "&middle_name=" + expectedMiddleName);
        // Trying to set incorrect name
        Response response = Me.post(user,
                "last_name=" +
                "&first_name=" + expectedFirstName +
                "&middle_name=");

        // Getting name, which shouldn't be updated
        // while incorrect request and be equal
        // to already sent correct name
        Response apiGetResponse = Me.get(user);
        Employee employee = new Employee(apiGetResponse);

        // Test assertions
        assertEquals(400, response.getCode());
        assertNotNull(employee.last_name);
        assertNotNull(employee.first_name);
        assertNotNull(employee.middle_name);
    }

    /**
     * 2.2.6 Только отчество
     */
    @Test
    public void setNameOnlyMiddleName() throws Exception {
        // Test data
        final String expectedLastName = "Селезнев";
        final String expectedFirstName = "Иван";
        final String expectedMiddleName = "Иосифович";

        // Test action
        // Setting correct name
        Me.post(user,
                "last_name=" + expectedLastName +
                "&first_name=" + expectedFirstName +
                "&middle_name=" + expectedMiddleName);
        // Trying to set incorrect name
        Response response = Me.post(user,
                "last_name=" +
                "&first_name=" +
                "&middle_name=" + expectedMiddleName);

        // Getting name, which shouldn't be updated
        // while incorrect request and be equal
        // to already sent correct name
        Response apiGetResponse = Me.get(user);
        Employee employee = new Employee(apiGetResponse);

        // Test assertions
        assertEquals(400, response.getCode());
        assertNotNull(employee.last_name);
        assertNotNull(employee.first_name);
        assertNotNull(employee.middle_name);
    }

    /**
     * 2.2.7 Только фамилия и отчество
     */
    @Test
    public void setNameOnlyLastAndMiddleName() throws Exception {
        // Test data
        final String expectedLastName = "Селезнев";
        final String expectedFirstName = "Иван";
        final String expectedMiddleName = "Иосифович";

        // Test action
        // Setting correct name
        Me.post(user,
                "last_name=" + expectedLastName +
                "&first_name=" + expectedFirstName +
                "&middle_name=" + expectedMiddleName);
        // Trying to set incorrect name
        Response response = Me.post(user,
                "last_name=" + expectedLastName +
                "&first_name=" +
                "&middle_name=" + expectedMiddleName);

        // Getting name, which shouldn't be updated
        // while incorrect request and be equal
        // to already sent correct name
        Response apiGetResponse = Me.get(user);
        Employee employee = new Employee(apiGetResponse);

        // Test assertions
        assertEquals(400, response.getCode());
        assertNotNull(employee.last_name);
        assertNotNull(employee.first_name);
        assertNotNull(employee.middle_name);
    }

    /**
     * 2.2.8 Только имя и отчество
     */
    @Test
    public void setNameOnlyFirstAndMiddleName() throws Exception {
        // Test data
        final String expectedLastName = "Селезнев";
        final String expectedFirstName = "Иван";
        final String expectedMiddleName = "Иосифович";

        // Test action
        // Setting correct name
        Me.post(user,
                "last_name=" + expectedLastName +
                "&first_name=" + expectedFirstName +
                "&middle_name=" + expectedMiddleName);
        // Trying to set incorrect name
        Response response = Me.post(user,
                "last_name=" +
                "&first_name=" + expectedFirstName +
                "&middle_name=" + expectedMiddleName);

        // Getting name, which shouldn't be updated
        // while incorrect request and be equal
        // to already sent correct name
        Response apiGetResponse = Me.get(user);
        Employee employee = new Employee(apiGetResponse);

        // Test assertions
        assertEquals(400, response.getCode());
        assertNotNull(employee.last_name);
        assertNotNull(employee.first_name);
        assertNotNull(employee.middle_name);
    }

    /**
     * 2.2.9 Редактирование имени вместе с флагом "ищу/не ищу работу"
     */
    @Test
    public void setNameAndIsInSearch() throws Exception {
        // Test data
        final String expectedLastName = "Селезнев";
        final String expectedFirstName = "Иван";
        final String expectedMiddleName = "Иосифович";
        final String expectedFlag = "true";

        final String badArgumentLastName = "Иванов";
        final String badArgumentFirstName = "Сергей";
        final String badArgumentMiddleName = "Петрович";
        final String badArgumentFlag = "false";

        ErrorDescription expectedError = new ErrorDescription();
        expectedError.description = "Conflict with is_in_search.";
        expectedError.bad_argument = "first_name";
        expectedError.errors = new HashMap<String, String>();
        expectedError.bad_arguments = new HashMap<String, String>();

        expectedError.errors.put("first_name", "bad_argument");
        expectedError.errors.put("last_name", "bad_argument");
        expectedError.errors.put("middle_name", "bad_argument");
        expectedError.errors.put("is_in_search", "bad_argument");

        expectedError.bad_arguments.put("first_name", "Conflict with is_in_search.");
        expectedError.bad_arguments.put("last_name", "Conflict with is_in_search.");
        expectedError.bad_arguments.put("middle_name", "Conflict with is_in_search.");

        // Test action
        // Setting correct name and flag
        Me.post(user,
                "last_name=" + expectedLastName +
                "&first_name=" + expectedFirstName +
                "&middle_name=" + expectedMiddleName);
        Me.post(user, "is_in_search=" + expectedFlag);

        // Trying to set incorrect name
        Response response = Me.post(user,
                "last_name=" + badArgumentLastName +
                "&first_name=" + badArgumentFirstName +
                "&middle_name=" + badArgumentMiddleName +
                "&is_in_search=" + badArgumentFlag);

        ErrorDescription error = new ErrorDescription(response);

        Response apiGetResponse = Me.get(user);
        Employee employee = new Employee(apiGetResponse);

        // Test assertions
        assertEquals(400, response.getCode());
        assertEquals(expectedError, error);
        assertEquals(employee.first_name, expectedFirstName);
        assertEquals(employee.last_name, expectedLastName);
        assertEquals(employee.middle_name, expectedMiddleName);
        assertTrue(employee.is_in_search);
    }

    /**
     * 2.2.10 Попытка доступа с некорректным accessToken
     */
    @Test
    public void incorrectAccessToken() throws Exception {
        String incorrectAccessToken = "abc";
        Authorization incorrectUser = new Authorization(incorrectAccessToken);

        // Test data
        final String expectedLastName = "Селезнев";
        final String expectedFirstName = "Иван";
        final String expectedMiddleName = "Иосифович";

        final String badArgumentLastName = "Иванов";
        final String badArgumentFirstName = "Сергей";
        final String badArgumentMiddleName = "Петрович";

        ErrorDescription expectedError = new ErrorDescription();
        expectedError.description = "Forbidden";
        expectedError.errors = new HashMap<String, String>();

        expectedError.errors.put("bad_authorization", "oauth");

        // Test action
        // Setting correct name
        Me.post(user,
                "last_name=" + expectedLastName +
                "&first_name=" + expectedFirstName +
                "&middle_name=" + expectedMiddleName);

        // Trying to set incorrect name
        Response response = Me.post(incorrectUser,
                "last_name=" + badArgumentLastName +
                "&first_name=" + badArgumentFirstName +
                "&middle_name=" + badArgumentMiddleName);

        ErrorDescription error = new ErrorDescription(response);

        Response apiGetResponse = Me.get(user);
        Employee employee = new Employee(apiGetResponse);

        // Test assertions
        assertEquals(403, response.getCode());
        assertEquals(expectedError, error);
        assertEquals(employee.first_name, expectedFirstName);
        assertEquals(employee.last_name, expectedLastName);
        assertEquals(employee.middle_name, expectedMiddleName);
    }
}
