package com.iflytek.staff.chao.array;

import java.util.Arrays;

/**
 * @author : hamilton
 * @Description: 二分法的一些应用
 * @date Date : 2022年07月12日 21:39
 */
public class BinaryDivide {


    /**
     *  在最多操作maxOperations次数内， 可以将数组 切割后的最小值
     * @param nums
     * @param maxOperations
     * @return
     */
    public int minimumSize(int[] nums, int maxOperations) {
        Arrays.sort(nums);
        int ans = 0, l = 1, r = nums[nums.length - 1], m = 0;

        while (l <= r) {
            m = (r + l) >> 1;
            int cut = 0;
            for (int num : nums) {
                if (num > m) {
                    // 超过的 需要切割，  余数为零的 ，切个 次数少一个，  可以 将num -1 然后进行切分，效果一样
                    cut += num / m;
                    if (num % m == 0) {
                        cut -= 1;
                    }
                    if (cut > maxOperations) {
                        break;
                    }
                }
            }

            if (cut > maxOperations) {
                l = m + 1;
            } else {
                ans = m;
                r = m - 1;
            }
        }

        return ans;
    }

    /**
     * 最小吃的速度 能在h小时内，吃完piles
     * @param piles
     * @param h
     * @return
     */
    public int minEatingSpeed(int[] piles, int h) {
        int ans = 0, l = 1, r = 1000000000, m = 0;

        while (l <= r) {
            m = (r + l) >> 1;
            int cut = 0;
            for (int num : piles) {
                // 切分的次数要加上余数的次数 才是总耗费次数
                cut += num / m;
                if (num % m != 0) {
                    cut += 1;
                }
                if (cut > h) {
                    break;
                }
            }

            if (cut > h) {
                l = m + 1;
            } else {
                ans = m;
                r = m - 1;
            }
        }

        return ans;
    }

    /**
     *  m 个小球  放在n 数组中 ，数组的下表位置 为上升序列，  求最大的间隔
     * @param position
     * @param m
     * @return
     */
    public int maxDistance(int[] position, int m) {
        int N = position.length;
        if(N <m) return -1;
        Arrays.sort(position);
        int l = 1 , r = position[N-1] -position[0] , ans =-1 , mid =0;
        while (l<=r){
            mid= (r+l) >>1 ;
            if(checkDis(mid ,position ,m)){
                ans=mid;
                l=mid+1;
            }else {
                r= mid-1;
            }
        }

        return mid;

    }

    private boolean checkDis(int dis, int[] position, int m) {
        int count =1;
        int pre= position[0];
        for (int i = 1; i < position.length; i++) {
            if(position[i]- pre >= dis){
                count++;
                pre= position[i];
            }
        }
        return count >=m;
    }

    public void sortColors(int[] nums) {
        int N = nums.length ;
        int iNum0 = 0 , iNum1 = 0   ;
        for (int i = 0; i < N; i++) {
            if(nums[i]==1){
                exchange(nums,i,iNum1);
                iNum1++;
            }else if(nums[i]==0){
                exchange(nums,i,iNum0);
                if(iNum0<iNum1){
                    exchange(nums,i,iNum1);
                }
                iNum0++;
                iNum1++;
            }
        }

    }

    public void sortColors2(int[] nums) {
        int N = nums.length ;
        int p=0  ;
        for (int i = 0; i < N; i++) {
            if(nums[i]==0){
                exchange(nums,i,p);
                p++;
            }
        }

        for (int i = p; i < N; i++) {
            if(nums[i]==1){
                exchange(nums,i,p);
                p++;
            }
        }
    }

    protected void exchange(int[] array, int i, int j) {
        if (i == j) return;
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
