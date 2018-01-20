package demos.concurrent;

public class DCLSingleton {
 private static DCLSingleton instance = null;
 private DCLSingleton() {}
 public static DCLSingleton getInstance() {
  if (instance == null) {  
   synchronized (DCLSingleton.class) {// 1
    if (instance == null) {// 2  
     instance = new DCLSingleton();// 3
    }  
   }  
  }  
  return instance;  
 }  
}  