package my.jerry.http;

public abstract class HttpFirstLine{
    private final String HTTP_VERSION = "HTTP/1.1";

    public String getHTTP_VERSION() {
        return HTTP_VERSION;
    }
}
