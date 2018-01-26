package stuff;

public class StuffTest {

    public static void main(String[] args) throws ClassNotFoundException {

        String subMyZnode = "/locks/test2_lock_0000000401".substring("/locks/test2_lock_0000000401".lastIndexOf("/") + 1);
        System.out.println(subMyZnode);
    }
}
