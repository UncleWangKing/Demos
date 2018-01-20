package demo.proxy;

import org.springframework.util.FileCopyUtils;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class MyProxy {
    private static final String rt="\r";

    public static Object newProxyInstance(MyClassLoader loader,Class<?> interfaces, MyInvocationHandler h) throws IllegalArgumentException{

        if(null == h){
            throw new NullPointerException();
        }
        //根据代理接口构造代理类
        Method[] methods = interfaces.getMethods();
        StringBuffer proxyClassString = new StringBuffer();
        proxyClassString.append("package ")
                .append(loader.getProxyClassPackage()).append(";").append(rt)
                .append("import java.lang.reflect.Method;").append(rt)
                .append("public class $MyProxy0 implements ").append(interfaces.getName()).append("{").append(rt)
                .append("MyInvocationHandler h;").append(rt)
                .append("public $MyProxy0(MyInvocationHandler h){")
                .append("this.h = h;}").append(rt)
                .append(getMethodString(methods,interfaces))
                .append("}");
        //写入JAVA文件 进行编译
        String filename = loader.getDir() + File.separator + "$MyProxy0.java";
        File myProxyFile = new File(filename);
        try {
            compile(proxyClassString, myProxyFile);

            //利用自定义的classloader加载
            Class $myProxy0 = loader.findClass("$MyProxy0");
            //$MyProxy0初始化
            Constructor constructor = $myProxy0.getConstructor(MyInvocationHandler.class);

            Object o = constructor.newInstance(h);

            return o;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private static void compile(StringBuffer proxyClassString, File myProxyFile) throws IOException{
        FileCopyUtils.copy(proxyClassString.toString().getBytes(), myProxyFile);

        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();

        StandardJavaFileManager standardJavaFileManager = javaCompiler.getStandardFileManager(null,null,null);

        Iterable javaFileObjects = standardJavaFileManager.getJavaFileObjects(myProxyFile);

        JavaCompiler.CompilationTask task = javaCompiler.getTask(null, standardJavaFileManager, null,null,null, javaFileObjects);

        task.call();

        standardJavaFileManager.close();
    }

    private static String getMethodString(Method[] methods, Class interfaces){
        StringBuffer methodStringBuff = new StringBuffer();
        for (Method method:methods) {
            methodStringBuff.append("public void ").append(method.getName())
                    .append("()").append("throws Throwable{")
                    .append("Method method1 = ").append(interfaces.getName())
                    .append(".class.getMethod(\"").append(method.getName())
                    .append("\",new Class[]{});")
                    .append("this.h.invoke(this,method1,null);}").append(rt);
        }
        return methodStringBuff.toString();
    }
}
