package demos.socket;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

class SocketUDPSend
{  
    public static void main(String[]args)  
    {  
            sed();  
    }  
   
    public static void sed()  
    {  
        try  
        {  
            DatagramSocket ds = new DatagramSocket();
            String str = "haha, my name is liuwang!";
            DatagramPacket dp = new DatagramPacket(str.getBytes(),0,str.length(),
                                                InetAddress.getByName("localhost"),8600);//发送给本机的地址，端口为8600
                    ds.send(dp);  
   
            //演示接受返回回来的数据。  
            byte[] buf = new byte[100];  
            DatagramPacket dp2 = new DatagramPacket(buf,100);//字节数组，长度  
            ds.receive(dp2);  
            System.out.println(new String(buf,0,dp2.getLength()));  
            ds.close();  
        }  
        catch(Exception e)  
        {  
            e.printStackTrace();  
        }  
    }  
   
}  