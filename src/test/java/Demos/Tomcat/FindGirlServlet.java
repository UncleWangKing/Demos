package demos.tomcat;

import java.io.IOException;

public class FindGirlServlet extends MyServlet {
    public void doGet(MyRequest myRequest, MyResponse myResponse) {
        try {
            myResponse.write("get girl...");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void doPost(MyRequest myRequest, MyResponse myResponse) {
        try {
            myResponse.write("post girl...");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
