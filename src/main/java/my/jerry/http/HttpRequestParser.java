package my.jerry.http;

import java.util.List;

public class HttpRequestParser implements HttpMessageParser {

    @Override
    public HttpFirstLine parseFirstLine(String line) {
        return null;
    }

    @Override
    public List<HttpHeader> parseHeaders(List<String> lines) {
        return null;
    }

    @Override
    public byte[] parseBody(List<String> lines) {
        return new byte[0];
    }
}
