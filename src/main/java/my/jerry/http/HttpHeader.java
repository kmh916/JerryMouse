package my.jerry.http;

public class HttpHeader {
    private final String key;
    private final String value;

    public HttpHeader(String key, String value) {
        this.key = key;
        this.value = value;
    }

    /*
     *    Key: SP Value CRLF
     * */


    @Override
    public String toString() {
        return key + ": " + value + "\r\n";
    }
}
