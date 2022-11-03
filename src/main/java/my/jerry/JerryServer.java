package my.jerry;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class JerryServer {
    public static void main(String[] args){
        URIParser parser = new URIParser();
        HtmlConverterImpl converter = new HtmlConverterImpl();
        MessageWriter<BufferedWriter, String> headerWriter = new HttpHeaderWriter();
        MessageWriter<BufferedWriter, String> bodyWriter = new HttpBodyWriter();

        try (
            Socket socket = new ServerSocket(8878).accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        ){


            String line = null;
            boolean firstLine = true;
            String uri = null;
            while(true){

                line = in.readLine();

                if(firstLine) {
                    uri = parser.parseUri(line);
                    firstLine = false;
                }

                if(line.equals("")){
                    System.out.println("flush");
                    List<String> headerLines = getHeader();
                    List<String> bodyLines = null;
                    try {
                        bodyLines = converter.convertHtmlToList(uri);
                        headerLines.add(converter.getContentLengthHeader(uri));
                    } catch (FileNotFoundException e) {
                        headerLines = get404Header();
                        bodyLines = converter.getNotFoundPageToStringList();
                        headerLines.add(converter.getContentLengthHeader("/NotFound.html"));
                    }
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
        }

    }



    private static List<String> getHeader() {
        List<String> list = new ArrayList<>();
        list.add("HTTP/1.1 200 OK");
        list.add("Content-Type: text/html; charset=utf-8");
        list.add("Cache-Control: no-store, no-store");
        return list;
    }

    private static List<String> get404Header() {
        List<String> list = new ArrayList<>();
        list.add("HTTP/1.1 404 Not Found");
        list.add("Content-Type: text/html; charset=utf-8");
        list.add("Cache-Control: no-store, no-store");
        return list;
    }

}
