package my.jerry.http;

import java.util.List;

public class HttpMessage {
    private HttpFirstLine firstLine;
    private List<HttpHeader> headers;
    private byte[] body;
}
