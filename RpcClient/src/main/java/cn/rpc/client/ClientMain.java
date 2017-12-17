package cn.rpc.client;

import cn.rpc.client.bean.Product;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

public class ClientMain {
    public static void main(String[] args) {
        IProductService productService = (IProductService)rpc(IProductService.class);
        Product product = productService.queryById(100L);
        System.out.println(product);
    }

    public static Object rpc(final Class clazz){
        return Proxy.newProxyInstance(clazz.getClassLoader(),
                new Class[]{clazz}, new InvocationHandler() {
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Socket socket = new Socket("127.0.0.1", 8888);

                        //我们想远程调用哪个类的方法，并传递给这个方法什么参数
                        //注意我们只知道Product Client,并不知道Product Client在Product的实现
                        String clientClassName = clazz.getName();
                        String methodName = method.getName();
                        Class[] parameterTypes = method.getParameterTypes();

                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                        objectOutputStream.writeUTF(clientClassName);
                        objectOutputStream.writeUTF(methodName);
                        objectOutputStream.writeObject(parameterTypes);
                        objectOutputStream.writeObject(args);
                        objectOutputStream.flush();

                        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                        Object o = objectInputStream.readObject();

                        objectInputStream.close();
                        objectOutputStream.close();
                        return o;
                    }
                });
    }
}
