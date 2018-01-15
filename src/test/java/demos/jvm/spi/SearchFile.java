package demos.jvm.spi;

import java.util.List;

public class SearchFile implements Search {
    @Override
    public List search(String keyword) {
        System.out.println("SearchFile");
        return null;
    }
}
