package com.iflytek.staff.chao.algorithm.base;

import com.iflytek.staff.chao.util.DirectionUtil;

import java.util.*;

/**
 * @author : hamilton
 * @Description: 回溯算法  Backtracking（回溯）属于 DFS
 * 普通 DFS 主要用在 可达性问题 ，这种问题只需要执行到特点的位置然后返回即可。
 * 而 Backtracking 主要用于求解 排列组合 问题，例如有 { 'a','b','c' } 三个字符，求解所有由这三个字符排列得到的字符串，这种问题在执行到特定的位置返回之后还会继续执行求解过程。
 * 注意事项 ：
 * 在访问一个新元素进入新的递归调用时，需要将新元素标记为已经访问，这样才能在继续递归调用时不用重复访问该元素；
 * 但是在递归返回时，需要将元素标记为未访问，因为只需要保证在一个递归链中不同时访问一个元素，可以访问已经访问过但是不在当前递归链中的元素。
 * @date Date : 2022年07月19日 17:05
 */
public class BackTracking {


//    // x ,y  点 顺序  上 右 下 左
//    int[] x = new int[]{0, 1, 0, -1};
//    int[] y = new int[]{1, 0, -1, 0};

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        //有重复时 ，要先排序
        Arrays.sort(nums);
        dfsRecall2(nums, 0, true, path, nums.length, ans);
        return ans;
    }

    /**
     * 元素无重复的
     *
     * @param nums
     * @param i
     * @param path
     * @param target
     * @param ans
     */
    private void dfsRecall(int[] nums, int i, Deque<Integer> path, int target, List<List<Integer>> ans) {
        if (i == target) {
            ans.add(new ArrayList<>(path));
            return;
        }
        path.addLast(nums[i]);
        dfsRecall(nums, i + 1, path, target, ans);
        path.removeLast();
        dfsRecall(nums, i + 1, path, target, ans);
    }

    /**
     * 元素有重复的
     *
     * @param nums
     * @param i
     * @param path
     * @param target
     * @param ans
     */
    private void dfsRecall2(int[] nums, int i, boolean add, Deque<Integer> path, int target, List<List<Integer>> ans) {
        if (i == target) {
            ans.add(new ArrayList<>(path));
            return;
        }
        // 前面有重复的， 且前面没放进去 , 此时只能执行不放进去的调用
        dfsRecall2(nums, i + 1, false, path, target, ans);
        if (i > 0 && nums[i] == nums[i - 1] && !add) {
            return;
        }
        path.addLast(nums[i]);
        dfsRecall2(nums, i + 1, true, path, target, ans);
        path.removeLast();
    }

    /**
     * 回溯 排列
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteCombine(int[] nums) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }
        Arrays.sort(nums);
        Deque<Integer> path = new LinkedList<>();
        boolean[] used = new boolean[len];

        dfs(nums, len, 0, path, used, res);
        return res;
    }

    private void dfs(int[] nums, int len, int depth, Deque<Integer> path, boolean[] used, List<List<Integer>> res) {
        if (len == depth) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < len; i++) {
            if (used[i]) {
                continue;
            }
            path.addLast(nums[i]);
            used[i] = true;
            dfs(nums, len, depth + 1, path, used, res);
            path.removeLast();
            used[i] = false;
        }
        return;
    }

    /**
     * 回溯 全排列  无重复元素
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteOrder(int[] nums) {
        int n = nums.length;

        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        for (int num : nums) {
            path.add(num);
        }

        backtrack(path, n, ans, 0);

        return ans;
    }

    private void backtrack(List<Integer> path, int n, List<List<Integer>> ans, int index) {
        if (index == n) {
            ans.add(new ArrayList<>(path));
            return;
        }

        for (int j = index; j < n; j++) {
            Collections.swap(path, index, j);
            backtrack(path, n, ans, index + 1);
            Collections.swap(path, index, j);
        }
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        int n = nums.length;

        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        Arrays.sort(nums);
        for (int num : nums) {
            path.add(num);
        }

        backtrackUnique(path, n, ans, 0);

        return ans;
    }

    private void backtrackUnique(List<Integer> path, int n, List<List<Integer>> ans, int index) {
        if (index == n) {
            ans.add(new ArrayList<>(path));
            return;
        }

        for (int j = index; j < n; j++) {
            if (path.get(index) == path.get(j)) {
                index++;
                continue;
            }
            Collections.swap(path, index, j);
            backtrack(path, n, ans, index + 1);
            Collections.swap(path, index, j);
        }
    }


    /**
     * 回溯 组合
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute3(int[] nums) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }

        Deque<Integer> path = new LinkedList<>();
        boolean[] used = new boolean[len];

        dfsUnique(nums, len, 0, path, used, res);
        return res;
    }

    private void dfsUnique(int[] nums, int len, int depth, Deque<Integer> path, boolean[] used, List<List<Integer>> res) {
        if (len == depth) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < len; i++) {
            // !used[i-1]   和 used[i-1]   结果上都可以， 表达的意思不一样
            //  重复数时  前面的没用  后面的不先用  ，
//            if (used[i]  || (i>0 && !used[i-1] && nums[i]==nums[i-1] )) {
//                continue;
//            }
            //  后面的先被用
            if (used[i] || (i > 0 && used[i - 1] && nums[i] == nums[i - 1])) {
                continue;
            }
            path.addLast(nums[i]);
            used[i] = true;
            dfsUnique(nums, len, depth + 1, path, used, res);
            path.removeLast();
            used[i] = false;
        }
        return;
    }

    public List<List<Integer>> combine(int n, int k) {

        List<List<Integer>> ans = new ArrayList<>();
        if (k <= 0 || n < k) {
            return ans;
        }

        Deque<Integer> path = new ArrayDeque<>();
        dst(1, n, k, path, ans);

        return ans;
    }

    private void dst(int i, int n, int k, Deque<Integer> path, List<List<Integer>> ans) {
        if (path.size() == k) {
            ans.add(new ArrayList<>(path));
        }

        for (int j = i; j <= n; j++) {
            path.addLast(j);
            dst(i + 1, n, k, path, ans);
            path.removeLast();
        }
    }


    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();

        Deque<Integer> path = new ArrayDeque<>();
        dfs(candidates, 0, target, path, ans);

        return ans;
    }

    private void dfs(int[] candidates, int i, int target, Deque<Integer> path, List<List<Integer>> ans) {
        if (target == 0) {
            ans.add(new ArrayList<>(path));
            return;
        }

        for (int j = i; j < candidates.length; j++) {
            // 运行重复用
            if (target >= candidates[j]) {
                path.addLast(candidates[j]);
                dfs(candidates, j, target - candidates[j], path, ans);
                path.removeLast();
            }
        }
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();

        Deque<Integer> path = new ArrayDeque<>();

        Arrays.sort(candidates);
        dfs2(candidates, 0, target, path, ans);

        return ans;
    }

    private void dfs2(int[] candidates, int i, int target, Deque<Integer> path, List<List<Integer>> ans) {

        if (target == 0) {
            ans.add(new ArrayList<>(path));
            return;
        } else if (target < 0) {
            return;
        }
        if (i == candidates.length) return;

        for (int j = i; j < candidates.length; j++) {
            while (j > i && candidates[j] == candidates[j - 1]) {
                j++;
            }
            dfs2(candidates, i + 1, target, path, ans);
            path.addLast(candidates[i]);
            dfs2(candidates, i + 1, target - candidates[i], path, ans);
            path.removeLast();
        }
    }

    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        if (digits.length() == 0) {
            return ans;
        }
        List<List<Character>> codes = new ArrayList<>();
        char c = 'a';
        for (int i = 2; i <= 9; i++) {
            int L = 3;
            if (i == 7 || i == 9) {
                L = 4;
            }
            List<Character> code = new ArrayList<>();
            for (int j = 0; j < L; j++) {
                code.add(c++);
            }
            codes.add(code);
        }

        backtrack(codes, new StringBuilder(), 0, digits, ans);
        return ans;
    }

    private void backtrack(List<List<Character>> codes, StringBuilder path, int idx, String digits, List<String> ans) {
        if (idx == digits.length()) {
            ans.add(new String(path));
        }
        int n = digits.charAt(idx) - '2';
        List<Character> codeList = codes.get(n);
        for (Character c : codeList) {
            path.append(c);
            backtrack(codes, path, idx + 1, digits, ans);
            path.deleteCharAt(idx);
        }
    }

    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        backtrack(new StringBuilder(), 0, 0, n, ans);
        return ans;
    }

    private void backtrack(StringBuilder path, int left, int right, int max, List<String> ans) {
        if (left == max && right == max) {
            ans.add(new String(path));
            return;
        }
        if (left < max) {
            path.append("(");
            backtrack(path, left + 1, right, max, ans);
            path.deleteCharAt(left + right);
        }

        if (right < left) {
            path.append(")");
            backtrack(path, left, right + 1, max, ans);
            path.deleteCharAt(left + right);
        }
    }


    /**
     * 79 判断矩阵中的相邻字母是否存在能按顺序组成word ,
     *
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        boolean[][] seen = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (backtrackWord(board, i, j, word, 0, seen)) return true;
            }
        }
        return false;
    }

    private boolean backtrackWord(char[][] board, int r, int l, String word, int idx, boolean[][] seen) {
        if (board[r][l] != word.charAt(idx)) {
            return false;
        } else if (idx == word.length() - 1) {
            return true;
        }

        seen[r][l] = true;

        boolean result = false;

        for (int[] dir : DirectionUtil.directions) {
            int srr = r + dir[0];
            int scc = l + dir[1];
            if (0 <= srr && srr < board.length && 0 <= scc && scc < board[0].length && !seen[srr][scc]) {
                if (backtrackWord(board, srr, scc, word, idx + 1, seen)) {
                    result = true;
                    break;
                }
            }
        }

        seen[r][l] = false;
        return result;
    }


}