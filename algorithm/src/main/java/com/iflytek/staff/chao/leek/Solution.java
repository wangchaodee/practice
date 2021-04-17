package com.iflytek.staff.chao.leek;

import java.util.Stack;

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}


public class Solution {


    public int[] reversePrint(ListNode head) {
        Stack<Integer> stack = new Stack<>();

        int index = 0;
        while (head != null) {
            index++;
            stack.add(head.val);
            head = head.next;
        }
        int[] ret = new int[index];
        index = 0;
        while (!stack.empty()) {
            ret[index++] = stack.peek();
        }
        return ret;
    }

    //
    public int deleteArray(int[] nums) {
        //0123456
        int flag = -1;
        int cycle = 0;

        int divide = 1;
        int delete = 0;
        int index = 0;
        while (delete < nums.length - 1) {
            divide *= 2;
            for (int i = 0; i < nums.length; i++) {
                if ((i % divide) == cycle) {
                    nums[i] = -1;
                    delete++;
                }
                index = i;
            }
            cycle++;
        }
        System.out.println("index:" + index);

        return nums[index];

    }

    public static void main(String[] args) {
        int[] array = new int[8];

        Solution s = new Solution();

        System.out.println(s.deleteArray(array));
    }


//
//
//
//    public int thirdMax(int[] nums) {
//
//        int t =0 ;
//        for(int i =nums.length-1 ; i>1 ;i--){
//            for(int j= 0 ;j<i ;j++){
//                if(nums[j] <nums[j+1]){
//                    t = nums[j+1];
//                    nums[j+1] = nums[j];
//                    nums[j] =t;
//                }
//            }
//        }
//
//
//        if(nums.length <3){
//            return nums[0];
//        }
//        else{
//            return  nums[2];
//        }
//    }
//
//
//
//
//    public int longestSubarray(int[] nums, int limit) {
//        int l = 0 ;
//        int r = 1 ;
//        int max= 1 ;
//        TreeMap<Integer,Integer> map = new TreeMap();
//        while(r<nums.length){
//            map.put(nums[r],map.getOrDefault(nums[r],0)+1);
//            while( map.lastKey()-map.firstKey()>limit  ){
//                map.put(nums[l],map.get(nums[l])-1);
//                if(map.get(nums[l])==0){
//                    map.remove(nums[l]);
//                }
//                l++;
//            }
//            max = Math.max(max,r-l+1);
//            r++ ;
//        }
//        return max ;
//    }
//
//    public int countSegments(String s) {
//
//        String[] strs=  s.split(" ");
//        int n = strs.length ;
//        for(String str : strs){
//            if(str.length()==0){
//                n--;
//            }
//        }
//        return n;
//    }
//
//    public double averageWaitingTime(int[][] customers) {
//        int n = customers.length;
//        if(n<1){
//            return 0;
//        }
//        int time =0 ;
//        int wait = 0;
//        for(int i=0 ;i<n; i++){
//            if(time < customers[i][0]){
//                time = customers[i][0];
//            }
//            time += customers[i][1];
//            wait += time - customers[i][0] ;
//        }
//        return  wait/n ;
//    }
//
//
//    public int repeatedNTimes(int[] A) {
//        Map<Integer,Integer> map = new HashMap();
//        int n = A.length /2 +1 ;
//
//        for(int i = 0 ;i< n ;i++){
//            if(map.getOrDefault(A[i],0)==1){
//                return A[i] ;
//            }
//            map.put(A[i],1);
//        }
//        return A[n];
//    }


}