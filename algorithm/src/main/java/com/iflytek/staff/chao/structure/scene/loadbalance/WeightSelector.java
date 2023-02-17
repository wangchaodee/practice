package com.iflytek.staff.chao.structure.scene.loadbalance;

import com.iflytek.staff.chao.util.NumberUtil;

import java.util.List;
import java.util.Random;

/**
 * @author : wangchaodee
 * @Description: 增加加权过滤
 * @date Date : 2023年02月16日 21:29
 */
public abstract class WeightSelector implements Selector {

    // 前缀和 数组
    private int[] pre;
    // 总数
    private int total;

    protected int cur;

    /**
     *   选择器如果直接选择个Server 返回也是可以的
     *
     *   如果后续不用到server  , 那么直接给个array 是否更合适，
     *
     * @param serverList
     */
    public void registerServerList(List<Server> serverList) {
        int N = serverList.size();
        int[] array = new int[N];
        for (int i = 1; i < N; i++) {
            array[i] = serverList.get(i).getMaxConnect();
        }
        int gcd = getGcd(array);
        array = processing(array, gcd);
        pre = new int[N];
        pre[0] = array[0];
        for (int i = 1; i < N; i++) {
            pre[i] = array[i] + pre[i - 1];
        }
        total = pre[N - 1] +1 ;
        cur = 0 ;
    }

    private int[] processing(int[] array, int gcd) {
        if (gcd == 1) return array;
        for (int i = 0; i < array.length; i++) {
            array[i] /= gcd;
        }
        return array;
    }

    private int getGcd(int[] nums) {
        int divi = nums[0];
        for (int i = 1; i < nums.length; i++) {
            divi = NumberUtil.gcd(divi, nums[i]);
            if (divi == 1) break;
        }
        return divi;
    }

    private int binarySearch(int x) {
        int l = 0, h = pre.length - 1;
        while (l < h) {
            int mid = (h + l) >> 1;
            if (pre[mid] < x) {
                l = mid + 1;
            } else {
                h = mid;
            }
        }
        return l;
    }


    public int generateIdx() {
        int idx = binarySearch(generateCur());
        return idx;
    }

    /**
     * 子类复写这个方法就可以
     * @return
     */
    public abstract int generateCur();

    public int getTotal() {
        return total;
    }

}
