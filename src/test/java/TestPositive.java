import API.Authorization;
import API.Me;
import API.Response;
import Entity.Employee;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/*
3. Редактирование информации
        3.1 Положительные тесты
        + 3.1.1 Редактирование фамилии, имени и отчества
        + 3.1.2 Редактирование фамилии и имени (отчество пустое)
        + 3.1.3 Редактирование флага "ишу/не ищу работу" = true
        + 3.1.4 Редактирование флага "ишу/не ищу работу" = false
        + 3.1.5 Передача некорректного строкового значения во флаг "ищу/не ищу работу"
        + 3.1.6 Передача численного значения во флаг "ищу/не ищу работу"
*/

public class TestPositive {
    static Authorization user;

    @BeforeClass
    public static void authentication() throws Exception {
        user = new Authorization("axelrodvl.test@gmail.com", "789456123");
    }

    /**
     * 3.1.1 Редактирование фамилии, имени и отчества
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
        Response response = Me.get(user);
        Employee employee = new Employee(response);

        // Test assertions
        assertEquals(200, response.getCode());
        assertEquals(employee.first_name, expectedFirstName);
        assertEquals(employee.last_name, expectedLastName);
        assertEquals(employee.middle_name, expectedMiddleName);
    }

    /**
     * 3.1.2 Редактирование фамилии и имени (отчество пустое)
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
        Response response = Me.get(user);
        Employee employee = new Employee(response);

        // Test assertions
        assertEquals(200, response.getCode());
        assertNull(employee.middle_name);
    }

    /**
     * 3.1.3 Редактирование флага "ишу/не ищу работу" = true
     */
    @Test
    public void setIsInSearchTrue() throws Exception {
        // Test data
        final String expectedFlag = "true";

        // Test actions
        Me.post(user, "is_in_search=" + expectedFlag);
        Response response = Me.get(user);
        Employee employee = new Employee(response);

        // Test assertions
        assertEquals(200, response.getCode());
        assertTrue(employee.is_in_search);
    }

    /**
     * 3.1.4 Редактирование флага "ишу/не ищу работу" = false
     */
    @Test
    public void setIsInSearchFalse() throws Exception {
        // Test data
        final String expectedFlag = "false";

        // Test actions
        Me.post(user, "is_in_search=" + expectedFlag);
        Response response = Me.get(user);
        Employee employee = new Employee(response);

        // Test assertions
        assertEquals(200, response.getCode());
        assertFalse(employee.is_in_search);
    }

    /**
     * 3.1.5 Передача некорректного строкового значения во флаг "ищу/не ищу работу"
     */
    @Test
    public void setIsInSearchIncorrectString() throws Exception {
        // Test data
        final String expectedFlag = "asdfasdf";

        // Test actions
        Me.post(user, "is_in_search=" + expectedFlag);
        Response response = Me.get(user);
        Employee employee = new Employee(response);

        // Test assertions
        assertEquals(200, response.getCode());
        assertFalse(employee.is_in_search);
    }

    /**
     * 3.1.6 Передача численного значения во флаг "ищу/не ищу работу"
     */
    @Test
    public void setIsInSearchIncorrectStringNumbers() throws Exception {
        // Test data
        final String expectedFlag = "0";

        // Test actions
        Me.post(user, "is_in_search=" + expectedFlag);
        Response response = Me.get(user);
        Employee employee = new Employee(response);

        // Test assertions
        assertEquals(200, response.getCode());
        assertFalse(employee.is_in_search);
    }
}
