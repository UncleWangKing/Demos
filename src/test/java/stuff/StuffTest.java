package stuff;

public class StuffTest {

    public static void main(String[] args) throws ClassNotFoundException {

        System.out.println(StuffTest.class.getClassLoader());
        System.out.println(String.class.getClassLoader());

        System.out.println(StuffTest.class.getClassLoader()
                +"->"+StuffTest.class.getClassLoader().getParent()
                +"->"+StuffTest.class.getClassLoader().getParent().getParent());
    }
}
