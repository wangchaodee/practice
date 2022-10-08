package com.iflytek.staff.chao.job;

import com.iflytek.staff.chao.list.TreeNode;

import java.util.*;

/**
 * @author : hamilton
 * @Description: 竞赛的算法题
 *
 * @date Date : 2022年08月06日 22:34
 */
public class Competion {

    public List<List<Integer>> mergeSimilarItems(int[][] items1, int[][] items2) {

        Arrays.sort(items1, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] -o2[0];
            }
        });

        Arrays.sort(items2, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] -o2[0];
            }
        });


        int N = items1.length ;
        int M = items2.length ;

        int i = 0 ;
        int j =0 ;

        List<List<Integer>> ans = new ArrayList<>();

        while (i<N && j <M){

            List<Integer> temp = new ArrayList<>();
            if(items1[i][0] == items2[j][0]){
                temp.add(items1[i][0]);
                temp.add(items1[i][1] + items2[j][1]);
                ans.add(temp);
                i++;
                j++;
            }else  if(items1[i][0] < items2[j][0]){
                temp.add(items1[i][0]);
                temp.add(items1[i][1] );
                ans.add(temp);
                i++;
            }else {
                temp.add(items2[j][0]);
                temp.add(items2[j][1]);
                ans.add(temp);
                j++;
            }
        }

        while (i<N){
            List<Integer> temp = new ArrayList<>();
            temp.add(items1[i][0]);
            temp.add(items1[i][1] );
            ans.add(temp);
            i++;
        }

        while (j<M){
            List<Integer> temp = new ArrayList<>();
            temp.add(items2[j][0]);
            temp.add(items2[j][1]);
            ans.add(temp);
            j++;
        }
        return ans ;
    }


    public long countBadPairs(int[] nums) {
        int N = nums.length;
        Map<Integer, Integer>  divideCount = new HashMap<>();
        for (int i = 0; i < N; i++) {
            int num= nums[i] - i;
            divideCount.put(num , divideCount.getOrDefault(num,0)+1);
        }
        int left = N ;
        long total = 0;
        for(Map.Entry<Integer,Integer> entry : divideCount.entrySet()){
            left = left - entry.getValue() ;
            total += entry.getValue() * left ;
        }
        return total ;

    }

    public long taskSchedulerII(int[] tasks, int space) {
        Map<Integer,Long> taskLastIndex = new HashMap<>();
        long day =0 ;
        for (int i = 0; i <tasks.length ; i++) {
            // 如果有同类型的
            if(taskLastIndex.containsKey(tasks[i])){
                long lastIndex = taskLastIndex.get(tasks[i]) ;
                if(day - lastIndex<space){
                    day += space -(day-lastIndex-1);
                }
            }
            taskLastIndex.put(tasks[i],day);
            day++;
        }
        return day;
    }

    public long minimumReplacement(int[] nums) {
        int N = nums.length ;
        if(N==1) return 0 ;
        int last = nums[N-1] ;
        int opt = 0 ;
        for (int i = N-2; i >=0 ; i++) {
            if(nums[i] <= nums[i+1]) {
                continue;
            }
            int d = nums[i] /last ;
            opt +=d ;
            if(nums[i]%last !=0  ){
                last = Math.min(last,nums[i]/(d+1) ) ;
            }
        }
        return opt;
    }

    // 2022 -08-07

    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        List<Integer>[] graph = new List[n] ;
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        // 重新构造边
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        boolean[] seen = new boolean[n];
        // 将限制节点加入 已标记点
        for (int r : restricted){
            seen[r] =true;
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        seen[0] =true;
        int count =0 ;
        while (!queue.isEmpty()){
            int node = queue.poll();
            count++;
            for (int next : graph[node]){
                if(!seen[next]){
                    queue.offer(next);
                    seen[next]=true;
                }
            }
        }
        return count ;
    }

    public int arithmeticTriplets(int[] nums, int diff) {
        int n = nums.length ;
        int count =0 ;
        for (int i = 0; i < n-2; i++) {
            for (int j = i+1; j < n-1; j++) {
                if(nums[j]-nums[i] == diff ) {
                    for (int k = j + 1; k < n; k++) {
                        if (nums[k]-nums[j] == diff){
                            count++;
                        }else if(nums[k]-nums[j] > diff){
                            break;
                        }
                    }
                }else if(nums[j]-nums[i] > diff){
                    break;
                }
            }
        }
        return count ;
    }

    public boolean validPartition(int[] nums) {
        int n = nums.length ;
        int[] div = new int[n-1];
        //计算差值
        for (int i = 1; i <n ; i++) {
            div[i-1] = nums[i]-nums[i-1];
        }

        //
        boolean[] dp = new boolean[n+1];
        int pre_pre = 0 ;
        int pre = 0 ;
        int count =0 ;
        for (int i = 0; i <n-1 ; i++) {
            if(div[i] == 0){
                count=0;
            }else if(div[i] ==1 ){
                if(pre>1) return false;
                count++;
            }else {
                if(pre!=0) return false;
            }
            pre_pre=pre;
            pre=div[i];
        }
        if(pre==1) {
            if(pre_pre!=1) return false;
            if(count%3 !=0 )return false;
        }
        return true;
    }

    public boolean checkXMatrix(int[][] grid) {
        int n = grid.length ;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(i==j || i+j==n-1){
                    if(grid[i][j] ==0 ) return false ;
                }else {
                    if(grid[i][j] !=0 ) return false ;
                }
            }
        }
        return true;
    }

    public int smallestEvenMultiple(int n) {
        if(n%2==0) return n ;
        return 2*n ;
    }

    public int longestContinuousSubstring(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length ;
        int max = 1, cur=1 ;
        for (int i = 1; i < n; i++) {
            if(chars[i] == chars[i-1] +1 ){
                cur++;
                max = Math.max(max,cur);
            }else {
                cur=1 ;
            }
        }
        return max;
    }

    /**
     * 翻转基数层的数值  ， 层遍历用队列  翻转用栈
     * @param root
     * @return
     */
    public TreeNode reverseOddLevels(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        Stack<Integer> stack = new Stack<>();
        queue.offer(root);
        int level =0 ;
        while (!queue.isEmpty()){
            int size = queue.size() ;
            for (int i = 0; i <size; i++) {
                TreeNode node = queue.poll() ;
                if(level%2==0) {
                    if (node.left != null) {
                        queue.offer(node.left);
                        stack.push(node.left.val);
                    }

                    if (node.right != null) {
                        queue.offer(node.right);
                        stack.push(node.right.val);
                    }
                }else {
                    node.val = stack.pop();
                    if (node.left != null) {
                        queue.offer(node.left);
                    }

                    if (node.right != null) {
                        queue.offer(node.right);
                    }
                }
            }

            level++;
        }
        return root;
    }
}
