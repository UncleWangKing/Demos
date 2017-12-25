package Demos.JVM.Reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class Reference {
    public static void main(String[] args){
        soft();
        weak();
        phantom();
    }
    public static void soft(){
        SoftReference soft = new SoftReference(new String("soft"));
        System.out.println("soft=" + soft.get());
        System.out.println("----------gc---------");
        System.gc();
        System.out.println("soft=" + soft.get());
    }
    public static void weak(){
        WeakReference weak = new WeakReference(new String("weak"));
        System.out.println("weak=" + weak.get());
        System.out.println("----------gc---------");
        System.gc();
        System.out.println("weak=" + weak.get());
    }
    public static void phantom(){
        String value = new String("phontom");
        PhantomReference<String> phantom = new PhantomReference<String>(value, new ReferenceQueue());
        System.out.println("phantom=" +phantom.get());
    }
}
