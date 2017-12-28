package demos.jvm.classLoader;

import java.util.List;

public class ClassLoaderTest {
  
    @SuppressWarnings("rawtypes")  
    public static void main(String[] args){  
        System.out.println("ClassLoaderText:"+ClassLoaderTest.class.getClassLoader().getClass().getName());
        System.out.println("System:"+System.class.getClassLoader());
        System.out.println("List:"+List.class.getClassLoader());
          
        ClassLoader cl = ClassLoaderTest.class.getClassLoader();  
        while(cl != null){  
            System.out.print(cl.getClass().getName()+"->");  
            cl = cl.getParent();  
        }  
        System.out.println(cl);
    }
      
} 