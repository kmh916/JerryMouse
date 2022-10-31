package my.jerry;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JerryServer {
    public static void main(String[] args){
        Socket socket = null;
        BufferedReader in = null;
        BufferedWriter out = null;
        URIParser parser = new URIParser();
        HtmlConverterImpl converter = new HtmlConverterImpl();
        MessageWriter<BufferedWriter, String> headerWriter = new HttpHeaderWriter();
        MessageWriter<BufferedWriter, String> bodyWriter = new HttpBodyWriter();

        try {
            socket = new ServerSocket(8878).accept();
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            String line = null;
            boolean firstLine = true;
            boolean favicon = false;
            String uri = null;
            while(true){

                line = in.readLine();

                if(firstLine) {
                    uri = parser.parseUri(line);
                    firstLine = false;
                }

                favicon = (uri == null);

                if(line.equals("") && !favicon){
                    System.out.println("flush");
                    List<String> headerLines = getHeader();
                    List<String> bodyLines = converter.convertHtmlToList(uri);
                    headerLines.add(converter.getContentLengthHeader(uri));
                    headerWriter.write(out,headerLines);
                    bodyWriter.write(out,bodyLines);
                    out.flush();
                    firstLine = true;
//                    break;
                }
                System.out.println("클라이언트 : " + line);
            }

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }


    private static List<String> convertLinesToList(BufferedReader br) throws IOException {
        List<String> list = new ArrayList<>();
        while(true){
            String line = br.readLine();
            if(line.equals("")){
                return list;
            }
            list.add(line);
        }
    }

    private static List<String> getHeader() throws IOException {
        List<String> list = new ArrayList<>();
        list.add("HTTP/1.1 200 OK");
        list.add("Content-Type: text/html; charset=utf-8");
        list.add("Cache-Control: no-store, no-store");
        return list;
    }
}
