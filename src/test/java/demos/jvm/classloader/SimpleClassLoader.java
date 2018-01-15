package demos.jvm.classloader;


import java.io.*;

public class SimpleClassLoader extends ClassLoader{

    //需要加载类.class文件的目录
    private String classDir;

    //无参的构造方法，用于class.newInstance()构造对象使用
    public SimpleClassLoader(){
    }

    public SimpleClassLoader(String classDir){
        this.classDir = classDir;  
    }

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {

        //class文件的路径
        String classPathFile = classDir + "\\" + name + ".class";
        System.out.println("classPathFile:" + classPathFile);
        try {
            //将class文件进行解密
            FileInputStream fis = new FileInputStream(classPathFile);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int bytes = -1;
            while((bytes = fis.read())!= -1){
                bos.write(bytes);
            }
            byte[] classByte = bos.toByteArray();
            //将字节流变成一个class
            return defineClass(classByte,0,classByte.length);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    //测试，先将ClassLoaderAttachment.class文件加密写到工程的class_temp目录下  
    public static void main(String[] args) throws Exception{
        Class clazz = new SimpleClassLoader("D:\\SoftBoot\\idea\\workspace\\Demos\\src\\test\\java\\demos\\jvm\\classloader\\classfile").loadClass("demos.jvm.classloader.ClassLoaderAttachment");
        //0xCOFFBABE
//        System.out.println("clazz:"+clazz);
//        System.out.println("clazz classLoader:"+clazz.getClassLoader());
        //类型判断
        System.out.println("ClassLoaderAttachment classLoader:"+ClassLoaderAttachment.class.getClassLoader());
        Object attachment = clazz.newInstance();
        //ClassLoaderAttachment 写法代表着 demos.jvm.classloader.ClassLoaderAttachment
        System.out.println(clazz.getClassLoader());
        System.out.println("attachment instanceof  ClassLoaderAttachment:" + (attachment instanceof  ClassLoaderAttachment));
    }
}  