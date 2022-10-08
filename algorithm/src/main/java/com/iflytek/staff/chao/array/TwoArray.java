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

    public void rotate(int[][] matrix) {
        int N = matrix.length;
        int temp;
        for (int i = 0; i < N / 2; i++) {
            for (int j = 0; j < N; j++) {
                temp = matrix[N - i - 1][j];
                matrix[N - i - 1][j] = matrix[i][j];
                matrix[i][j] = temp;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                temp = matrix[j][i];
                matrix[j][i] = matrix[i][j];
                matrix[i][j] = temp;
            }
        }

    }

    public int[][] merge2(int[][] intervals) {
        //空值判断 忽略
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int N = intervals.length;
        int[][] merged = new int[N][2];
        int idx = 0;
        merged[idx][0] = intervals[0][0];
        merged[idx][1] = intervals[0][1];
        for (int i = 1; i < N; i++) {
            if (merged[idx][1] < intervals[i][0]) {
                //添加新的区间
                idx++;
                merged[idx][0] = intervals[i][0];
                merged[idx][1] = intervals[i][1];
            } else {
                //合并  新的右区间大时 替换  扩大右区间
                if (merged[idx][1] < intervals[i][1]) {
                    merged[idx][1] = intervals[i][1];
                }
            }
        }
        return Arrays.copyOfRange(merged, 0, idx + 1);

    }

    // 生成 1 到 n*n ，且按顺时针 螺旋形式存放到矩阵中
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int ll = 0, lr = n - 1; //左右边界
        int rs = 0, rx = n - 1; // 上下边界
        int num = 0;
        while (ll <= lr && rs <= rx) {
            // 上 从左向右
            for (int i = ll; i <= lr; i++) {
                matrix[rs][i] = ++num;
            }
            ++rs;
//            if(rs>rx) break;  可否删除  ？
            // 右 从上到下
            for (int i = rs; i <= rx; i++) {
                matrix[i][lr] = ++num;
            }
            --lr;
            if (ll > lr) break;
            // 下 从右向左
            for (int i = lr; i >= ll; i--) {
                matrix[rx][i] = ++num;
            }
            --rx;
            if (rx < rs) break;
            // 左 从下向上
            for (int i = rx; i >= rs; i--) {
                matrix[i][ll] = ++num;
            }
            ++ll;
        }

        return matrix;
    }

    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        int F = firstList.length;
        int S = secondList.length;
        if (F == 0 || S == 0) return new int[0][0];
        int f = 0, s = 0;
        int i = 0;
        int[][] merges = new int[F + S][2];
        while (f < F && s < S) {
            if (firstList[f][0] <= secondList[s][0]) {
                if (secondList[s][0] <= firstList[f][1]) {
                    merges[i][0] = secondList[s][0];
                    if (firstList[f][1] <= secondList[s][1]) {
                        merges[i++][1] = firstList[f][1];
                        f++;
                    } else {
                        merges[i++][1] = secondList[s][1];
                        s++;
                    }
                } else {
                    f++;
                }
            } else {

                if (firstList[f][0] <= secondList[s][1]) {
                    merges[i][0] = firstList[f][0];
                    if (secondList[s][1] <= firstList[f][1]) {
                        merges[i++][1] = secondList[s][1];
                        s++;
                    } else {
                        merges[i++][1] = firstList[f][1];
                        f++;
                    }
                } else {
                    s++;
                }
            }
        }
        return Arrays.copyOfRange(merges, 0, i);
    }

    public int maxArea(int[] height) {
        int N = height.length;
        int maxArea = 0;
        int i = 0, j = N - 1;
        while (i < j) {
            if (height[j] > height[i]) {
                maxArea = Math.max(maxArea, (j - i) * height[i]);
                i++;
            } else {
                maxArea = Math.max(maxArea, (j - i) * height[j]);
                j--;
            }
        }
        return maxArea;
    }

    public int maximalNetworkRank(int n, int[][] roads) {
        int[] city = new int[n];
        int[][] graph = new int[n][n];
        for (int[] r : roads) {
            city[r[0]]++;
            city[r[1]]++;
            graph[r[0]][r[1]]=1;
            graph[r[1]][r[0]]=1;
        }
        int ans = 0 ;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                ans = Math.max(ans,city[i]+city[j] - graph[i][j]);
            }
        }
        return ans ;
    }


    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        int N = stations.length;
        PriorityQueue<Integer>  gas = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        }) ;
        int count =0 ;
        for (int i = 0; i <N ; i++) {
            if(startFuel - stations[i][0]<0) {
                if(gas.isEmpty()){
                    return -1;
                }else {
                    while (startFuel<stations[i][0] && !gas.isEmpty()){
                        count++;
                        startFuel += gas.poll();
                    }
                    if(startFuel<stations[i][0]) return -1;
                    gas.offer(stations[i][1]);
                }
            }else {
                gas.offer(stations[i][1]);
            }
        }
        while (startFuel<target && !gas.isEmpty() ){
            count++;
            startFuel += gas.poll();
        }
        if(startFuel>=target) return count ;
        return -1;
    }


    /**
     * 找出到达所有节点 的最少点
     * @param n
     * @param edges
     * @return
     */
    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        int[] inCount = new int[n];
        for (List<Integer> edge : edges){
            inCount[edge.get(1)]++;
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if(inCount[i]==0) {
                ans.add(i);
            }
        }

        return ans ;
    }

    public int[][] kClosest(int[][] points, int k) {
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]*o1[0] + o1[1]*o1[1]  -  (o2[0]*o2[0] + o2[1]*o2[1]);
            }
        });

       return Arrays.copyOfRange(points,0,k);
    }

    public int[][] kClosest2(int[][] points, int k) {
       PriorityQueue<int[]> pq = new PriorityQueue<>(k, new Comparator<int[]>() {
           @Override
           public int compare(int[] o1, int[] o2) {
               return o2[0]-o1[0];
           }
       }) ;
        int n = points.length ;
        for (int i = 0; i < n; i++) {
            int dist = points[i][0]* points[i][0]    + points[i][1] * points[i][1] ;
            if(i>=k) {
                if(dist < pq.peek()[0]){
                    pq.poll();
                }
            }

            pq.offer(new int[]{dist,i});
        }

        int[][] ans = new int[k][2];
        for (int i = 0; i <k ; i++) {
            ans[i] = points[pq.poll()[1]];
        }

        return  ans ;
    }

    public void setZeroes(int[][] matrix) {
        int m = matrix.length ;
        int n = matrix[0].length;

        boolean[]  line = new boolean[m];
        boolean[]  row = new boolean[n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(matrix[i][j]==0){
                    line[i]=true;
                    row[j]=true;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            if(line[i]){
                for (int j = 0; j < n; j++) {
                    matrix[i][j]=0;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if(row[i]){
                for (int j = 0; j < m; j++) {
                    matrix[j][i]=0;
                }
            }
        }


    }

    /**
     * 查看矩阵是否相等   旋转后是否相等
     * @param mat
     * @param target
     * @return
     */
    public boolean findRotation(int[][] mat, int[][] target) {
        int N = mat.length;
        boolean res1 = true, res2 = true ,res3 =true , res4 =true ;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(mat[i][j] != target[i][j])  res1 = false;

                if(mat[j][N-1-i] !=target[i][j]) res2 =false;

                if(mat[N-1-i][N-1-j] !=target[i][j]) res3 =false;

                if(mat[N-1-j][i] !=target[i][j]) res4 =false;
            }
        }

        return res1 || res2 || res3 || res4;
    }


}
