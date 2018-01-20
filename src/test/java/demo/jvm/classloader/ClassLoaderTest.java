package demo.jvm.classloader;

import java.util.Date;
import java.util.List;
//-Xbootclasspath/a:C:\Users\28529\Desktop\test\my_lib.jar
public class ClassLoaderTest {
  
    @SuppressWarnings("rawtypes")  
    public static void main(String[] args){
        System.out.println("ClassLoaderText:"+ClassLoaderTest.class.getClassLoader().getClass().getName());
        System.out.println("List:"+List.class.getClassLoader());
//        System.out.println("getSystemClassLoader:"+ClassLoader.getSystemClassLoader());

//        ClassLoader cl = ClassLoaderTest.class.getClassLoader();
//        while(cl != null){
//            System.out.print(cl.getClass().getName()+"->");
//            cl = cl.getParent();
//        }

        System.out.println();
        System.out.println("--------------------------------------------------");

        try {
            Class classDateApp = new MyClassLoader("D:\\SoftBoot\\idea\\workspace\\Demos\\src\\test\\java\\demos\\jvm\\classloader\\classfile").loadClass("demo.jvm.classloader.ClassLoaderAttachment");
            Class classDateMy = new MyClassLoader("D:\\SoftBoot\\idea\\workspace\\Demos\\src\\test\\java\\demos\\jvm\\classloader\\classfile").loadClass("ClassLoaderAttachment");
            Date dateApp = (Date) classDateApp.newInstance();
            Date dateMy = (Date) classDateMy.newInstance();
            //输出ClassLoaderAttachment类的加载器名称
            System.out.println("Default ClassLoader:"+ClassLoaderTest.class.getClassLoader().getSystemClassLoader());
            System.out.println("dateApp ClassLoader:"+dateApp.getClass().getClassLoader().getClass().getName());
            System.out.println("dateApp"+dateApp);
            System.out.println("dateMy ClassLoader:"+dateMy.getClass().getClassLoader().getClass().getName());
            System.out.println("dateMy:"+dateMy);
            System.out.println("dateApp instanceof  ClassLoaderAttachment:" + (dateApp instanceof  ClassLoaderAttachment));
            System.out.println("dateApp instanceof  demos.jvm.classloader.ClassLoaderAttachment:" + (dateApp instanceof  demo.jvm.classloader.ClassLoaderAttachment));
            System.out.println("dateMy instanceof  ClassLoaderAttachment:" + (dateMy instanceof  ClassLoaderAttachment));
            System.out.println("dateMy instanceof  demos.jvm.classloader.ClassLoaderAttachment:" + (dateMy instanceof  demo.jvm.classloader.ClassLoaderAttachment));
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
      
} 