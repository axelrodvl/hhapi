import API.Authorization;

public class Work {
    static Authorization user;

    public static void main(String[] args) throws Exception {
        // Using already taken access token
        //String testAccessToken = "RQ607OVQJDSDHOSUG0GN3JM2MA9J7LA03C5FKUVTRDIK8SRL477TR8DQU3LA59JF";
        //user = new Authorization(testAccessToken);

        // Creating new access token and retrieving authorization at new user
        user = new Authorization("axelrodvl.test@gmail.com", "789456123");
        System.out.println(user.getAccessToken());
    }
}
