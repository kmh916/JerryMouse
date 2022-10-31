package my.jerry;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

public class TCPMessageWriter implements MessageWriter<BufferedWriter,String>{
    @Override
    public void write(BufferedWriter writer, List<String> lines) throws IOException {
        for (String line : lines) {
            writer.write(line+"\r\n");
        }
//        writer.flush();
    }
}
