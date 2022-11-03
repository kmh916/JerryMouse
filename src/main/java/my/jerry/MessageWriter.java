package my.jerry;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

public interface MessageWriter<T extends Writer,D> {
    void write(T writer, List<D> lines) throws IOException;
}
