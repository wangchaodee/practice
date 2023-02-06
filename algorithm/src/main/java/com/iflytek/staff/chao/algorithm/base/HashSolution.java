package com.iflytek.staff.chao.algorithm.base;

import java.util.*;

/**
 * @author : hamilton
 * @Description: 哈希表题解
 * @date Date : 2023年01月10日 20:51
 */
public class HashSolution {
    // 定长场景   如 26个字母   或有限范围整数值  可以直接用 数组标记
    // HashSet
    // HashMap    预设容量 可以减少扩容的性能消耗

    /**
     * 查找是否存在两数之和等于target
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) return new int[]{i, j};
            }
        }
        return new int[2];
    }


    public int[] twoSum3(int[] nums, int target) {
        HashMap<Integer, Integer> indexForNum = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (indexForNum.containsKey(target - nums[i])) {
                return new int[]{indexForNum.get(target - nums[i]), i};
            } else {
                indexForNum.put(nums[i], i);
            }
        }
        return null;
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

    /**
     * 判断是否存在重复数值
     *
     * @param nums
     * @return
     */
    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) return true;
        }
        return false;
    }

    public boolean containsDuplicate2(int[] nums) {
        return Arrays.stream(nums).distinct().count() < nums.length;
    }

    /**
     * 594. 最长和谐子序列
     *
     * @param nums
     * @return
     */
    public int findLHS(int[] nums) {
        Map<Integer, Integer> countNum = new HashMap<>();
        for (int num : nums) {
            countNum.put(num, countNum.getOrDefault(num, 0) + 1);
        }
        int longest = 0;
        for (int num : countNum.keySet()) {
            if (countNum.containsKey(num + 1)) {
                longest = Math.max(longest, countNum.get(num) + countNum.get(num + 1));
            }
        }
        return longest;
    }

    /**
     * 128. 最长连续序列
     */
    public int longestConsecutive(int[] nums) {

        if (nums.length <= 1) return nums.length;

        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int num : nums) {
            treeSet.add(num);
        }

        int longest = 0;
        int pre = treeSet.pollFirst();
        int preCnt = 1;
        while (!treeSet.isEmpty()) {
            int num = treeSet.pollFirst();
            if (num == pre + 1) {
                preCnt++;
                longest = Math.max(longest, preCnt);
            } else {
                preCnt = 1;
            }
            pre = num;
        }
        return longest;
    }

    public int longestConsecutive2(int[] nums) {

        if (nums.length <= 1) return nums.length;

        HashSet<Integer> treeSet = new HashSet<>();
        for (int num : nums) {
            treeSet.add(num);
        }

        int longest = 0;
        for (int num : treeSet) {
            if (!treeSet.contains(num - 1)) {
                int cnt = 1;
                int curNum = num;
                while (treeSet.contains(curNum + 1)) {
                    curNum = curNum + 1;
                    cnt++;
                }
                longest = Math.max(longest, cnt);
            }
        }

        return longest;
    }


    /**
     * 1282. 用户分组  按照对应的groupSize 将元素分组
     *
     * @param groupSizes
     * @return
     */
    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        List<List<Integer>> ans = new ArrayList<>();

        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < groupSizes.length; i++) {
            List<Integer> cur = map.getOrDefault(groupSizes[i], new ArrayList<>());
            cur.add(i);
            if (cur.size() == groupSizes[i]) {
                ans.add(cur);
                map.put(groupSizes[i], new ArrayList<>());
            } else {
                map.put(groupSizes[i], cur);
            }
        }
        return ans;
    }

    public int repeatedNTimes(int[] A) {
        Map<Integer, Integer> map = new HashMap();
        int n = A.length / 2 + 1;

        for (int i = 0; i < n; i++) {
            if (map.getOrDefault(A[i], 0) == 1) {
                return A[i];
            }
            map.put(A[i], 1);
        }
        return A[n];
    }


    public int[] arrayRankTransform(int[] arr) {
        Map<Integer, Integer> arrIndex = new HashMap<>();
        int[] arrCopy = arr.clone();
        Arrays.sort(arrCopy);
        int index = 1;
        arrIndex.put(arrCopy[0], 1);
        for (int i = 1; i < arrCopy.length; i++) {
            if (arrCopy[i] == arrCopy[i - 1]) {
                continue;
            }
            arrIndex.put(arrCopy[i], ++index);
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = arrIndex.get(arr[i]);
        }
        return arr;
    }

}