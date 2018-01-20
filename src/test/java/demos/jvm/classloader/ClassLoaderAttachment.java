package demo.jvm.classloader;

import java.util.Date;

public class ClassLoaderAttachment extends Date {
    //打印数据
    @Override  
    public String toString(){  
        return "Hello ClassLoader!";  
    }  
  
}  