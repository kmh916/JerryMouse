package my.jerry;

public class URIParser {

    public String parseUri(String line){
        if(line.contains("GET")) {
            String[] arr = line.split(" ");
            if(arr.length != 3)
                throw new IllegalArgumentException("line not valid");
            String uri = arr[1];

            return uri.equals("/favicon.ico") ? null : uri;
        } else {
            throw new IllegalArgumentException("line not contains 'GET'");
        }
    }
}
