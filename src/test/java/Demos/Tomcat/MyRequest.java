package Demos.Tomcat;

import java.io.IOException;
import java.io.InputStream;

public class MyRequest {
    private String url;
    private String method;

    public String getUrl() {
        return url;
    }

    public String getMethod() {
        return method;
    }

    public MyRequest(InputStream inputStream) throws IOException{
        String httpRequest = "";
        byte[] httpRequestBytes = new byte[1024];
        int length = 0;
        if((length = inputStream.read(httpRequestBytes)) > 0){
            httpRequest = new String(httpRequestBytes, 0, length);
        }

//        HTTP请求协议
//        GET /favicon.ico HTTP/1.1
//        Accept: */*
//        Accept-Encoding: gzip, deflate
//        User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; rv:11.0) like Gecko
//        Host: localhost:8080
//        Connetction: Keep-Alive
        String httpHead = httpRequest.split("\n")[0];
        url = httpHead.split("\\s")[1];
        method = httpHead.split("\\s")[0];
        System.out.println(this);
    }
}
