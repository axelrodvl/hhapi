import API.Authorization;
import API.HeadHunterAPI;
import API.Method;
import Entity.*;
import org.apache.oltu.oauth2.client.response.OAuthResourceResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

/*
2. Редактирование информации
        2.2 Отрицательные тесты
            2.2.1 Некорректное значение имени (знаки кроме "-")
            2.2.2 Некорректное значение фамилии (знаки кроме "-")
            2.2.3 Некорректное значение отчества (знаки кроме "-")
            2.2.4 Некорректное значение имени (числа)
            2.2.5 Некорректное значение фамилии (числа)
            2.2.6 Некорректное значение отчества (числа)
            2.2.7 Пустое ФИО
            2.2.8 Пустое имя
            2.2.9 Пустая фамилия
            2.2.10 Только фамилия
            2.2.11 Только имя
            2.2.12 Только отчество
            2.2.13 Только фамилия и отчество
            2.2.14 Только имя и отчество
            2.2.15 Редактирование имени вместе с флагом "ищу/не ищу работу"
            2.2.16 Попытка доступа с некорректным accessToken
*/

public class TestPostNegative {
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
     * 2.2.1 Некорректное значение имени (знаки кроме "-")
     */
    @Test
    public void setNameFirstNamePunctuationMarks() throws Exception {
        // Test data
        final String expectedLastName = "Селезнев";
        final String expectedFirstName = "Иван";
        final String expectedMiddleName = "Иосифович";

        final String badArgumentLastName = "Иванов";
        final String badArgumentFirstName = "!@#$%^&*()-";
        final String badArgumentMiddleName = "Петрович";

        // Test action
        // Setting correct name
        HeadHunterAPI.post(user, Method.me,
                "last_name=" + expectedLastName +
                        "&first_name=" + expectedFirstName +
                        "&middle_name=" + expectedMiddleName);
        // Trying to set incorrect name
        OAuthResourceResponse response = HeadHunterAPI.post(user, Method.me,
                "last_name=" + badArgumentLastName +
                        "&first_name=" + badArgumentFirstName +
                        "&middle_name=" + badArgumentMiddleName);

        // Getting name, which shouldn't be updated
        // while incorrect request and be equal
        // to already sent correct name
        OAuthResourceResponse apiGetResponse = HeadHunterAPI.get(user, Method.me);
        Employee employee = new Employee(apiGetResponse);

        // Test assertions
        assertEquals(expectedFirstName, employee.first_name);
        assertEquals(expectedLastName, employee.last_name);
        assertEquals(expectedMiddleName, employee.middle_name);
        assertEquals(400, response.getResponseCode());
    }

    /**
     * 2.2.2 Некорректное значение фамилии (знаки кроме "-")
     */
    @Test
    public void setNameLastNamePunctuationMarks() throws Exception {
        // Test data
        final String expectedLastName = "Селезнев";
        final String expectedFirstName = "Иван";
        final String expectedMiddleName = "Иосифович";

        final String badArgumentLastName = "!@#$%^&*()-";
        final String badArgumentFirstName = "Сергей";
        final String badArgumentMiddleName = "Петрович";

        // Test action
        // Setting correct name
        HeadHunterAPI.post(user, Method.me,
                "last_name=" + expectedLastName +
                        "&first_name=" + expectedFirstName +
                        "&middle_name=" + expectedMiddleName);
        // Trying to set incorrect name
        OAuthResourceResponse response = HeadHunterAPI.post(user, Method.me,
                "last_name=" + badArgumentLastName +
                        "&first_name=" + badArgumentFirstName +
                        "&middle_name=" + badArgumentMiddleName);

        // Getting name, which shouldn't be updated
        // while incorrect request and be equal
        // to already sent correct name
        OAuthResourceResponse apiGetResponse = HeadHunterAPI.get(user, Method.me);
        Employee employee = new Employee(apiGetResponse);

        // Test assertions
        assertEquals(expectedLastName, employee.last_name);
        assertEquals(expectedFirstName, employee.first_name);
        assertEquals(expectedMiddleName, employee.middle_name);
        assertEquals(400, response.getResponseCode());
    }

    /**
     * 2.2.3 Некорректное значение отчества (знаки кроме "-")
     */
    @Test
    public void setNameMiddleNamePunctuationMarks() throws Exception {
        // Test data
        final String expectedLastName = "Селезнев";
        final String expectedFirstName = "Иван";
        final String expectedMiddleName = "Иосифович";

        final String badArgumentLastName = "Иванов";
        final String badArgumentFirstName = "Сергей";
        final String badArgumentMiddleName = "!@#$%^&*()-";

        // Test action
        // Setting correct name
        HeadHunterAPI.post(user, Method.me,
                "last_name=" + expectedLastName +
                        "&first_name=" + expectedFirstName +
                        "&middle_name=" + expectedMiddleName);
        // Trying to set incorrect name
        OAuthResourceResponse response = HeadHunterAPI.post(user, Method.me,
                "last_name=" + badArgumentLastName +
                        "&first_name=" + badArgumentFirstName +
                        "&middle_name=" + badArgumentMiddleName);

        // Getting name, which shouldn't be updated
        // while incorrect request and be equal
        // to already sent correct name
        OAuthResourceResponse apiGetResponse = HeadHunterAPI.get(user, Method.me);
        Employee employee = new Employee(apiGetResponse);

        // Test assertions
        assertEquals(expectedMiddleName, employee.middle_name);
        assertEquals(expectedFirstName, employee.first_name);
        assertEquals(expectedLastName, employee.last_name);
        assertEquals(400, response.getResponseCode());
    }

    /**
     * 2.2.4 Некорректное значение имени (числа)
     */
    @Test
    public void setNameFirstNameNumbers() throws Exception {
        // Test data
        final String expectedLastName = "Селезнев";
        final String expectedFirstName = "Иван";
        final String expectedMiddleName = "Иосифович";

        final String badArgumentLastName = "Иванов";
        final String badArgumentFirstName = "123456";
        final String badArgumentMiddleName = "Петрович";

        // Test action
        // Setting correct name
        HeadHunterAPI.post(user, Method.me,
                "last_name=" + expectedLastName +
                        "&first_name=" + expectedFirstName +
                        "&middle_name=" + expectedMiddleName);
        // Trying to set incorrect name
        OAuthResourceResponse response = HeadHunterAPI.post(user, Method.me,
                "last_name=" + badArgumentLastName +
                        "&first_name=" + badArgumentFirstName +
                        "&middle_name=" + badArgumentMiddleName);

        // Getting name, which shouldn't be updated
        // while incorrect request and be equal
        // to already sent correct name
        OAuthResourceResponse apiGetResponse = HeadHunterAPI.get(user, Method.me);
        Employee employee = new Employee(apiGetResponse);

        // Test assertions
        assertEquals(expectedFirstName, employee.first_name);
        assertEquals(expectedLastName, employee.last_name);
        assertEquals(expectedMiddleName, employee.middle_name);
        assertEquals(400, response.getResponseCode());
    }

    /**
     * 2.2.5 Некорректное значение фамилии (числа)
     */
    @Test
    public void setNameLastNameNumbers() throws Exception {
        // Test data
        final String expectedLastName = "Селезнев";
        final String expectedFirstName = "Иван";
        final String expectedMiddleName = "Иосифович";

        final String badArgumentLastName = "123456";
        final String badArgumentFirstName = "Сергей";
        final String badArgumentMiddleName = "Петрович";

        // Test action
        // Setting correct name
        HeadHunterAPI.post(user, Method.me,
                "last_name=" + expectedLastName +
                        "&first_name=" + expectedFirstName +
                        "&middle_name=" + expectedMiddleName);
        // Trying to set incorrect name
        OAuthResourceResponse response = HeadHunterAPI.post(user, Method.me,
                "last_name=" + badArgumentLastName +
                        "&first_name=" + badArgumentFirstName +
                        "&middle_name=" + badArgumentMiddleName);

        // Getting name, which shouldn't be updated
        // while incorrect request and be equal
        // to already sent correct name
        OAuthResourceResponse apiGetResponse = HeadHunterAPI.get(user, Method.me);
        Employee employee = new Employee(apiGetResponse);

        // Test assertions
        assertEquals(expectedLastName, employee.last_name);
        assertEquals(expectedFirstName, employee.first_name);
        assertEquals(expectedMiddleName, employee.middle_name);
        assertEquals(400, response.getResponseCode());
    }

    /**
     * 2.2.6 Некорректное значение отчества (числа)
     */
    @Test
    public void setNameMiddleNameNumbers() throws Exception {
        // Test data
        final String expectedLastName = "Селезнев";
        final String expectedFirstName = "Иван";
        final String expectedMiddleName = "Иосифович";

        final String badArgumentLastName = "Иванов";
        final String badArgumentFirstName = "Сергей";
        final String badArgumentMiddleName = "123456";

        // Test action
        // Setting correct name
        HeadHunterAPI.post(user, Method.me,
                "last_name=" + expectedLastName +
                        "&first_name=" + expectedFirstName +
                        "&middle_name=" + expectedMiddleName);
        // Trying to set incorrect name
        OAuthResourceResponse response = HeadHunterAPI.post(user, Method.me,
                "last_name=" + badArgumentLastName +
                        "&first_name=" + badArgumentFirstName +
                        "&middle_name=" + badArgumentMiddleName);

        // Getting name, which shouldn't be updated
        // while incorrect request and be equal
        // to already sent correct name
        OAuthResourceResponse apiGetResponse = HeadHunterAPI.get(user, Method.me);
        Employee employee = new Employee(apiGetResponse);

        // Test assertions
        assertEquals(expectedMiddleName, employee.middle_name);
        assertEquals(expectedFirstName, employee.first_name);
        assertEquals(expectedLastName, employee.last_name);
        assertEquals(400, response.getResponseCode());
    }

    /**
     * 2.2.7 Пустое ФИО
     */
    @Test
    public void setNameEmptyFullName() throws Exception {
        // Test data
        final String expectedLastName = "Селезнев";
        final String expectedFirstName = "Иван";
        final String expectedMiddleName = "Иосифович";

        // Test action
        // Setting correct name
        HeadHunterAPI.post(user, Method.me,
                "last_name=" + expectedLastName +
                        "&first_name=" + expectedFirstName +
                        "&middle_name=" + expectedMiddleName);
        // Trying to set incorrect name
        OAuthResourceResponse response = HeadHunterAPI.post(user, Method.me,
                "last_name=" +
                        "&first_name=" +
                        "&middle_name=");

        // Getting name, which shouldn't be updated
        // while incorrect request and be equal
        // to already sent correct name
        OAuthResourceResponse apiGetResponse = HeadHunterAPI.get(user, Method.me);
        Employee employee = new Employee(apiGetResponse);

        // Test assertions
        assertNotNull("Employee first name is null", employee.first_name);
        assertNotNull("Employee middle name is null", employee.middle_name);
        assertNotNull("Employee last name is null", employee.last_name);
        assertEquals(400, response.getResponseCode());
    }

    /**
     * 2.2.8 Пустое имя
     */
    @Test
    public void setNameEmptyFirstName() throws Exception {
        // Test data
        final String expectedLastName = "Селезнев";
        final String expectedFirstName = "Иван";
        final String expectedMiddleName = "Иосифович";

        // Test action
        // Setting correct name
        HeadHunterAPI.post(user, Method.me,
                "last_name=" + expectedLastName +
                        "&first_name=" + expectedFirstName +
                        "&middle_name=" + expectedMiddleName);
        // Trying to set incorrect name
        OAuthResourceResponse response = HeadHunterAPI.post(user, Method.me,
                "last_name=" + expectedLastName +
                        "&first_name=" +
                        "&middle_name=" + expectedMiddleName);

        // Getting name, which shouldn't be updated
        // while incorrect request and be equal
        // to already sent correct name
        OAuthResourceResponse apiGetResponse = HeadHunterAPI.get(user, Method.me);
        Employee employee = new Employee(apiGetResponse);

        // Test assertions
        assertNotNull("Employee first name is null", employee.first_name);
        assertEquals(400, response.getResponseCode());
    }

    /**
     * 2.2.9 Пустая фамилия
     */
    @Test
    public void setNameEmptyLastName() throws Exception {
        // Test data
        final String expectedLastName = "Селезнев";
        final String expectedFirstName = "Иван";
        final String expectedMiddleName = "Иосифович";

        // Test action
        // Setting correct name
        HeadHunterAPI.post(user, Method.me,
                "last_name=" + expectedLastName +
                        "&first_name=" + expectedFirstName +
                        "&middle_name=" + expectedMiddleName);
        // Trying to set incorrect name
        OAuthResourceResponse response = HeadHunterAPI.post(user, Method.me,
                "last_name=" +
                        "&first_name=" + expectedFirstName +
                        "&middle_name=" + expectedMiddleName);

        // Getting name, which shouldn't be updated
        // while incorrect request and be equal
        // to already sent correct name
        OAuthResourceResponse apiGetResponse = HeadHunterAPI.get(user, Method.me);
        Employee employee = new Employee(apiGetResponse);

        // Test assertions
        assertNotNull("Employee last name is null", employee.last_name);
        assertEquals(400, response.getResponseCode());
    }

    /**
     * 2.2.10 Только фамилия
     */
    @Test
    public void setNameOnlyLastName() throws Exception {
        // Test data
        final String expectedLastName = "Селезнев";
        final String expectedFirstName = "Иван";
        final String expectedMiddleName = "Иосифович";

        // Test action
        // Setting correct name
        HeadHunterAPI.post(user, Method.me,
                "last_name=" + expectedLastName +
                        "&first_name=" + expectedFirstName +
                        "&middle_name=" + expectedMiddleName);
        // Trying to set incorrect name
        OAuthResourceResponse response = HeadHunterAPI.post(user, Method.me,
                "last_name=" + expectedLastName +
                        "&first_name=" +
                        "&middle_name=");

        // Getting name, which shouldn't be updated
        // while incorrect request and be equal
        // to already sent correct name
        OAuthResourceResponse apiGetResponse = HeadHunterAPI.get(user, Method.me);
        Employee employee = new Employee(apiGetResponse);

        // Test assertions
        assertNotNull("Employee last name is null", employee.last_name);
        assertNotNull("Employee first name is null", employee.first_name);
        assertNotNull("Employee middle name is null", employee.middle_name);
        assertEquals(400, response.getResponseCode());
    }

    /**
     * 2.2.11 Только имя
     */
    @Test
    public void setNameOnlyFirstName() throws Exception {
        // Test data
        final String expectedLastName = "Селезнев";
        final String expectedFirstName = "Иван";
        final String expectedMiddleName = "Иосифович";

        // Test action
        // Setting correct name
        HeadHunterAPI.post(user, Method.me,
                "last_name=" + expectedLastName +
                        "&first_name=" + expectedFirstName +
                        "&middle_name=" + expectedMiddleName);
        // Trying to set incorrect name
        OAuthResourceResponse response = HeadHunterAPI.post(user, Method.me,
                "last_name=" +
                        "&first_name=" + expectedFirstName +
                        "&middle_name=");

        // Getting name, which shouldn't be updated
        // while incorrect request and be equal
        // to already sent correct name
        OAuthResourceResponse apiGetResponse = HeadHunterAPI.get(user, Method.me);
        Employee employee = new Employee(apiGetResponse);

        // Test assertions
        assertNotNull("Employee last name is null", employee.last_name);
        assertNotNull("Employee first name is null", employee.first_name);
        assertNotNull("Employee middle name is null", employee.middle_name);
        assertEquals(400, response.getResponseCode());
    }

    /**
     * 2.2.12 Только отчество
     */
    @Test
    public void setNameOnlyMiddleName() throws Exception {
        // Test data
        final String expectedLastName = "Селезнев";
        final String expectedFirstName = "Иван";
        final String expectedMiddleName = "Иосифович";

        // Test action
        // Setting correct name
        HeadHunterAPI.post(user, Method.me,
                "last_name=" + expectedLastName +
                        "&first_name=" + expectedFirstName +
                        "&middle_name=" + expectedMiddleName);
        // Trying to set incorrect name
        OAuthResourceResponse response = HeadHunterAPI.post(user, Method.me,
                "last_name=" +
                        "&first_name=" +
                        "&middle_name=" + expectedMiddleName);

        // Getting name, which shouldn't be updated
        // while incorrect request and be equal
        // to already sent correct name
        OAuthResourceResponse apiGetResponse = HeadHunterAPI.get(user, Method.me);
        Employee employee = new Employee(apiGetResponse);

        // Test assertions
        assertNotNull("Employee last name is null", employee.last_name);
        assertNotNull("Employee first name is null", employee.first_name);
        assertNotNull("Employee middle name is null", employee.middle_name);
        assertEquals(400, response.getResponseCode());
    }

    /**
     * 2.2.13 Только фамилия и отчество
     */
    @Test
    public void setNameOnlyLastAndMiddleName() throws Exception {
        // Test data
        final String expectedLastName = "Селезнев";
        final String expectedFirstName = "Иван";
        final String expectedMiddleName = "Иосифович";

        // Test action
        // Setting correct name
        HeadHunterAPI.post(user, Method.me,
                "last_name=" + expectedLastName +
                        "&first_name=" + expectedFirstName +
                        "&middle_name=" + expectedMiddleName);
        // Trying to set incorrect name
        OAuthResourceResponse response = HeadHunterAPI.post(user, Method.me,
                "last_name=" + expectedLastName +
                        "&first_name=" +
                        "&middle_name=" + expectedMiddleName);

        // Getting name, which shouldn't be updated
        // while incorrect request and be equal
        // to already sent correct name
        OAuthResourceResponse apiGetResponse = HeadHunterAPI.get(user, Method.me);
        Employee employee = new Employee(apiGetResponse);

        // Test assertions
        assertNotNull("Employee last name is null", employee.last_name);
        assertNotNull("Employee first name is null", employee.first_name);
        assertNotNull("Employee middle name is null", employee.middle_name);
        assertEquals(400, response.getResponseCode());
    }

    /**
     * 2.2.14 Только имя и отчество
     */
    @Test
    public void setNameOnlyFirstAndMiddleName() throws Exception {
        // Test data
        final String expectedLastName = "Селезнев";
        final String expectedFirstName = "Иван";
        final String expectedMiddleName = "Иосифович";

        // Test action
        // Setting correct name
        HeadHunterAPI.post(user, Method.me,
                "last_name=" + expectedLastName +
                        "&first_name=" + expectedFirstName +
                        "&middle_name=" + expectedMiddleName);
        // Trying to set incorrect name
        OAuthResourceResponse response = HeadHunterAPI.post(user, Method.me,
                "last_name=" +
                        "&first_name=" + expectedFirstName +
                        "&middle_name=" + expectedMiddleName);

        // Getting name, which shouldn't be updated
        // while incorrect request and be equal
        // to already sent correct name
        OAuthResourceResponse apiGetResponse = HeadHunterAPI.get(user, Method.me);
        Employee employee = new Employee(apiGetResponse);

        // Test assertions
        assertNotNull("Employee last name is null", employee.last_name);
        assertNotNull("Employee first name is null", employee.first_name);
        assertNotNull("Employee middle name is null", employee.middle_name);
        assertEquals(400, response.getResponseCode());
    }

    /**
     * 2.2.15 Редактирование имени вместе с флагом "ищу/не ищу работу"
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
        HeadHunterAPI.post(user, Method.me,
                "last_name=" + expectedLastName +
                        "&first_name=" + expectedFirstName +
                        "&middle_name=" + expectedMiddleName);
        HeadHunterAPI.post(user, Method.me,
                "is_in_search=" + expectedFlag);

        // Trying to set incorrect name
        OAuthResourceResponse response = HeadHunterAPI.post(user, Method.me,
                "last_name=" + badArgumentLastName +
                        "&first_name=" + badArgumentFirstName +
                        "&middle_name=" + badArgumentMiddleName +
                        "&is_in_search=" + badArgumentFlag);

        ErrorDescription error = new ErrorDescription(response);

        OAuthResourceResponse apiGetResponse = HeadHunterAPI.get(user, Method.me);
        Employee employee = new Employee(apiGetResponse);

        // Test assertions
        assertEquals(expectedError, error);
        assertEquals(employee.first_name, expectedFirstName);
        assertEquals(employee.last_name, expectedLastName);
        assertEquals(employee.middle_name, expectedMiddleName);
        assertTrue(employee.is_in_search);
        assertEquals(400, response.getResponseCode());
    }

    /**
     * 2.2.16 Попытка доступа с некорректным accessToken
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

        String expectedError = "java.io.IOException: Server returned HTTP response code: 403 for URL: https://api.hh.ru/me?access_token=abc";

        // Test action
        // Setting correct name
        HeadHunterAPI.post(user, Method.me,
                "last_name=" + expectedLastName +
                        "&first_name=" + expectedFirstName +
                        "&middle_name=" + expectedMiddleName);

        // Trying to set incorrect name
        try {
            OAuthResourceResponse response = HeadHunterAPI.post(incorrectUser, Method.me,
                    "last_name=" + badArgumentLastName +
                            "&first_name=" + badArgumentFirstName +
                            "&middle_name=" + badArgumentMiddleName);
        } catch (Exception ex) {
            // Test assertions 1
            assertEquals(expectedError, ex.getMessage());
        }

        OAuthResourceResponse apiGetResponse = HeadHunterAPI.get(user, Method.me);
        Employee employee = new Employee(apiGetResponse);

        // Test assertions 2
        assertEquals(employee.first_name, expectedFirstName);
        assertEquals(employee.last_name, expectedLastName);
        assertEquals(employee.middle_name, expectedMiddleName);
    }
}
