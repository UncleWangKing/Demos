package demos.jvm.spi;

import com.mysql.jdbc.Driver;

public class SearchTest {
    public static void main(String[] args) {
        Driver search = SearchFactory.newSearch();
        System.out.println(search);
    }  
}  