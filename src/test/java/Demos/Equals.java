package Demos;

import java.util.HashMap;

//通过自写equals  来感受map中以及所有自带数据结构中的equals执行前
// 需要先比较hashcode
public class Equals {
    private String color;

    public Equals(String color) {
        this.color = color;
    }

    public boolean equals(Object obj) {
        if(obj==null) return false;
        if (!(obj instanceof Equals))
            return false;   
        if (obj == this)
            return true;
        return this.color.equals(((Equals) obj).color);
    }

    public static void main(String[] args) {
        Equals a1 = new Equals("green");
        Equals a2 = new Equals("red");

        //equals函数可以返回true 和预期相符
        System.out.println(new Equals("green").equals(a1));

        //hashMap stores apple type and its quantity
        HashMap<Equals, Integer> m = new HashMap<Equals, Integer>();
        m.put(a1, 10);
        m.put(a2, 20);
        //同一个类 完全可以在map找到
        System.out.println(m.get(a1));
        //hashcode先比较 不同所以找不到
        System.out.println(m.get(new Equals("green")));
    }
}