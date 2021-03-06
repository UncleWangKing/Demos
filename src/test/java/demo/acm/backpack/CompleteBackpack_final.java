package demo.acm.backpack;

public class CompleteBackpack_final {
    public static void main(String[] args) throws Exception {
        int val[] = {40, 10, 30, 50};//物品价值数组
        int wt[] = {5, 4, 6, 3};//物品重量数组
        int totalWeight = 10;//背包可装总重量

        System.out.println(knapsack(val, wt, totalWeight));
    }

    public static int knapsack(int val[], int wt[], int totalWeight) {
        int N = wt.length;
        int[] dp = new int[totalWeight + 1];

        for (int item=1;item<=N;item++){
            //核心
            //恰恰和01相反 就是需要考虑已经放入i后的情况 达到O(VN)
            for (int weight=0;weight<=totalWeight;weight++){
                if (wt[item-1] <= weight){
                    dp[weight]=Math.max(val[item-1]+dp[weight-wt[item-1]], dp[weight]);
                }
                else {
                    dp[weight]=dp[weight];
                }
            }
            //打印搜索过程
            for (int rows : dp)
                System.out.format("%5d", rows);
            System.out.println();
        }

        return dp[totalWeight];
    }
}
