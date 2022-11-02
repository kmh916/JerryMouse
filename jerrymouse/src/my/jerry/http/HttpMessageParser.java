package my.jerry.http;

import java.util.List;

public interface HttpMessageParser {

    HttpFirstLine parseFirstLine(String line);

    List<HttpHeader> parseHeaders(List<String> lines);

    byte[] parseBody(List<String> lines);

}
