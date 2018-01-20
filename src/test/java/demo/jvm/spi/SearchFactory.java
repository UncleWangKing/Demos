package demo.jvm.spi;

import com.mysql.jdbc.Driver;

import java.util.Iterator;
import java.util.ServiceLoader;

public class SearchFactory {
    private SearchFactory() {  
    }  
    public static Driver newSearch() {
        Driver search = null;
        ServiceLoader<Driver> serviceLoader = ServiceLoader.load(Driver.class);

        Iterator<Driver> searchs = serviceLoader.iterator();
        System.out.println(searchs);
        if (searchs.hasNext()) {  
            search = searchs.next();
            System.out.println(search);
        }  
        return search;  
    }  
}  