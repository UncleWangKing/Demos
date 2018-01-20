package demo.proxy;

public class Test {
    public static void main(String[] args) throws Throwable {
        Man man = new LiuWang();
        MyHandler myHandler = new MyHandler(man);
        Man proxyMan = (Man) MyProxy.newProxyInstance(
                new MyClassLoader(
                        "D:\\SoftBoot\\idea\\workspace\\Demos\\src\\test\\java\\demos\\proxy"
                ,"demos.proxy")
                ,Man.class, myHandler);


        System.out.println(proxyMan.getClass().getName());
        proxyMan.findObj();
    }
}
