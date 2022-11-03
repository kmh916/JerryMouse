package my.jerry.http;

public class HttpStatusLine extends HttpFirstLine {
    private final HttpStatus status;

    public HttpStatusLine(HttpStatus status) {
        this.status = status;
    }

    /*
    *   HttpStatusLine [HTTP-Version SP Status-Code SP Reason-Phrase CRLF]
    * */

    @Override
    public String toString() {
        return this.getHTTP_VERSION() + " " + status.getCode() + " " + status.getReasonPhrase() + "\r\n";
    }
}
