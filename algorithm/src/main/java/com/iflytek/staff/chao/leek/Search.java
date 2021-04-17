package com.iflytek.staff.chao.leek;

public class Search {

    public int search(int[] nums, int target) {
        int N = nums.length;
        int left = 0;
        int right = N - 1;
        while (left <= right) {
            int middle = (left + right) / 2;
            if (nums[middle] == target) {
                return middle;
            }

            if (nums[left] == target) {
                return left;
            }

            if (nums[right] == target) {
                return right;
            }
            if (nums[left] < nums[middle]) {
                if (nums[left] < target && nums[middle] > target) {
                    right = middle - 1;
                } else {
                    left = middle + 1;
                }
            } else {
                if (nums[middle] < target && nums[middle] > target) {
                    left = middle + 1;
                } else {
                    right = middle - 1;
                }
            }
        }
        return -1;

    }
}
