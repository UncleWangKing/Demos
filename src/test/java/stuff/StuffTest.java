package stuff;

public class StuffTest {
    public static void main(String[] args) {
        new Thread( () -> System.out.println("In Java8, Lambda expression rocks !!") ).start();
    }
}
