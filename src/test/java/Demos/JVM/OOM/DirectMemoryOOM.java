package Demos.JVM.OOM;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * VM Args：-Xmx20M -XX:MaxDirectMemorySize=10M
 * @author zzm
 */
public class DirectMemoryOOM {

	private static final int _1MB = 1024 * 1024;

	public static void main(String[] args) throws Exception {
		Field[] declaredFields = Unsafe.class.getDeclaredFields();
		Field unsafeField = null;
		for (Field field: declaredFields) {
			if("theUnsafe" == field.getName()) {
				unsafeField = field;
			}
		}
//		getDeclaredFields 方法 返回数组并不保证顺序------不保证顺序不是说会故意打乱顺序
//		Field unsafeField = Unsafe.class.getDeclaredFields()[0];
		unsafeField.setAccessible(true);
		Unsafe unsafe = (Unsafe) unsafeField.get(null);
//		该方法被限制权限
//		Unsafe unsafe = Unsafe.getUnsafe();
		while (true) {
			unsafe.allocateMemory(_1MB);
		}
	}
}