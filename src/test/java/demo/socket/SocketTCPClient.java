package demo.socket;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

class SocketTCPClient
{  
    private Socket s;
    SocketTCPClient(Socket s)  
    {  
        this.s = s;  
    }  
   
    public static void main(String []args)  
    {  
            client();  
    }  
   
    public static void client()  
    {  
        try  
        {  
            Socket s = new Socket(InetAddress.getByName("localhost"),8000);//端口号要一致。
   
            OutputStream os = s.getOutputStream();
            os.write("Hello World!".getBytes());  
   
            InputStream is = s.getInputStream();
            byte [] buf = new byte[100];
            int length = 0;
            while((length = is.read(buf)) > 0)
                System.out.println(new String(buf,0,length));
//            1.7后写在try里的会被自动释放
            os.close();
            is.close();
            s.close();  
   
        }  
        catch(Exception e)  
        {  
            e.printStackTrace();  
        }  
    }  
}  