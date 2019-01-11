package demo.netty.aio;

import java.io.IOException;

public class TimeServer {

    public static void main(String[] args)throws IOException {
        int port = 8080;

        AsyncTimeServerHandler timeServerHandler = new AsyncTimeServerHandler(port);
        new Thread(timeServerHandler,"AIOServer").start();
    }

}