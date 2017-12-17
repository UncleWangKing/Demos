package Demos.Tomcat;

import java.io.IOException;

public class HelloWorldServlet extends MyServlet {
    public void doGet(MyRequest myRequest, MyResponse myResponse) {
        try {
            myResponse.write("get world...");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void doPost(MyRequest myRequest, MyResponse myResponse) {
        try {
            myResponse.write("post world...");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
