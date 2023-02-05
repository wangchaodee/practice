package com.iflytek.staff.chao.algorithm.base;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author : hamilton
 * @Description: 数组相关  多个数组之间求交集 排大小 ，合并等
 * @date Date : 2023年01月29日 21:26
 */
public class ArrayMultiple {

    /**
     * 349 两个数组的交集
     * 给定两个数组 nums1 和 nums2 ，返回 它们的交集 。输出结果中的每个元素一定是 唯一 的。我们可以 不考虑输出结果的顺序 。
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        if (n1 > n2) {
            return intersect(nums2, nums1);
        }
        Set<Integer> numCount = new HashSet<>();
        for (int i = 0; i < n1; i++) {
            numCount.add(nums1[i]);
        }

        int[] ans = new int[n1]; //Math.min(n1, n2)  第三行已经判断了
        int k = 0;
        for (int i = 0; i < n2; i++) {
            if (numCount.contains(nums2[i])) {
                numCount.remove(nums2[i]);
                ans[k++] = nums2[i];
            }
        }
        return Arrays.copyOfRange(ans, 0, k);
    }

    public int[] intersect2(int[] nums1, int[] nums2) {
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


    public int maxDistance(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        int i = 0, j = 0;
        int distance = 0;
        while (i < n1 && j < n2) {
            if (nums1[i] <= nums2[j]) {
                distance = Math.max(distance, j - i);
                j++;
            } else {
                i++;
                while (i > j) {
                    j++;
                }
            }
        }

        return distance;
    }


    public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        int N = nums1.length;
        int sum = 0;
        int M = 1000000007;
        int[][] chaArray = new int[N][3];

        // 计算绝对差值  形成数组
        for (int i = 0; i < N; i++) {
            int cha = nums1[i] - nums2[i];
            if (cha < 0) cha = -cha;
            chaArray[i][0] = cha;
            chaArray[i][1] = nums1[i];
            chaArray[i][2] = nums2[i];
        }

        //差值排序
        Arrays.sort(chaArray, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        Arrays.sort(nums1);
        //倒叙 从最大的差值数组中 找能减小的值
        int maxReplace = 0;
        for (int i = N - 1; i >= 0; i--) {
            int num2 = chaArray[i][2];
            int l = BinarySearch.binarySearchLeft(nums1, num2);
            int replaceCha = 0;
            if (l == N) {
                //所有数小于num2
                replaceCha = num2 - nums1[N - 1];
            } else if (l == 0) {
                //所有数大于num2
                replaceCha = nums1[0] - num2;
            } else {
                replaceCha = Math.min(nums1[l] - num2, num2 - nums1[l - 1]);
            }

            maxReplace = Math.max(maxReplace, chaArray[i][0] - replaceCha);
            // 找到最优值
            if (replaceCha == 0) break;
        }
        for (int i = 0; i < N; i++) {
            sum = (sum + chaArray[i][0]) % M;
        }


        return (sum - maxReplace + M) % M;
    }

    /**
     * 1630. 等差子数组
     * @param nums
     * @param l
     * @param r
     * @return
     */
    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        int n = l.length;
        List<Boolean> ans = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int[] arr = Arrays.copyOfRange(nums, l[i], r[i] + 1);
            Arrays.sort(arr);
            int d = arr[1] - arr[0];
            boolean flag = true;
            for (int j = 2; j < arr.length; j++) {
                if (arr[j] - arr[j - 1] != d) {
                    flag = false;
                    break;
                }
            }
            ans.add(flag);
        }
        return ans;
    }


    /**
     * 给你三个整数数组 nums1、nums2 和 nums3 ，请你构造并返回一个 元素各不相同的 数组，且由 至少 在 两个 数组中出现的所有值组成。数组中的元素可以按 任意 顺序排列。
     *
     * @param nums1
     * @param nums2
     * @param nums3
     * @return
     */
    public List<Integer> twoOutOfThree(int[] nums1, int[] nums2, int[] nums3) {
        Set<Integer> dataExist = new HashSet<>();
        Set<Integer> dataExist2 = new HashSet<>();
        Set<Integer> result = new HashSet<>();
        for (Integer num : nums1) {
            dataExist.add(num);
        }
        for (Integer num : nums2) {
            if (dataExist.contains(num)) {
                result.add(num);
            } else {
                dataExist2.add(num);
            }
        }

        for (Integer num : nums3) {
            if (dataExist.contains(num)) {
                result.add(num);
            }
            if (dataExist2.contains(num)) {
                result.add(num);
            }
        }

        return result.stream().collect(Collectors.toList());
    }

}
