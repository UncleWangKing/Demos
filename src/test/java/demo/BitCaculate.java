package Demos;

/**
    展示如何用位运算代替 对100取余
*/ 
public class BitCaculate {

    public static void main(String[] args) {
        int i = 6666623;
        int q,r;
        q = i / 100;
        // really: r = i - q*(64+32+4);
        // really: r = i - (q * 100);
        r = i - ((q << 6) + (q << 5) + (q << 2));
        int num1 = q<<6;
        int num2 = q<<5;
        int num3 = q<<2;

        System.out.println(i + "-(" + num1 + "+" + num2 + "+" + num3 + ")");
        System.out.println("%100结果" + r);
        int testNum = 32 >>> 6;
        System.out.println(testNum);
    }
}
