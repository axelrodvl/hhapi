package Entity;

import API.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Employee object JSON handler
 * Can be used for creating employees and getting info about them
 */
public class Employee {
    public String id;
    public String resumes_url;
    public String first_name;
    public String middle_name;
    public String last_name;
    public String email;
    public String negotiations_url;

    public Boolean is_admin;
    public Boolean is_employer;
    public Boolean is_in_search;
    public Boolean is_anonymous;
    public Boolean is_applicant;

    public Long unread_negotiations;
    public Long new_resume_views;

    /**
     * Creating Employee object from API's /me response
     * @param response JSON as String
     * @throws Exception
     */
    public Employee(Response response) throws Exception {
        JSONParser parser = new JSONParser();
        JSONObject responseJSON = (JSONObject) parser.parse(response.getBody());

        // String or null
        id = (String) responseJSON.get("id");
        resumes_url = (String) responseJSON.get("resumes_url");
        first_name = (String) responseJSON.get("first_name");
        middle_name = (String) responseJSON.get("middle_name");
        last_name = (String) responseJSON.get("last_name");
        email = (String) responseJSON.get("email");
        negotiations_url = (String) responseJSON.get("negotiations_url");

        // Boolean or null
        is_admin = (Boolean) responseJSON.get("is_admin");
        is_employer = (Boolean) responseJSON.get("is_employer");
        is_in_search = (Boolean) responseJSON.get("is_in_search");
        is_anonymous = (Boolean) responseJSON.get("is_anonymous");
        is_applicant = (Boolean) responseJSON.get("is_applicant");

        // Long or null
        JSONObject counters = (JSONObject) responseJSON.get("counters");
        unread_negotiations = (Long) counters.get("unread_negotiations");
        new_resume_views = (Long) counters.get("new_resume_views");
    }
}
