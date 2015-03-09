package Web;

public class Xpath {
    public static class LoginWindow {
        public static String editLogin = "/html/body/div[1]/div[2]/div/form/label[1]/input";
        public static String editPassword = "/html/body/div[1]/div[2]/div/form/label[2]/input";
        public static String buttonLogin = "/html/body/div[1]/div[2]/div/form/div[3]/input";
    }

    public static class AccessApproval {
        public static String buttonConfirm = "/html/body/div/div[2]/div/form[1]/p/button[1]";
        public static String textQuestion = "/html/body/div/div[2]/div/p";
    }
}