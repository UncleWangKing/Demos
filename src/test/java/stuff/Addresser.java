package stuff;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class Addresser {
      
    private static Unsafe unsafe;
  
    static {  
        try {  
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);  
            unsafe = (Unsafe) field.get(null);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    public static long addressOf(Object o) throws Exception {  
          
        Object[] array = new Object[] { o };  
  
        long baseOffset = unsafe.arrayBaseOffset(Object[].class);  
        int addressSize = unsafe.addressSize();
        long objectAddress;  
        switch (addressSize) {  
        case 4:  
            objectAddress = unsafe.getInt(array, baseOffset);  
            break;  
        case 8:  
            objectAddress = unsafe.getLong(array, baseOffset);  
            break;  
        default:  
            throw new Error("unsupported address size: " + addressSize);  
        }  
        return (objectAddress);  
    }  
  
    public static void main(String... args) throws Exception {  
        Object mine = "Hi there".toCharArray();
        long address = addressOf(mine);  
        System.out.println("Addess: " + address);

        // Verify address works - should see the characters in the array in the output
        printBytes(address, 27);
    }

    public static void printBytes(long objectAddress, int num) {
        for (long i = 0; i < num; i++) {
            int cur = unsafe.getByte(objectAddress + i);
            System.out.print((char) cur);
        }
        System.out.println();
    }
}