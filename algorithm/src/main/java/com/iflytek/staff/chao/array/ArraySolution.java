package com.iflytek.staff.chao.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArraySolution {

    public static void main(String[] args) {


    }


    public void moveZeroes(int[] nums) {
        int l = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[l++] = nums[i];
            }
        }
        while (l < nums.length) {
            nums[l++] = 0;
        }
    }

    /**
     * int[] result =new ArraySolution().plusOne(new int[]{8, 9, 9, 9});
     * for (int e : result) {
     * System.out.print(e);
     * }
     *
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {

        int sum = digits[digits.length - 1] + 1;
        digits[digits.length - 1] = sum % 10;
        int tmp = sum / 10;
        for (int i = digits.length - 2; i >= 0; i--) {
            int t = digits[i] + tmp;
            tmp = t / 10;
            digits[i] = t % 10;
        }
        if (tmp == 1) {
            int[] digits2 = new int[digits.length + 1];
            digits2[0] = 1;
            return digits2;
        } else {
            return digits;
        }

    }

    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int l = 0, r = 0;

        List<Integer> list = new ArrayList<>();
        while (l < nums1.length && r < nums2.length) {
            if (nums1[l] < nums2[r]) {
                l++;
            } else if (nums1[l] > nums2[r]) {
                r++;
            } else {
                list.add(nums1[l]);
                l++;
                r++;
            }
        }
        int[] ret = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ret[i] = list.get(i);
        }
        return ret;
    }


    public int singleNumber(int[] nums) {
        int a = nums[0];
        for (int i = 1; i < nums.length; i++) {
            a ^= nums[i];
        }
        return a;
    }


    public int removeDuplicates(int[] nums) {
        int l = 1;
//        int r = 1;
        for (int r = 1; r < nums.length; r++) {
            if (nums[r] != nums[r - 1]) {
                nums[l++] = nums[r];
            }
        }
        return l;
    }



}
