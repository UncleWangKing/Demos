package demos.concurrent;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WorkerThread implements Runnable{
    @Override  
    public void run() {  
         System.out.println(Thread.currentThread().getName()+" Start. Time = "+getNowDate());  
         threadSleep();  
         System.out.println(Thread.currentThread().getName()+" End. Time = "+getNowDate());  
          
    }  
    /** 
     * 睡3秒 
     */  
    public void threadSleep(){  
        try {  
            Thread.sleep(3000);  
        } catch (InterruptedException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
    }  
     /** 
      * 获取现在时间 
      *  
      * @return 返回时间类型 yyyy-MM-dd HH:mm:ss 
      */  
    public static String getNowDate() {  
          Date currentTime = new Date();
          SimpleDateFormat formatter;
            formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");   
            String ctime = formatter.format(currentTime);   
          return ctime;  
         }  
}  