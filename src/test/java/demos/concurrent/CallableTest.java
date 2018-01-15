package demos.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class CallableTest {
      
    public static void main(String[] args) {  
//        //创建线程池
//        ExecutorService es = Executors.newSingleThreadExecutor();
//        //创建Callable对象任务
//        CallableDemo calTask=new CallableDemo();
//        //提交任务并获取执行结果
//        Future<Integer> future =es.submit(calTask);
//        //关闭线程池
//        es.shutdown();
        //创建线程池
        ExecutorService es = Executors.newSingleThreadExecutor();
        //创建Callable对象任务
        CallableDemo calTask=new CallableDemo();
        //创建FutureTask
        FutureTask<Integer> future=new FutureTask<>(calTask);
        //执行任务
        es.submit(future);
        //关闭线程池
        es.shutdown();
        try {  
            System.out.println("主线程在执行其他任务");
            Thread.sleep(2000);

        if(future.get()!=null){  
            //输出获取到的结果  
            System.out.println("future.get()-->"+future.get());  
        }else{  
            //输出获取到的结果  
            System.out.println("future.get()未获取到结果");  
        }  
          
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        System.out.println("主线程在执行完成");  
    }  
}  