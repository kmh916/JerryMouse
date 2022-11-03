package my.jerry;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class HtmlConverterImpl implements HtmlConverter<String> {

    private static final String HTML_CONTEXT_PATH = "html";


    public static void main(String[] args) throws IOException {
        HtmlConverterImpl htmlConverter = new HtmlConverterImpl();
        List<String> strings = htmlConverter.convertHtmlToList("/test.html");

        strings.forEach(System.out::println);

    }


    @Override
    public List<String> convertHtmlToList(String uri) throws IOException {
        List<String> list = new ArrayList<>();
        File file = getFile(uri);

        String line = null;
        BufferedReader br = new BufferedReader(new FileReader(file));
        while((line = br.readLine()) != null){
            list.add(line);
        }
        return list;
    }

    public String getContentLengthHeader(String uri) throws IOException{
        int byteSize = getByteSize(uri);

        return "Content-Length: "+byteSize;
    }

    public List<String> getNotFoundPageToStringList() throws IOException {
        List<String> list = new ArrayList<>();
        File file = getFile("/NotFound.html");

        String line = null;
        BufferedReader br = new BufferedReader(new FileReader(file));
        while((line = br.readLine()) != null){
            list.add(line);
        }
        return list;
    }

    private int getByteSize(String uri) throws IOException {
        int contentsLength = 0;
        File file = getFile(uri);

        String line = null;
        BufferedReader br = new BufferedReader(new FileReader(file));
        while((line = br.readLine()) != null){
            contentsLength += line.getBytes(StandardCharsets.UTF_8).length+2; // 2 means \r\n
        }
        return contentsLength;
    }

    private File getFile(String uri) throws FileNotFoundException{
        File file = new File(HTML_CONTEXT_PATH + uri);
        if(file.exists())
            return file;
        else
            throw new FileNotFoundException("not found file");
    }
}
