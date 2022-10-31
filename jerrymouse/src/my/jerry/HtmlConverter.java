package my.jerry;

import java.io.IOException;
import java.util.List;

public interface HtmlConverter<T> {
    List<T> convertHtmlToList(String uri) throws IOException;

}
