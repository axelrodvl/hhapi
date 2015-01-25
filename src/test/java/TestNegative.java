import API.Authorization;
import API.Me;
import API.Response;
import Entity.*;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/*
3. Редактирование информации
        3.2 Отрицательные тесты
        + 3.2.1 Пустое ФИО
        + 3.2.2 Пустое имя
        + 3.2.3 Пустая фамилия
        + 3.2.4 Только фамилия
        + 3.2.5 Только имя
        + 3.2.6 Только отчество
        + 3.2.7 Только фамилия и отчество
        + 3.2.8 Только имя и отчество
        3.2.9 Редактирование имени вместе с флагом "ищу/не ищу работу"
*/

public class TestNegative {
    static Authorization user;

    @BeforeClass
    public static void authentication() throws Exception {
        user = new Authorization("axelrodvl.test@gmail.com", "80007338495");
    }

    /**
     * 3.2.1 Пустое ФИО
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
     * 3.2.2 Пустое имя
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
     * 3.2.3 Пустая фамилия
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
     * 3.2.4 Только фамилия
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
     * 3.2.5 Только имя
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
     * 3.2.6 Только отчество
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
     * 3.2.7 Только фамилия и отчество
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
     * 3.2.8 Только имя и отчество
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
     * 3.2.9 Редактирование имени вместе с флагом "ищу/не ищу работу"
     */
    @Test
    public void setNameAndIsInSearch() throws Exception {
        // Test data
        final String expectedLastName = "Селезнев";
        final String expectedFirstName = "Иван";
        final String expectedMiddleName = "Иосифович";
        final String expectedFlag = "true";

        // Test action
        Response response = Me.post(user,
                "last_name=" + expectedLastName +
                "&first_name=" + expectedFirstName +
                "&middle_name=" + expectedMiddleName +
                "&is_in_search=" + expectedFlag);

        ErrorDescription error = new ErrorDescription(response);

        /*
        Response apiGetResponse = Me.get(user);
        Employee employee = new Employee(apiGetResponse);
        */

        // Test assertions
        assertEquals(400, response.getCode());
        assertEquals("Conflict with is_in_search.", error.description);
        assertEquals("first_name", error.bad_argument);
        //assertNotNull(emp.first_name);
    }
}
