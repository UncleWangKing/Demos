package demos.concurrent;

public class DDLSingleton {
 private volatile static DDLSingleton instance = null;
 private DDLSingleton() {}
 public static DDLSingleton getInstance() {
  if (instance == null) {  
   synchronized (DDLSingleton.class) {// 1
    if (instance == null) {// 2  
     instance = new DDLSingleton();// 3
    }  
   }  
  }  
  return instance;  
 }  
}  