package com.iflytek.staff.chao.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author : hamilton
 * @Description: 数组统计
 * @date Date : 2022年08月15日 07:33
 */
public class ArrayCount {

    /**
     * 找出错误的数字
     * @param nums
     * @return
     */
    public int[] findErrorNums(int[] nums) {
        int[] cnt = new int[nums.length+1];
        for (int i = 0; i < nums.length; i++) {
            cnt[nums[i]]++;
        }
        int repeat = 0 ;
        int miss =0 ;
        for (int i = 1; i <= nums.length ; i++) {
            if(cnt[i] == 2) repeat = i;
            if(cnt[i]==0) miss =i ;
        }
        return new int[]{repeat,miss};
    }

    /**
     * 找出所有未出现的数字
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int[] cnt = new int[nums.length+1];
        for (int i = 0; i < nums.length; i++) {
            cnt[nums[i]]++;
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 1; i <= nums.length ; i++) {
            if(cnt[i]==0) ans.add(i) ;
        }
        return ans;
    }

    /**
     * 找出重复的数组
     * @param nums
     * @return
     */
    public List<Integer> findDuplicates(int[] nums) {
        int[] cnt = new int[nums.length+1];
        for (int i = 0; i < nums.length; i++) {
            cnt[nums[i]]++;
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 1; i <= nums.length ; i++) {
            if(cnt[i]==2) ans.add(i) ;
        }
        return ans;
    }

    /**
     * 最小操作次数
     * @param nums
     * @return
     */
    public int minMoves(int[] nums) {
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if(nums[i] > max) max = nums[i];
        }
        int cnt = 0;
        for (int i = 0; i < nums.length; i++) {
            cnt += (nums[i] - max) ;
        }
        return cnt ;
    }

    /**
     * 数组的改变、移动
     * @param nums
     * @return
     */
    public boolean checkPossibility(int[] nums) {
        int  n = nums.length ;
        int cnt =1 ;
        for (int j = 1; j < n; j++) {
            if(nums[j-1] >nums[j]){
                cnt++;
                if(cnt>1) return false;
                if(j>1 && nums[j] < nums[j-2]){
                    nums[j] = nums[j-1];
                }
            }
        }

        return true ;
    }

    /**
     * 平滑处理  九个格的平均值
     * @param img
     * @return
     */
    public int[][] imageSmoother(int[][] img) {
        int m = img.length ;
        int n = img[0].length ;

        int[][] ans = new int[m][n] ;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                int num=0 , sum =0 ;
                for (int k = i-1; k <=i+1 ; k++) {
                    for (int l = j-1; l <=j+1 ; l++) {
                        if(k>=0 && k<m  && l >=0 && l<n) {
                            num++ ;
                            sum += img[k][l];
                        }
                    }
                }
                ans[i][j] =  sum /num ;
            }

        }

        return  ans ;
    }

    /**
     * 范围求和
     * @param m
     * @param n
     * @param ops
     * @return
     */
    public int maxCount(int m, int n, int[][] ops) {
        int a = m , b = n ;
        for (int i = 0; i < ops.length; i++) {
            if(ops[i][0] < a )  a = ops[i][0] ;
            if(ops[i][1] < b ) b = ops[i][1] ;
        }
        return  a * b ;
    }

    /**
     * 统计战舰数量
     * @param board
     * @return
     */
    public int countBattleships(char[][] board) {
        int m = board.length ;
        int n = board[0].length ;
        int ans =0 ;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(board[i][j] =='X'){
                    for (int k = j+1; k <n && board[i][k]=='X'  ; k++) {
                        board[i][k]='.';
                    }

                    for (int k = i+1; k <m && board[k][j]=='X'  ; k++) {
                        board[k][j]='.';
                    }
                    ans++ ;
                }
            }
        }

        return ans ;
    }

    /**
     * 旋转数组
     * @param nums
     * @return
     */
    public int maxRotateFunction(int[] nums) {
        int f =0  , n = nums.length ;
        int sum = Arrays.stream(nums).sum();
        for (int i = 0; i < n; i++) {
            f += i* nums[i] ;
        }
        int ans = f ;
        for (int i = n-1; i >0; i--) {
            f += sum - n*nums[i];
            ans = Math.max(f,ans);
        }
        return ans ;
    }
}
