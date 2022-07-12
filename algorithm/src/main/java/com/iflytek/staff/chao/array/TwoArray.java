package com.iflytek.staff.chao.array;

import java.util.*;

public class TwoArray {

    /**
     * 合并重叠数组
     *
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][2];
        }

        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        Stack<int[]> outputs = new Stack<>();
        for (int i = 0; i < intervals.length; i++) {
            int[] curr = intervals[i];
            if (outputs.isEmpty()) {
                outputs.add(curr);
            } else {
                int[] last = outputs.peek();
                if (last[1] >= curr[0]) {
                    if (last[1] < curr[1]) last[1] = curr[1];
                } else {
                    outputs.add(curr);
                }
            }
        }
        int[][] result = new int[outputs.size()][2];
        outputs.copyInto(result);
        return result;
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

    public List<List<Integer>> permute(int[] nums) {
        int n = nums.length;

        List<List<Integer>> ans = new ArrayList<>();
        if (n == 0) {
            return ans;
        }

        Deque<Integer> path = new ArrayDeque<>();
        boolean[] used = new boolean[n];
        dst(nums, n, 0, path, used, ans);

        return ans;
    }

    private void dst(int[] nums, int n, int depth, Deque<Integer> path, boolean[] used, List<List<Integer>> ans) {
        if (path.size() == n) {
            ans.add(new ArrayList<>(path));
            return;
        }

        for (int j = 0; j < n; j++) {
            if (used[j]) {
                continue;
            }
            path.addLast(nums[j]);
            used[j] = true;
            dst(nums, n, depth + 1, path, used, ans);
            path.removeLast();
            used[j] = false;
        }
    }

    public List<List<Integer>> permute2(int[] nums) {
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

    public List<String> letterCasePermutation(String s) {
        List<StringBuilder> ans = new ArrayList<>();
        ans.add(new StringBuilder());
        for (char c : s.toCharArray()) {
            int n = ans.size();
            if (Character.isLetter(c)) {
                for (int i = 0; i < n; i++) {
                    ans.add(new StringBuilder(ans.get(i)));
                    ans.get(i).append(Character.toUpperCase(c));
                    ans.get(n + i).append(Character.toLowerCase(c));
                }
            } else {
                for (int i = 0; i < n; i++) {
                    ans.get(i).append(c);
                }
            }
        }

        List<String> stringList
                = new ArrayList<>();
        for (StringBuilder sb : ans) {
            stringList.add(sb.toString());
        }
        return stringList;

    }


    public int firstUniqChar(String s) {
        Map<Character, Integer> position = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!position.containsKey(c)) {
                position.put(c, i);
            } else {
                position.put(c, -1);
            }
        }
        int first = s.length();
        for (Map.Entry<Character, Integer> entry : position.entrySet()) {
            if (entry.getValue() != -1 && entry.getValue() < first) {
                first = entry.getValue();
            }
        }
        if (first == s.length()) {
            first = -1;
        }
        return first;
    }


    public boolean canConstruct(String ransomNote, String magazine) {
        int[] chars = new int[26];
        for (char c : ransomNote.toCharArray()) {
            chars[c - 'a']++;
        }

        for (char c : magazine.toCharArray()) {
            chars[c - 'a']--;
        }

        for (int i = 0; i < 26; i++) {
            if (chars[i] > 0) {
                return false;
            }
        }
        return true;
    }

    public boolean isAnagram(String s, String t) {
        int[] chars = new int[26];
        for (char c : s.toCharArray()) {
            chars[c - 'a']++;
        }

        for (char c : t.toCharArray()) {
            chars[c - 'a']--;
        }

        for (int i = 0; i < 26; i++) {
            if (chars[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public boolean checkIfExist(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] == 2 * arr[j] || 2 * arr[i] == arr[j]) {
                    return true;
                }
            }
        }
        return false;
    }


    public int[] kWeakestRows(int[][] mat, int k) {
        int m = mat.length, n = mat[0].length;

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]) {
                    return o1[0] - o2[0];
                } else {
                    return o1[1] - o2[1];
                }
            }
        });

        for (int i = 0; i < m; i++) {
            int count = binarySearch(mat[i], 0);
            pq.offer(new int[]{count, i});
        }
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = pq.poll()[1];
        }
        return ans;
    }

    private int binarySearch(int arr[], int target) {
        int l = 0, m = 0, r = arr.length - 1;
        int ans = -1;
        while (l <= r) {
            m = l + (r - l) / 2;
            if (arr[m] <= target) r = m - 1;
            else {
                ans = m;
                l = m + 1;
            }
        }
        return ans + 1;
    }



}
