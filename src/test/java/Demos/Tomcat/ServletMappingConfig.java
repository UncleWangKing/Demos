package Demos.Tomcat;

import java.util.ArrayList;
import java.util.List;

public class ServletMappingConfig {
    public static List<ServletMapping> servletMappingList = new ArrayList<ServletMapping>();

    static {
        servletMappingList.add(new ServletMapping("findGirl", "/girl", "Demos.Tomcat.FindGirlServlet"));
        servletMappingList.add(new ServletMapping("helloWorld", "/world", "Demos.Tomcat.HelloWorldServlet"));
    }
}
