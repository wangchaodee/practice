package com.iflytek.staff.chao.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author : hamilton
 * @Description: 数组遍历
 * @date Date : 2022年08月14日 21:48
 */
public class ArrayTraversal {

    /**
     * 统计连续1 的最大数量
     * @param nums
     * @return
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        int n = nums.length ;
        int cnt = 0 ;
        int max = 0 ;
        for (int i = 0; i < n; i++) {
            if(nums[i]==1) {
                cnt++;
            }else {
                if(cnt>max){
                    max=cnt;
                }
                cnt=0 ;
            }
        }
        return max>cnt ? max :cnt;
    }

    /**
     * 提莫攻击  返回总的中毒秒数
     * @param timeSeries
     * @param duration
     * @return
     */
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int count =0 ;
        int next = 0;
        for (int i = 0; i <timeSeries.length ; i++) {
            if(timeSeries[i] > next){
                count +=duration ;
            }else {
                count += (timeSeries[i] + duration -1 ) - next ;
            }
            next = timeSeries[i] + duration -1 ;
        }
        return count ;
    }

    /**
     * 返回第三大的数
     * @param nums
     * @return
     */
    public int thirdMax(int[] nums) {
        int N = nums.length;

        Set<Integer> sets = new HashSet<>();
        PriorityQueue<Integer> pq = new PriorityQueue(3);

        for (int i = 0; i < N; i++) {
            if(sets.contains(nums[i])){
                continue;
            }
            sets.add(nums[i]);
            if (pq.size() < 3) {
                pq.offer(nums[i]);
            } else if( pq.peek() < nums[i]) {
                pq.poll();
                pq.add(nums[i]);
            }
        }

        if (pq.size() == 3) {
            return pq.peek();
        } else {
            int max = 0;
            while (!pq.isEmpty()) {
                max = pq.poll();
            }
            return max;
        }
    }

    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length ;
       return Math.max(nums[n-3]* nums[n-2]* nums[n-1], nums[0]* nums[1]* nums[n-1]) ;
    }

    /**
     * 设置零  将0 所在的行和列都转变为 0
     * @param matrix
     */
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int  n = matrix[0].length;

        boolean[] line = new boolean[m];
        boolean[] row = new boolean[n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(matrix[i][j]==0){
                   line[i]=true;
                   row[j]=true;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            if(line[i]){
                for (int j = 0; j <n ; j++) {
                    matrix[i][j]=0;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if(row[i]){
                for (int j = 0; j <m ; j++) {
                    matrix[j][i]=0;
                }
            }
        }
    }

    /**
     *
     * @param mat
     * @return
     */
    public int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length ;
        int n = mat[0].length;
        int[] ans = new int[m*n];
        int idx = 0 ;
        for (int i = 0; i < m+n-1; i++) {
            if(i%2==0){
                int x = i<m ? i : m-1;
                int y = i<m ? 0 : i-m+1;
                while (x>=0 && y<n){
                    ans[idx++]= mat[x][y];
                    x--;
                    y++;
                }
            }else {
                int x = i<n ? 0 : i-n+1;
                int y = i<n ? i : n-1;
                while (x<m && y>=0){
                    ans[idx++]= mat[x][y];
                    x++;
                    y--;
                }
            }
        }
        return ans;
    }

}
