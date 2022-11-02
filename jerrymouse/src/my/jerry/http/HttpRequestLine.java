package my.jerry.http;

public class HttpRequestLine extends HttpFirstLine{
    private final HttpMethod method;
    private final String uri;

    public HttpRequestLine(HttpMethod method, String uri) {
        this.method = method;
        this.uri = uri;
    }

    /*
    *   HttpRequestLine [Method SP Request-URI SP HTTP-Version CRLF]
    * */

    @Override
    public String toString() {
        return method.name() + " " + this.uri + " " + this.getHTTP_VERSION() + "\r\n";
    }
}
