package demo.tomcat;

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
//        GET /girl HTTP/1.1
//        Host: localhost:8080
//        Connection: keep-alive
//        User-Agent: Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36
//        Upgrade-Insecure-Requests: 1
//        Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8
//        Accept-Encoding: gzip, deflate, br
//        Accept-Language: zh-CN,zh;q=0.9
//        Cookie: Idea-dcd385b9=46cc524a-4dc8-4890-bfe7-a7a69ccef2f2
        String httpHead = httpRequest.split("\n")[0];
        url = httpHead.split("\\s")[1];
        method = httpHead.split("\\s")[0];

        System.out.println(httpRequest);
    }

    @Override
    public String toString() {
        return "MyRequest{" +
                "url='" + url + '\'' +
                ", method='" + method + '\'' +
                '}';
    }
}
