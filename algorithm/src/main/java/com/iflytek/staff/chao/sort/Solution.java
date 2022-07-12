package com.iflytek.staff.chao.sort;

import java.util.*;

/**
 * @author : hamilton
 * @Description: TODO
 * @date Date : 2022年06月21日 下午9:49
 */
public class Solution {

    public int[] sortedSquares(int[] nums) {

        int l = 0;
        int r = nums.length - 1;
        int[] result = new int[nums.length];
        for (int k = nums.length - 1; k >= 0; k--) {
            int ll = nums[l] * nums[l];
            int rr = nums[r] * nums[r];
            if (less(ll, rr)) {
                result[k] = rr;
                r--;
            } else {
                result[k] = ll;
                l--;
            }

        }
        return result;
    }

    public int[] sortedSquares2(int[] nums) {


        int N = nums.length;
        for (int i = 0; i < N; i++) {
            nums[i] = nums[i] * nums[i];
        }

        for (int i = 1; i < N; i++) {
            for (int j = i; j > 0; j--) {
                if (less(nums[j], nums[j - 1])) {
                    exchange(nums, j, j - 1);
                }
            }
        }

        return nums;
    }



    public void moveZeroes(int[] nums) {
        int N = nums.length;
        int l = 0;
        for (int i = 0; i < N; i++) {
            if (nums[i] != 0) {
                exchange(nums, l, i);
                ++l;
            }
        }
    }


    public int[] twoSum(int[] numbers, int target) {
        int l = 0, r = numbers.length - 1;
        while (l < r) {
            int sum = numbers[l] + numbers[r];
            if (sum < target) l++;
            else if (sum > target) r--;
            else return new int[]{l + 1, r + 1};
        }
        return null;
    }



    protected boolean less(int l, int r) {
        return l < r;
    }

    protected void exchange(int[] array, int i, int j) {
        if (i == j) return;
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public int search2(int[] nums, int target) {
        int lo = 0, hi = nums.length - 1;
        while (lo <= hi) {
            int mid = (hi - lo) / 2 + lo;
            if (nums[mid] < target) {
                lo = mid + 1;
            } else if (nums[mid] > target) {
                hi = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public int search(int[] nums, int target) {
        return binarySearch(nums, target, 0, nums.length - 1);
    }

    public int binarySearch(int[] nums, int target, int lo, int hi) {
        if (hi - lo < 0) return -1;
        int mid = (hi - lo) / 2 + lo;
        if (nums[mid] < target) {
            return binarySearch(nums, target, mid + 1, hi);
        } else if (nums[mid] > target) {
            return binarySearch(nums, target, lo, mid - 1);
        } else {
            return mid;
        }
    }

    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    public int thirdMax(int[] nums) {

        for (int i = 1; i < nums.length; i--) {
            for (int j = i; j > 0; j--) {
                if (less(nums[j], nums[j - 1])) {
                    exchange(nums, j, j - 1);
                }
            }
        }

        int N = nums.length;
        if (N < 3) return nums[N - 1];
        int diff = 1;
        for (int j = N - 2; j > 0; j--) {
            if (less(nums[j], nums[j + 1])) ++diff;
            if (diff == 3) return nums[j];
        }
        return nums[N - 1];

    }

    public int missingNumber(int[] nums) {
        int v = 0;
        for (int i = 0; i < nums.length; i++) {
            v += nums[i] - i;
        }
        return v;
    }

    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> sames = new HashSet<>();
        for (int i = 0; i < nums1.length; i++) {

            for (int j = 0; j < nums2.length; j++) {
                if (nums1[i] == nums2[j]) {
                    sames.add(nums1[i]);
                }
            }
        }
        int[] ret = new int[sames.size()];
        Iterator<Integer> iterator = sames.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            ret[i++] = iterator.next();
        }

        return ret;
    }

    public int[] getLeastNumbers2(int[] arr, int k) {

        int[] least = new int[k];
        if (k <= 0) return least;
        int N = arr.length;
        PriorityQueue<Integer> pq = new PriorityQueue<>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });

        for (int i = 0; i < k; i++) {
            pq.add(arr[i]);
        }

        for (int i = k; i < N; i++) {
            if (less(arr[i], pq.peek())) {
                pq.poll();
                pq.offer(arr[i]);
            }
        }


        for (int i = 0; i < k; i++) {
            least[i] = pq.poll();
        }
        return least;
    }

    public int[] getLeastNumbers(int[] arr, int k) {
        int N = arr.length;
        int[] least = new int[k];
        for (int i = 0; i < k; i++) {
            int min = i;
            for (int j = i; j < N; j++) {
                if (less(arr[j], arr[min])) min = j;
            }
            exchange(arr, i, min);
            least[i] = arr[i];
        }
        return least;
    }


    public int[] twoSumNoOrder(int[] numbers, int target) {
        int N = numbers.length;
        Map<Integer, Integer> numOrder = new HashMap<>();
        for (int i = 0; i < N; i++) {
            if (numOrder.containsKey(numbers[i])) {
                int j = numOrder.get(numbers[i]);
                return new int[]{j, i};
            }
            numOrder.put(target - numbers[i], i);
        }
        return null;
    }


}
