package demo.acm.backpack;

import com.sun.javafx.scene.control.skin.VirtualFlow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CompleteBackpack_better_split {
    public static void main(String[] args) throws Exception {
        List<Integer> val = Arrays.asList(40, 10, 30, 50);//物品价值数组
        List<Integer> wt = Arrays.asList(5, 4, 6, 3);//物品重量数组
        int totalWeight = 10;//背包可装总重量

        List<Integer> val_new = new ArrayList<>();
        List<Integer> wt_new = new ArrayList<>();
        // 2^n 来组合成可用的数量 转化为01背包
        for (int i = 0; i < wt.size(); i++) {
            int totalForCurrent = totalWeight / wt.get(i);
            System.out.print(totalForCurrent + " ");
            for (int j = 0; Math.pow(2, j) <= totalForCurrent; j++) {
                val_new.add((int) Math.pow(2, j) * val.get(i));
                wt_new.add((int) Math.pow(2, j) * wt.get(i));
            }
        }
        System.out.println();
        print(val_new);
        print(wt_new);


        System.out.println(knapsack(val_new, wt_new, totalWeight));
    }

    public static void print(List<Integer> list){
        list.forEach(t-> System.out.print(t+" "));
        System.out.println();
    }

    public static int knapsack(List<Integer> val, List<Integer> wt, int totalWeight) {
        int N = wt.size();
        int[] dp = new int[totalWeight + 1];

        for (int item=1;item<=N;item++){
            //核心
            //依赖数组左方的数据做判断 所以倒过来 不然赋值过程中 左方的数据丢失了
            for (int weight=0;weight<=totalWeight;weight++){
                if (wt.get(item-1) <= weight){
                    dp[weight]=Math.max(val.get(item-1)+dp[weight-wt.get(item-1)], dp[weight]);
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
