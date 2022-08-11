package com.iflytek.staff.chao.array;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : hamilton
 * @Description: 数学相关
 * @date Date : 2022年07月30日 11:45
 */
public class MathOpt {

    /**
     * 求经过同一条直线 的最大点数
     * @param points
     * @return
     */
    public int maxPoints(int[][] points) {
        int N  = points.length ;
        if(N<=2) return N ;

        int ans = 0 ;

        for (int i = 0; i < N; i++) {
            if(ans >= N-i  || ans > N/2){
                break;
            }
            Map<Integer,Integer> map = new HashMap<>();
            for (int j = i+1; j < N; j++) {
                int x = points[i][0] - points[j][0];
                int y = points[i][1] - points[j][1];
                if(x==0){
                    y=1;
                }else if(y==0){
                    x=1;
                }else {
                    if(y<0){
                        x=-x;
                        y=-y;
                    }
                    int xy=  gcd(y,Math.abs(x));
                    x /=xy;
                    y /=xy;
                    int key = y+ x*20001;
                    map.put(key,map.getOrDefault(key,0)+1);
                }
            }
            int max =0 ;
            for (Map.Entry<Integer,Integer> entry : map.entrySet()){
                max= Math.max(max,entry.getValue()+1);
            }

            ans = Math.max(ans,max);
        }

        return ans;
    }


    private int gcd(int a , int b){
        return b==0 ?  a : gcd(b,a%b);
    }

    /**
     * 判断是否单调数列
     * @param nums
     * @return
     */
    public boolean isMonotonic(int[] nums) {
        boolean inc = true , decr = true ;
        for (int i = 1; i < nums.length; i++) {
            if(nums[i] > nums[i-1]){
                inc = false;
            }
            if (nums[i] < nums[i-1]) {
                decr =false;
            }
        }

        return inc|| decr;
    }


    /**
     * 算阶乘后 尾数为0的个数
     * @param n
     * @return
     */
    public int trailingZeroes(int n) {
        int count =0;
        while (n/5 >0){
            count += n/5 ;
        }
        return count ;
    }

    public int[] productExceptSelf(int[] nums) {
        int N = nums.length ;
        int[] result = new int[N] ;
        int tmp =1 ;
        for (int i = 0; i < N; i++) {
            result[i] *=tmp;
            tmp *=nums[i];
        }
        tmp=1;
        for (int i = N-1; i >=0  ; i--) {
            result[i] *= tmp ;
            tmp *= nums[i];
        }
        return  result ;
    }
}
