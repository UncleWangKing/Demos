package Stuff;


import org.omg.CORBA.OBJECT_NOT_EXIST;

import java.util.Arrays;

/**
* User Tester. 
* 
* @author <Authors name> 
* @since <pre>ʮһ�� 5, 2017</pre> 
* @version 1.0 
*/ 
public class Xianyu {
    private static long totalDistance = Long.MAX_VALUE;
    private static int bestCount = 1;
    //接待处当做不存在 从最左边到最右边的半圆逆时针依次为0-5
    private static int [] bestIndex = {-1,-1,-1,-1,-1,-1};
    private static int [] distanceGrid  = {
                                    0,40,80,100,120,160,
                                    40,0,40,60,80,120,
                                    80,40,0,20,40,80,
                                    100,60,20,0,20,40,
                                    120,80,40,20,0,40,
                                    160,120,80,40,40,0
                                    };
    private static int []timesGrid = {
                                    0,10,10,200,20,0,
                                    10,0,0,0,80,20,
                                    40,0,0,0,0,0,
                                    10,40,0,0,10,190,
                                    0,30,50,0,0,10,
                                    10,60,40,60,30,0
    };
    private static long getTotalDistance(int[] array){
        long distance = 0;
        for(int i = 0; i < 6; ++i)
            for(int j = 0; j < 6; ++j){
                if(i!=j){
                    long tempTimes = timesGrid[i * 6 + j] ;
                    long tempDistance = getDistanceByPos(array, i, j);
                    if(tempDistance > 70)
                        tempDistance  = 70;
                    distance += tempTimes * tempDistance;
//                    System.out.println(i + " 到 "  + j + "总距离为=" + tempTimes + "*" + tempDistance + "=" + tempTimes * tempDistance);
                }
            }
        return distance;
    }
    private static long getDistanceByPos(int[] array, int i, int j){
//        printList(array);
//        System.out.println(i + " 到 "  + j);
        boolean begin = false;
        long distance = 0;
        for (int k = 0; k < array.length; k++) {
            if(array[k] == i || array[k] == j)
                begin = !begin;
            if(begin) {
//                array[k] 到 array[k+1]
                distance += distanceGrid[array[k] * 6 + array[k + 1]];
//                System.out.println("distance from " + i + " to " + j + " = " + array[k] + "+" + array[k+1] + "=" + distanceGrid[array[k] * 6 + array[k + 1]]);
            }
        }

        return distance;
    }
    private static void printList(int[] array){
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] array=new int[]{0,3,5,2,1,4};
        permute(array,0);
        System.out.println("最少总距离="+totalDistance);
        System.out.print("最优排列为:");
        printList(bestIndex);
    }
    //全排列
    private static void permute(int[] array,int start){
        if(start==array.length){  // 输出
            long distance = getTotalDistance(array);

            if(distance <= totalDistance) {
                System.out.print("第" + bestCount++ +"次最新最优排列为:");
                printList(array);
                System.out.println("总距离 = " + distance);
                bestIndex = array.clone();
                totalDistance = distance;
            }
        }
        else
            for(int i=start;i<array.length;++i){
                swap(array,start,i);  //  交换元素
                permute(array,start+1);  //交换后，再进行全排列算法
                swap(array,start,i);  //还原成原来的数组，便于下一次的全排列
            }
    }

    private static void swap(int[] array,int s,int i){
        int t=array[s];
        array[s]=array[i];
        array[i]=t;
    }
}
