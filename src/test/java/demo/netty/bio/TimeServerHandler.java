package demo.netty.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

public class TimeServerHandler implements Runnable {
    private Socket socket;
    public TimeServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run(){
        BufferedReader in  = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            out = new PrintWriter(this.socket.getOutputStream(), true);
            String currentTime = null;
            String body = null;
            while(true){
                body = in.readLine();
                if(null == body)
                    break;
                System.out.println("The time server receive order : " + body);
                currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ?
                        new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
                out.println(currentTime);
            }
        }catch (Exception e){
            //关闭in out socket
            if(null != in){
                try {
                    in.close();
                }catch (IOException e1){
                    e1.printStackTrace();
                }
            }
            if(null != out){
                out.close();
            }
            if(null != this.socket){
                try {
                    this.socket.close();
                }catch (IOException e1){
                    e1.printStackTrace();
                }
                this.socket = null;
            }
        }
    }
}
