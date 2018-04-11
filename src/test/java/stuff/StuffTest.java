package stuff;

public class StuffTest {
    public static void main(String[] args) {
        int count_u = 0;
        for (int i = 0; i < 9; ++i)
            for (int j = 0; j < 9; ++j)
                if(i % 3 != j % 3) {
                    System.out.println("i:" + i + " j:" + j);
                    count_u++;
                }
        System.out.println(count_u);
    }
}
