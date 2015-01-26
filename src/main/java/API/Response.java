package API;

/**
 * Http response handler
 */
public class Response {
    private int code;
    private String body;

    /**
     * Creating new Response object based on Http response body and code
     * @param code
     * @param body
     */
    public Response(int code, String body) {
        this.code = code;
        this.body = body;
    }

    /**
     * Http response code getter
     * @return code
     */
    public int getCode() {
        return code;
    }

    /**
     * Http response body getter
     * @return body
     */
    public String getBody() {
        return body;
    }
}
