package my.jerry.http;

public enum HttpMethod {
    GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS, TRACE;


    public boolean contains(String value){
        for (HttpMethod method : values()) {
            if(method.name().equals(value))
                return true;
        }
        return false;
    }

}
