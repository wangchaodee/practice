package com.iflytek.staff.chao.algorithm.base;

import com.iflytek.staff.chao.structure.base.list.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static com.iflytek.staff.chao.algorithm.base.SortUtil.less;

/**
 * @author : hamilton
 * @Description: 双指针算法
 * @date Date : 2023年01月16日 10:32
 */
public class DoublePointer {


    /**
     * 167. Two Sum II - Input array is sorted (Easy)
     *
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum2(int[] numbers, int target) {
        int l = 0, r = numbers.length - 1;
        while (l < r) {
            int sum = numbers[l] + numbers[r];
            if (sum < target) l++;
            else if (sum > target) r--;
            else return new int[]{l, r};
        }
        return null;
    }

    /**
     * 345. 反转字符串中的元音字母
     *
     * @param s
     * @return
     */
    public String reverseVowels(String s) {
//        if(s==null) return null ;

        HashSet<Character> vowels = new HashSet<>(
                Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
        int n = s.length();
        char[] result = new char[n];
        int i = 0, j = n - 1;
        while (i <= j) {
            char ci = s.charAt(i);
            char cj = s.charAt(j);
            if (!vowels.contains(ci)) {
                result[i++] = ci;
            } else if (!vowels.contains(cj)) {
                result[j--] = cj;
            } else {
                result[i++] = cj;
                result[j--] = ci;
            }
        }
        return new String(result);
    }

    /**
     * 633 判断c是否是两个数的平方和
     *
     * @param c
     * @return
     */
    public boolean judgeSquareSum(int c) {
        int a = 0;
        int b = (int) Math.sqrt(c);
        while (a < b) {
            if (a * a + b * b == c) {
                return true;
            } else if (a * a + b * b > c) {
                b--;
            } else {
                a++;
            }
        }
        return false;
    }

    public boolean isPalindrome(String s) {
        int n = s.length();
        int l = 0, r = n - 1;
        while (l < r) {
            while (l < r && !Character.isLetterOrDigit(s.charAt(l))) l++;
            while (l < r && !Character.isLetterOrDigit(s.charAt(r))) r--;
            if (Character.toLowerCase(s.charAt(l)) != Character.toLowerCase(s.charAt(r))) {
                return false;
            } else {
                l++;
                r--;
            }
        }
        return true;
    }

    /**
     * 680. 验证回文串 II
     *
     * @param s
     * @return
     */
    public boolean validPalindrome(String s) {
        int n = s.length();
        for (int l = 0, r = n - 1; l < r; l++, r--) {
            char cl = s.charAt(l);
            char cj = s.charAt(r);
            if (cl != cj) {
                return validPalindrome(s, l, r - 1) || validPalindrome(s, l + 1, r);
            }
        }
        return true;
    }

    private boolean validPalindrome(String s, int l, int r) {
        int n = s.length();
        while (l < r) {
            char cl = s.charAt(l);
            char cj = s.charAt(r);
            if (cl != cj) {
                return false;
            } else {
                l++;
                r--;
            }
        }
        return true;
    }

    /**
     * 88. Merge Sorted Array (Easy)
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int N = m + n - 1;
        int n1 = m - 1, n2 = n - 1;
        while (n1 >= 0 && n2 >= 0) {
            if (less(nums1[n1], nums2[n2])) {
                nums1[N--] = nums2[n2--];
            } else {
                nums1[N--] = nums1[n1--];
            }
        }
        while (n2 >= 0) {
            nums1[N--] = nums2[n2--];
        }
    }

    /**
     * 141 判断链表是否存在环
     *
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        if (head == null) return false;
        ListNode l1 = head;
        ListNode l2 = head.next;
        while (l1 != null && l2 != null && l2.next != null) {
            if (l1 == l2) return true;
            l1 = l1.next;
            l2 = l2.next.next;
        }
        return false;
    }

    /**
     * 524. 通过删除字母匹配到字典里最长单词
     * 如果有多个相同长度的结果，返回字典序的最小字符串。  字典序
     *
     * @param s
     * @param dictionary
     * @return
     */
    public String findLongestWord(String s, List<String> dictionary) {
        int max = 0;
        String ans = "";
        for (int i = 0; i < dictionary.size(); i++) {
            String t = dictionary.get(i);
            if (max > t.length() || (max == t.length() && ans.compareTo(t) < 0)) continue;
            if (isSubStr(s, t)) {
                max = t.length();
                ans = t;
            }
        }
        return ans;
    }

    private boolean isSubStr(String s, String t) {
        int sl = s.length(), tl = t.length(), i = 0, j = 0;
        while (i < sl && j < tl) {
            char cl = s.charAt(i);
            char cj = t.charAt(j);
            if (cl == cj) {
                j++;
            }
            i++;
        }
        return j == tl;
    }

    /**
     * 556. 下一个更大元素 III  双指针思想
     * @param n
     * @return
     */
    public int nextGreaterElement(int n) {
        List<Integer> nums = new ArrayList<>();
        while (n != 0 ){
            nums.add(n%10);
            n /=10;
        }
        int s = nums.size() , idx = -1 ;
        int i = 0 ;
        // 寻找第一个低位点
        while (idx==-1 && i<s-1){
            if(nums.get(i) > nums.get(i+1)){
                idx=i+1;
                break;
            }
            i++;
        }
        if(idx==-1) return -1;
        // 将第一个大于idx位置的数值 调换到此位置
        for (int j = 0; j < idx; j++) {
            if(nums.get(j)> nums.get(idx)) {
                SortUtil.exchange(nums,j,idx);
                break;
            }
        }
        // 由于idx 之后 是严格降序的， 翻转  变为能组成的最小值
        for (int l = 0 ,r = idx-1 ; l < r; l++,r--) {
            SortUtil.exchange(nums,l,r);
        }
        long ans = 0 ;
        for (int j = s-1; j >=0; j--) {
            ans = ans*10 + nums.get(j);
        }
        return ans > Integer.MAX_VALUE ?-1 : (int) ans ;
    }

    /**
     * 713. 乘积小于 K 的子数组
     * @param nums
     * @param k
     * @return
     */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int pro = 1;
        int l = 0;
        int count = 0;
        for (int r = 0; r < nums.length; r++) {
            pro *= nums[r];
            while (l <= r && pro >= k) {
                pro /= nums[l++];
            }
            // 以当前元素为结尾的 新增连续子数组个数 是和当前最大区间长度相等的
            count += (r - l + 1);
        }
        return count;
    }
}
