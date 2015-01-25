package Entity;

import API.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.ArrayList;
import java.util.HashMap;

/*
{
  "errors": [
    {
      "type": "bad_argument",
      "value": "first_name"
    },
    {
      "type": "bad_argument",
      "value": "last_name"
    },
    {
      "type": "bad_argument",
      "value": "middle_name"
    },
    {
      "type": "bad_argument",
      "value": "is_in_search"
    }
  ],
  "bad_arguments": [
    {
      "name": "first_name",
      "description": "Conflict with is_in_search."
    },
    {
      "name": "last_name",
      "description": "Conflict with is_in_search."
    },
    {
      "name": "middle_name",
      "description": "Conflict with is_in_search."
    }
  ],
  "description": "Conflict with is_in_search.",
  "bad_argument": "first_name"
}
*/

public class ErrorDescription {
    public HashMap<String, String> errors = null;
    public HashMap<String, String> bad_arguments = null;
    public String description = null;
    public String bad_argument = null;

    public ErrorDescription(Response response) throws Exception {
        JSONParser parser = new JSONParser();
        JSONObject responseJSON = (JSONObject) parser.parse(response.getBody());

        description = (String) responseJSON.get("description");
        bad_argument = (String) responseJSON.get("bad_argument");

        JSONArray errorsArray = (JSONArray) responseJSON.get("errors");
        errors = new HashMap<String, String>();
        for(Object obj : errorsArray) {
            JSONObject node = (JSONObject) obj;
            errors.put((String) node.get("value"), (String) node.get("type"));
        }

        JSONArray badArgumentsArray = (JSONArray) responseJSON.get("bad_arguments");
        bad_arguments = new HashMap<String, String>();
        for(Object obj : badArgumentsArray) {
            JSONObject node = (JSONObject) obj;
            bad_arguments.put((String) node.get("name"), (String) node.get("description"));
        }
    }
}
