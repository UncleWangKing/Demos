package demo.acm;

public class MaximumSubsequenceSum {
    public static void main(String[] args) {
        int[] list = {Integer.MIN_VALUE, Integer.MIN_VALUE, 3};
//        int[] list = {1, 2, -1, 3, -2};
        System.out.println(maxSum(list));
    }
    private static int maxSum(int[] num){
        int result = 0;
        int maxResult = Integer.MIN_VALUE;
        for (int i = 0; i < num.length; i++) {
            result += num[i];
            if (result > maxResult) // 相加之后大于结果值，注意需要考虑全是负数的情况
                maxResult = result;// 比较是否大于最大值maxResult
            else if (result < 0) // 否则如果相加的结果小于0，并且小于maxResult，这个子串的最大值就确定下来了
                    result = 0;//重置
        }
        return maxResult;
    }
}
