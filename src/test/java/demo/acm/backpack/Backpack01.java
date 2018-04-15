package demo.acm.backpack;

public class Backpack01 {
    public static void main(String[] args) throws Exception {
        int val[] = {40, 10, 30, 50};//物品价值数组
        int wt[] = {5, 4, 6, 3};//物品重量数组
        int totalWeight = 10;//背包可装总重量

        System.out.println(knapsack(val, wt, totalWeight));
    }

    public static int knapsack(int val[], int wt[], int totalWeight) {
        //获得物品总数量
        //可以是 wt.length 或者 val.length 都可以
        int N = wt.length;

        //创建储存搜索过程的二维数组
        //行数是物品总数量+1 列是总容量+1
        //+1是因为第0列和第0行都置0,作为初始状态，有效的搜索从第1行第1列开始
        int[][] dp = new int[N + 1][totalWeight + 1];
/**
 * java创建数组默认就是0 java中以下两步不需要
 */
//        //第一行置0
//        for (int col = 0; col <= totalWeight; col++)
//            dp[0][col] = 0;
//
//        //第一列置0
//        for (int row = 0; row <= N; row++)
//            dp[row][0] = 0;

        for (int item=1;item<=N;item++){
            //逐行从左到右填充
            //每一个 dp[i][j] 都代表有前i种物品的情况下 总重为j的背包可以装的最大价值
            for (int weight=1;weight<=totalWeight;weight++){
                //当前所剩重量装不下
                if (wt[item-1] <= weight){
                    //val[item-1]+dp[item-1][weight-wt[item-1]]
                    //  上一行(当前物品出现之前)---减去当前物品的重量列的值 + 当前物品价值
                    //dp[item-1][weight]
                    //  上一行(当前物品出现之前)---相同列的值
                    //两者中选更大的填充结果
                    dp[item][weight]=Math.max(val[item-1]+dp[item-1][weight-wt[item-1]], dp[item-1][weight]);
                    //算法思想通俗解释:
                    //循环每到新的一行等于新的物品出现了
                    //我是一个重量为w，价值为v,第i个出现的物品，我来询问你背包在我出现之前(i-1行)
                    //总容量为totalW时候的你暂存的最优解 dp[i-1][totalW]
                    // 和
                    //总容量为totalW-w的时候你的暂存最优解 dp[i-1][totalW-w]  + v 哪个大
                    //前者大 延用你的结果dp[i-1][totalW] 后者大 把我放进来 dp[i-1][totalW-w]  + v
                }
                else {
                    //当前所剩重量装得下 直接装
                    dp[item][weight]=dp[item-1][weight];
                }
            }

        }

        //打印搜索过程
        for (int[] rows : dp) {
            for (int col : rows) {
                System.out.format("%5d", col);
            }
            System.out.println();
        }

        return dp[N][totalWeight];
    }
}
