package demo.jvm.spi;

import java.util.List;

public class SearchDb implements Search {
    @Override
    public List search(String keyword) {
        System.out.println("SearchDb");
        return null;
    }
}
