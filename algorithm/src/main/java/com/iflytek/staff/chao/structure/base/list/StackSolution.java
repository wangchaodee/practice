package com.iflytek.staff.chao.structure.base.list;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : hamilton
 * @Description: 栈结构 相关算法
 * @date Date : 2023年01月10日 19:23
 */
public class StackSolution {

    /**
     * 150. 逆波兰表达式求值
     *
     * @param tokens
     * @return
     */
    public int evalRPN(String[] tokens) {

        Stack<Integer> stack = new Stack<>();
        List<String> fuhao = Arrays.asList("+", "-", "*", "/");
        for (int i = 0; i < tokens.length; i++) {
            if (fuhao.contains(tokens[i])) {
                int num2 = stack.pop();
                int num1 = stack.pop();
                switch (tokens[i]){
                    case "+" :
                        stack.push(num1 + num2);
                        break;
                    case "-" :
                        stack.push(num1 - num2);
                        break;
                    case "*":
                        stack.push(num1 * num2);
                        break;
                    case "/":
                        stack.push(num1 / num2);
                        break;
                }

            } else {
                stack.push(Integer.valueOf(tokens[i]));
            }
        }
        return stack.pop();
    }

    String[] tokens ;
    int idx ;
    public int evalRPN2(String[] tokens) {
        this.tokens = tokens ;
        this.idx = tokens.length -1 ;
        return calc();
    }
    private int calc(){
        switch (tokens[idx--]) {
            case "+": {
                int right = calc();
                idx--;
                return calc() + right;
            }
            case "-": {
                int right = calc();
                idx--;
                return calc() - right;
            }
            case "*": {
                int right = calc();
                idx--;
                return calc() * right;
            }
            case "/": {
                int right = calc();
                idx--;
                return calc() / right;
            }

            default:
                return Integer.valueOf(tokens[++idx]);
        }
    }

    public String minRemoveToMakeValid(String s) {
        int N = s.length();
        Set<Integer> indexRemove = new HashSet<>();
        Stack<Integer> openFlag = new Stack<>();
        // 添加无效有括号
        for (int i = 0; i < N; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                openFlag.push(i);
            }
            if (c == ')') {
                if (!openFlag.isEmpty()) {
                    openFlag.pop();
                } else {
                    indexRemove.add(i);
                }
            }
        }
        //添加无效左括号
        while (!openFlag.isEmpty()) {
            indexRemove.add(openFlag.pop());
        }
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < N; i++) {
            if (indexRemove.contains(i)) {
                continue;
            }
            ans.append(s.charAt(i));
        }
        return ans.toString();

    }

    public String decodeString(String s) {
        StringBuilder res = new StringBuilder();
        int k = 0;
        Stack<Integer> kstack = new Stack<>();
        Stack<StringBuilder> sbStack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '[') {
                kstack.push(k);
                sbStack.push(res);
                k = 0;
                res = new StringBuilder();
            } else if (c == ']') {
                StringBuilder temp = new StringBuilder();
                int times = kstack.pop();
                for (int i = 0; i < times; i++) {
                    temp.append(res);
                }
                res = sbStack.pop().append(temp);

            } else if (Character.isDigit(c)) {
                k = 10 * k + c - '0';
            } else {
                res.append(c);
            }

        }
        return res.toString();

    }

    /**
     * 739 数组中元素与下一个比它大的元素之间的距离
     *
     * @param temperatures
     * @return
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int N = temperatures.length;
        int[] ans = new int[N];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int j = stack.pop();
                ans[j] = i - j;
            }
            stack.push(i);
        }
        return ans;
    }

    /**
     * 下一个更大的元素
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] ans = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            int j = 0;
            while (j < nums2.length && nums1[i] != nums2[j]) j++;

            while (j < nums2.length && nums1[i] >= nums2[j]) j++;

            ans[i] = (j == nums2.length ? -1 : nums2[j]);
        }
        return ans;
    }

    /**
     * 循环数组  计算下一个更大元素
     *
     * @param nums
     * @return
     */
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        Stack<Integer> pre = new Stack<>();
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        for (int i = 0; i < n * 2; i++) {
            int j = i % n;
            // 如果当前值大于栈中的值  则更新到ans
            while (!pre.isEmpty() && nums[pre.peek()] < nums[j]) {
                ans[pre.pop()] = nums[j];
            }
            if (i < n) {
                // 放指针
                pre.push(i);
            }
        }

        return ans;
    }


    /**
     * 768. 最多能完成排序的块 II  , arr[i]  的值可以大于i ,也可以重复
     *
     * @param arr
     * @return
     */
    public int maxChunksToSorted2(int[] arr) {
        Stack<Integer> stack = new Stack<>();

        for (int num : arr) {
            if (stack.isEmpty() || num >= stack.peek()) {
                stack.push(num);
            } else {
                int mx = stack.pop();
                while (!stack.isEmpty() && stack.peek() > num) {
                    stack.pop();
                }
                stack.push(mx);
            }
        }
        return stack.size();
    }

    /**
     * 20 用栈实现括号匹配
     *
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack();

        for (char c : s.toCharArray()) {

            if (c == '{' || c == '(' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;
                char p = stack.pop();
                boolean b1 = c == ')' && p != '(';
                boolean b2 = c == '}' && p != '{';
                boolean b3 = c == ']' && p != '[';
                if (b1 || b2 || b3) return false;
            }
        }
        return stack.isEmpty();
    }

}