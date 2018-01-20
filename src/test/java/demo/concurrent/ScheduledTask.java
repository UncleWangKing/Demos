package demo.concurrent;

import java.util.Date;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduledTask {
    public ScheduledThreadPoolExecutor se = new ScheduledThreadPoolExecutor(5);
    public static void main(String[] args) {  
        new ScheduledTask();  
    }  
    public void fixedPeriodSchedule() {  
        // 设定可以循环执行的runnable,初始延迟为0，这里设置的任务的间隔为5秒  
        for(int i=0;i<5;i++){  
            se.scheduleAtFixedRate(new FixedSchedule(), 0, 5, TimeUnit.SECONDS);
        }  
    }  
    public ScheduledTask() {  
        fixedPeriodSchedule();  
    }  
    class FixedSchedule implements Runnable {  
        public void run() {  
            System.out.println("当前线程："+Thread.currentThread().getName()+"  当前时间："+new Date(System.currentTimeMillis()));
        }  
    }  
}  