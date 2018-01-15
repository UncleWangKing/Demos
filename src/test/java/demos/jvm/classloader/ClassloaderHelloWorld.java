package demos.jvm.classloader;

public class ClassloaderHelloWorld {

    public static void main(String[] args) throws ClassNotFoundException {

        System.out.println(ClassloaderHelloWorld.class.getClassLoader().getSystemClassLoader());
        //Launcher$AppClassLoader Launcher内部类
        System.out.println(String.class.getClassLoader());

        System.out.println(ClassloaderHelloWorld.class.getClassLoader()
                +"->"+ ClassloaderHelloWorld.class.getClassLoader().getParent()
                +"->"+ ClassloaderHelloWorld.class.getClassLoader().getParent().getParent());
    }
}
