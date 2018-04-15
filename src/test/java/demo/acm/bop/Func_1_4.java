package demo.acm.bop;

import java.util.Arrays;
  
/* 
动态规划： 
（X1,X2,X3,X4,X5）代表购买每卷的个数，F（X1，X2，X3，X4，X5）代表最低价格，X1 < X2 < X3 < X4 < X5。 
F（X1，X2，X3，X4，X5）=0，当所有参数都为0的情况（这也是退出递归的出口）。 
F（X1，X2，X3，X4，X5）= min{ 
                         5*8*(1-25%) +F（X1-1，X2-1，X3-1，X4-1，X5-1） //参数全部  > 0 
                         4*8*(1-20%) +F（X1，X2-1，X3-1，X4-1，X5-1）   //x2 > 0 
                         3*8*(1-10%) +F（X1，X2，X3-1，X4-1，X5-1）     //x3 > 0 
                         2*8*(1-5%) +F（X1，X2，X3，X4-1，X5-1）        //x4 > 0 
                         8 +F（X1，X2，X3，X4，X5-1）                   //x5 > 0 
                         } 
*/  
public class Func_1_4 {  
    int larg;  
  
    public Func_1_4(int larg) {  
        this.larg = larg;  
    }  
    /* 
    求5个数中的最小值 
     */  
    double min(double y1, double y2, double y3, double y4, double y5) {  
        double tiny = 1000.0;  
        double []y = {y1, y2, y3, y4, y5};  
        for (double i : y) {  
            if (i < tiny) {  
                tiny = i;  
            }  
        }  
        return tiny;  
    }  
    /* 
    思路： 
    5卷书，每一本的价格都相同，找数量最少的那本，根据这本书确定min中都放入哪些情形 
     */  
    double dp(int x1, int x2, int x3, int x4, int x5) {  
        int []x = {x1, x2, x3, x4, x5};  
        Arrays.sort(x); // 对Xn排序  
        if (x[0] > 0) {  
            //全部大于0  
            return min(5*8*0.75 + dp(x[0] - 1, x[1] - 1, x[2] - 1, x[3] - 1, x[4] - 1),  
                    4*8*0.80 + dp(x[0], x[1] - 1, x[2] - 1, x[3] - 1, x[4] - 1),  
                    3*8*0.90 + dp(x[0], x[1], x[2] - 1, x[3] - 1, x[4] - 1),  
                    2*8*0.95 + dp(x[0], x[1], x[2], x[3] - 1, x[4] - 1),  
                    8 + dp(x[0], x[1], x[2], x[3], x[4] - 1));  
        } else if (x[0] == 0 && x[1] > 0) {  
            //4个大于0  
            return min(this.larg,  
                    4*8*0.80 + dp(x[0], x[1] - 1, x[2] - 1, x[3] - 1, x[4] - 1),  
                    3*8*0.90 + dp(x[0], x[1], x[2] - 1, x[3] - 1, x[4] - 1),  
                    2*8*0.95 + dp(x[0], x[1], x[2], x[3] - 1, x[4] - 1),  
                    8 + dp(x[0], x[1], x[2], x[3], x[4] - 1));  
        } else if (x[0] == 0 && x[1] == 0 && x[2] > 0) {  
            //3个大于0  
            return min(this.larg,  
                    this.larg,  
                    3*8*0.90 + dp(x[0], x[1], x[2] - 1, x[3] - 1, x[4] - 1),  
                    2*8*0.95 + dp(x[0], x[1], x[2], x[3] - 1, x[4] - 1),  
                    8 + dp(x[0], x[1], x[2], x[3], x[4] - 1));  
        } else if (x[0] == 0 && x[1] == 0 && x[2] == 0 && x[3] > 0) {  
            //2个大于0  
            return min(this.larg,  
                    this.larg,  
                    this.larg,  
                    2*8*0.95 + dp(x[0], x[1], x[2], x[3] - 1, x[4] - 1),  
                    8 + dp(x[0], x[1], x[2], x[3], x[4] - 1));  
        } else if (x[0] == 0 && x[1] == 0 && x[2] == 0 && x[3] == 0 && x[4] > 0) {  
            //1个大于0  
            return min(this.larg,  
                    this.larg,  
                    this.larg,  
                    this.larg,  
                    8 + dp(x[0], x[1], x[2], x[3], x[4] - 1));  
        } else {  
            // 购物车为0  
            return 0;  
        }  
    }  
  
    public static void main(String []args) {  
        Func_1_4 f = new Func_1_4(1000);  
        double tiny_charge = f.dp(2,2,2,1,1);  
        System.out.println("tiny_charge: " + tiny_charge);  
    }  
  
}