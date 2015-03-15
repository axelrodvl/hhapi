package Web;

import org.openqa.selenium.By;

public class Xpath {
    public static class LoginWindow {
        public static By editLogin = By.xpath("/html/body/div[1]/div[2]/div/form/label[1]/input");
        public static By editPassword = By.xpath("/html/body/div[1]/div[2]/div/form/label[2]/input");
        public static By buttonLogin = By.xpath("/html/body/div[1]/div[2]/div/form/div[3]/input");
    }

    public static class AccessApproval {
        public static By buttonConfirm = By.xpath("/html/body/div/div[2]/div/form[1]/p/button[1]");
        public static By textQuestion = By.xpath("/html/body/div/div[2]/div/p");
    }
}