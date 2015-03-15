package Entity;

import org.apache.oltu.oauth2.client.response.OAuthResourceResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.util.HashMap;

/**
 * Error description JSON handler
 * Can be used for creating and comparing errors
 */
public class ErrorDescription {
    public HashMap<String, String> errors = null;
    public HashMap<String, String> bad_arguments = null;
    public String description = null;
    public String bad_argument = null;

    /**
     * Creating ErrorDescription object based on JSON server response
     * @param response JSON as String
     * @throws Exception
     */
    public ErrorDescription(OAuthResourceResponse response) throws Exception {
        JSONParser parser = new JSONParser();
        JSONObject responseJSON = (JSONObject) parser.parse(response.getBody());


        description = (String) responseJSON.get("description");

        bad_argument = (String) responseJSON.get("bad_argument");

        JSONArray errorsArray = (JSONArray) responseJSON.get("errors");
        if(errorsArray != null) {
            errors = new HashMap<String, String>();
            for (Object obj : errorsArray) {
                JSONObject node = (JSONObject) obj;
                errors.put((String) node.get("value"), (String) node.get("type"));
            }
        }

        JSONArray badArgumentsArray = (JSONArray) responseJSON.get("bad_arguments");
        if(badArgumentsArray != null) {
            bad_arguments = new HashMap<String, String>();
            for (Object obj : badArgumentsArray) {
                JSONObject node = (JSONObject) obj;
                bad_arguments.put((String) node.get("name"), (String) node.get("description"));
            }
        }
    }

    /**
     * Creating empty ErrorDescription object
     */
    public ErrorDescription() {}

    /**
     * ErrorDescription objects comparison
     * @param obj
     * @return
     */
    public boolean equals(Object obj) {
        if(obj == this)
            return true;

        if(obj == null)
            return false;

        if(getClass() != obj.getClass())
            return false;
        else {
            ErrorDescription error = (ErrorDescription) obj;

            // Description
            if((this.description == null) && (error.description != null))
                return false;
            else
                if((this.description != null) && (error.description == null))
                    return false;
                else
                    if((this.description != null) && (error.description != null))
                        if(!(this.description.equals(error.description)))
                            return false;

            // Bad argument
            if((this.bad_argument == null) && (error.bad_argument != null))
                return false;
            else
            if((this.bad_argument != null) && (error.bad_argument == null))
                return false;
            else
            if((this.bad_argument != null) && (error.bad_argument != null))
                if(!(this.bad_argument.equals(error.bad_argument)))
                    return false;

            // Errors
            if((this.errors == null) && (error.errors != null))
                return false;
            else
            if((this.errors != null) && (error.errors == null))
                return false;
            else
            if((this.errors != null) && (error.errors != null))
                if(!(this.errors.equals(error.errors)))
                    return false;

            // Bad arguments
            if((this.bad_arguments == null) && (error.bad_arguments != null))
                return false;
            else
            if((this.bad_arguments != null) && (error.bad_arguments == null))
                return false;
            else
            if((this.bad_arguments != null) && (error.bad_arguments != null))
                if(!(this.bad_arguments.equals(error.bad_arguments)))
                    return false;

            return true;
        }
    }
}
