package demo.netty.aio;

public class TimeClient {


    public static void main(String[] args){
        int port = 8080;

        new Thread(new AsyncTimeClientHandler("127.0.0.1",port),"AIOClient").start();
    }

}