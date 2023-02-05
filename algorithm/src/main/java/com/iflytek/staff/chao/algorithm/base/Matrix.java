package com.iflytek.staff.chao.algorithm.base;

import com.iflytek.staff.chao.util.DirectionUtil;

import java.util.*;

/**
 * 矩阵  二维数组相关算法
 */
public class Matrix {

    /**
     * 设置零  将0 所在的行和列都转变为 0
     *
     * @param matrix
     */
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        boolean[] line = new boolean[m];
        boolean[] row = new boolean[n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    line[i] = true;
                    row[j] = true;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            if (line[i]) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (row[i]) {
                for (int j = 0; j < m; j++) {
                    matrix[j][i] = 0;
                }
            }
        }
    }

    public void setZeroes2(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 0) {
                    queue.add(new int[]{i, j});
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] idx = queue.poll();
            int r = idx[0];
            int l = idx[1];
            for (int i = 0; i < n; i++) {
                matrix[r][i] = 0;
            }
            for (int i = 0; i < m; i++) {
                matrix[i][l] = 0;
            }
        }
    }

    /**
     * 改变矩阵维度
     *
     * @param mat
     * @param r
     * @param c
     * @return
     */
    public int[][] matrixReshape(int[][] mat, int r, int c) {
        int m = mat.length;
        int n = mat[0].length;
        if (r * c != m * n) {
            return mat;
        }
        int a = 0, b = 0;

        int[][] ans = new int[r][c];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                a = (i * n + j) / c;
                b = (i * n + j) % c;
                ans[a][b] = mat[i][j];
            }
        }

        // 还可以循环ans矩阵 ，以自增指针 动态计算 相除 取余  计算到原矩阵位置

        return ans;
    }

    public double averageWaitingTime(int[][] customers) {
        int n = customers.length;
        if (n < 1) {
            return 0;
        }
        int time = 0;
        int wait = 0;
        for (int i = 0; i < n; i++) {
            if (time < customers[i][0]) {
                time = customers[i][0];
            }
            time += customers[i][1];
            wait += time - customers[i][0];
        }
        return wait / n;
    }

    public int nearestValidPoint(int x, int y, int[][] points) {
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < points.length; i++) {
            if (x == points[i][0] || y == points[i][1]) {
                min = Math.min(min, Math.abs(x - points[i][0]) + Math.abs(y - points[i][1]));
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }


    /**
     * 求经过同一条直线 的最大点数
     *
     * @param points
     * @return
     */
    public int maxPoints(int[][] points) {
        int N = points.length;
        if (N <= 2) return N;

        int ans = 0;

        for (int i = 0; i < N; i++) {
            if (ans >= N - i || ans > N / 2) {
                break;
            }
            Map<Integer, Integer> map = new HashMap<>();
            for (int j = i + 1; j < N; j++) {
                int x = points[i][0] - points[j][0];
                int y = points[i][1] - points[j][1];
                if (x == 0) {
                    y = 1;
                } else if (y == 0) {
                    x = 1;
                } else {
                    if (y < 0) {
                        x = -x;
                        y = -y;
                    }
                    int xy = Number.gcd(y, Math.abs(x));
                    x /= xy;
                    y /= xy;
                    int key = y + x * 20001;
                    map.put(key, map.getOrDefault(key, 0) + 1);
                }
            }
            int max = 0;
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                max = Math.max(max, entry.getValue() + 1);
            }

            ans = Math.max(ans, max);
        }

        return ans;
    }

    /**
     * 搜索二维矩阵 II  Z 字形查找
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int l = 0, r = n - 1;
        while (l < m && r >= 0) {
            if (matrix[l][r] == target) return true;
            if (matrix[l][r] < target) {
                l++;
            } else {
                r--;
            }
        }
        return false;
    }


    public boolean isValidSudoku(char[][] board) {
        int[][] rows = new int[9][9];
        int[][] lines = new int[9][9];
        int[][][] box = new int[3][3][9];

        int n = board.length;
        int m = board[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] != '.') {

                    int num = board[i][j] - '0' - 1;
                    rows[i][num]++;
                    lines[j][num]++;
                    box[i / 3][j / 3][num]++;
                    if (rows[i][num] > 1 || lines[j][num] > 1 || box[i / 3][j / 3][num] > 1) {
                        return false;
                    }

                }
            }
        }
        return true;

    }

    /**
     * @param mat
     * @return
     */
    public int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[] ans = new int[m * n];
        int idx = 0;
        for (int i = 0; i < m + n - 1; i++) {
            if (i % 2 == 0) {
                int x = i < m ? i : m - 1;
                int y = i < m ? 0 : i - m + 1;
                while (x >= 0 && y < n) {
                    ans[idx++] = mat[x][y];
                    x--;
                    y++;
                }
            } else {
                int x = i < n ? 0 : i - n + 1;
                int y = i < n ? i : n - 1;
                while (x < m && y >= 0) {
                    ans[idx++] = mat[x][y];
                    x++;
                    y--;
                }
            }
        }
        return ans;
    }

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

    /**
     * 242 两个字符串包含的字符是否完全相同
     *
     * @param s
     * @param t
     * @return
     */
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

    /**
     * 48 旋转图像
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        int N = matrix.length;
        int temp;
        //上下翻转
        for (int i = 0; i < N / 2; i++) {
            for (int j = 0; j < N; j++) {
                temp = matrix[N - i - 1][j];
                matrix[N - i - 1][j] = matrix[i][j];
                matrix[i][j] = temp;
            }
        }
        // 按对角线 翻转
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
            graph[r[0]][r[1]] = 1;
            graph[r[1]][r[0]] = 1;
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                ans = Math.max(ans, city[i] + city[j] - graph[i][j]);
            }
        }
        return ans;
    }


    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        int N = stations.length;
        PriorityQueue<Integer> gas = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        int count = 0;
        for (int i = 0; i < N; i++) {
            if (startFuel - stations[i][0] < 0) {
                if (gas.isEmpty()) {
                    return -1;
                } else {
                    while (startFuel < stations[i][0] && !gas.isEmpty()) {
                        count++;
                        startFuel += gas.poll();
                    }
                    if (startFuel < stations[i][0]) return -1;
                    gas.offer(stations[i][1]);
                }
            } else {
                gas.offer(stations[i][1]);
            }
        }
        while (startFuel < target && !gas.isEmpty()) {
            count++;
            startFuel += gas.poll();
        }
        if (startFuel >= target) return count;
        return -1;
    }


    /**
     * 找出到达所有节点 的最少点
     *
     * @param n
     * @param edges
     * @return
     */
    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        int[] inCount = new int[n];
        for (List<Integer> edge : edges) {
            inCount[edge.get(1)]++;
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (inCount[i] == 0) {
                ans.add(i);
            }
        }

        return ans;
    }

    public int[][] kClosest(int[][] points, int k) {
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] * o1[0] + o1[1] * o1[1] - (o2[0] * o2[0] + o2[1] * o2[1]);
            }
        });

        return Arrays.copyOfRange(points, 0, k);
    }

    public int[][] kClosest2(int[][] points, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(k, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                // 变为大顶堆
                return o2[0] - o1[0];
            }
        });
        int n = points.length;
        for (int i = 0; i < n; i++) {
            int dist = points[i][0] * points[i][0] + points[i][1] * points[i][1];
            if (i >= k) {
                if (dist < pq.peek()[0]) {
                    pq.poll();
                    // 放入距离平方  、  位置参数
                    pq.offer(new int[]{dist, i});
                }
            }else {
                pq.offer(new int[]{dist, i});
            }
        }

        int[][] ans = new int[k][2];
        for (int i = 0; i < k; i++) {
            ans[i] = points[pq.poll()[1]];
        }

        return ans;
    }


    /**
     * 1886. 判断矩阵经轮转后是否一致  旋转后是否相等
     *
     * @param mat
     * @param target
     * @return
     */
    public boolean findRotation(int[][] mat, int[][] target) {
        int N = mat.length;
        boolean res1 = true, res2 = true, res3 = true, res4 = true;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (mat[i][j] != target[i][j]) res1 = false;

                if (mat[j][N - 1 - i] != target[i][j]) res2 = false;

                if (mat[N - 1 - i][N - 1 - j] != target[i][j]) res3 = false;

                if (mat[N - 1 - j][i] != target[i][j]) res4 = false;

                if(!(res1 || res2 || res3 || res4)) {
                    return false ;
                }
            }
        }

        return true;
    }


    /**
     * 计算矩阵 每一个9宫格中的最大值
     *
     * @param grid
     * @return
     */
    public int[][] largestLocal(int[][] grid) {
        int n = grid.length;
        int[][] ans = new int[n - 2][n - 2];
        for (int i = 0; i < n - 2; i++) {
            for (int j = 0; j < n - 2; j++) {

                //比较9宫格的最大值
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        ans[i][j] = Math.max(ans[i][j], grid[i + k][j + l]);
                    }
                }

            }
        }
        return ans;
    }

    public boolean checkStraightLine(int[][] coordinates) {
        // y = ax + b ;   特殊情况 竖线  水平线
        int[] ratio = calculateRatio(coordinates[0][0], coordinates[0][1],
                coordinates[1][0], coordinates[1][1]);
        int N = coordinates.length;
        int i = 2;
        if (ratio[0] == 1) {
            while (i < N && coordinates[i][0] == coordinates[i - 1][0]) i++;
            return i == N;
        } else {
            while (i < N) {
                int expectY = (coordinates[i][0] * ratio[2] + ratio[1]) / ratio[3];
                if (expectY != coordinates[i][1]) return false;
                i++;
            }
        }
        return true;

    }

    private int[] calculateRatio(int x1, int y1, int x2, int y2) {
        int[] res = new int[4];  // 类型 ，  b 值 ，a值，
        // 不存重复的点 ，
        // 竖线
        if (x1 == x2 && y1 != y2) {
            res[0] = 1;
        }//水平线
        else if (x1 != x2 && y1 == y2) {

            res[1] = y1;
            res[3] = 1;
        } else {
            // 是否会不能整除
            res[1] = y2 * x1 - y1 * x2;

            res[2] = y1 - y2;
            res[3] = x1 - x2;
        }
        return res;
    }

    /**
     * 适用于num值较小  ， 否则占用空间太大
     *
     * @param num
     * @param xPos
     * @param yPos
     * @return
     */
    public int orchestraLayout2(int num, int xPos, int yPos) {

        int[][] grid = new int[num][num];
        int total = num * num;
        int i = 0, j = 0;
        int di = 0;
        for (int k = 0; k < total; k++) {
            grid[i][j] = (k % 9) + 1;

            int ti = i + DirectionUtil.directions[di][0];
            int tj = j + DirectionUtil.directions[di][1];
            if (ti < 0 || ti >= num || tj < 0 || tj >= num || grid[ti][tj] != 0) {
                di = (di + 1) % 4;
            }
            i += DirectionUtil.directions[di][0];
            j += DirectionUtil.directions[di][1];
        }
        return grid[xPos][yPos];
    }

    /**
     * 面积法
     *
     * @param num
     * @param xPos
     * @param yPos
     * @return
     */
    public int orchestraLayout(int num, int xPos, int yPos) {
        int quan = (num + 1) / 2;//总圈数
        //坐标所在圈数
        int ceng = Math.min(Math.min(xPos, yPos), Math.min(num - 1 - xPos, num - 1 - yPos)) + 1;
        // 所在圈的边长  , 总边长 减 2层 外层圈边长
        long innerBian = num - 2 * (ceng - 1);
        long area = 1L * num * num; // 需要先转long类型
        long inArae = innerBian * innerBian;
        long index = ((area - inArae) % 9) + 1; // 前两个值 需要计算时就要用long类型， 否则会出错
        int left = ceng - 1;
        int right = num - ceng;
        // 判断坐标所在内圈的哪一边
        if (xPos == left) {
            index += yPos - left;
        } else if (yPos == right) {
            index += right - left;
            index += xPos - left;
        } else if (xPos == right) {
            index += 2 * (right - left);
            index += right - yPos;
        } else if (yPos == left) {
            index += 3 * (right - left);
            index += right - xPos;
        }

        return (int) (index % 9 == 0 ? 9 : index % 9);
    }

    /**
     * 54. 螺旋矩阵
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        int N = matrix.length;
        if (N == 0) {
            return null;
        }
        int M = matrix[0].length;
        int total = N * M;
        List<Integer> ans = new ArrayList<>();

        boolean[][] seen = new boolean[N][M];
        // 向有 、 下 、 左 、上
        int[][] direction = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int di = 0;

        int i = 0, j = 0;
        for (int k = 0; k < total; k++) {
            ans.add( matrix[i][j]);
            seen[i][j] = true;
            int ti = i + direction[di][0];
            int tj = j + direction[di][1];
            if (ti < 0 || ti >= N || tj < 0 || tj >= M || seen[ti][tj]) {
                // 越界 或者 走到重复位置  则转换方向
                di = (di + 1) % 4;
            }
            i += direction[di][0];
            j += direction[di][1];
        }

        return ans;
    }

    /**
     * 平滑处理  九个格的平均值
     *
     * @param img
     * @return
     */
    public int[][] imageSmoother(int[][] img) {
        int m = img.length;
        int n = img[0].length;

        int[][] ans = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                int num = 0, sum = 0;
                for (int k = i - 1; k <= i + 1; k++) {
                    for (int l = j - 1; l <= j + 1; l++) {
                        if (k >= 0 && k < m && l >= 0 && l < n) {
                            num++;
                            sum += img[k][l];
                        }
                    }
                }
                ans[i][j] = sum / num;
            }

        }

        return ans;
    }

    /**
     * 范围求和
     *
     * @param m
     * @param n
     * @param ops
     * @return
     */
    public int maxCount(int m, int n, int[][] ops) {
        int a = m, b = n;
        for (int i = 0; i < ops.length; i++) {
            if (ops[i][0] < a) a = ops[i][0];
            if (ops[i][1] < b) b = ops[i][1];
        }
        return a * b;
    }

    /**
     * 统计战舰数量
     *
     * @param board
     * @return
     */
    public int countBattleships(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'X') {
                    for (int k = j + 1; k < n && board[i][k] == 'X'; k++) {
                        board[i][k] = '.';
                    }

                    for (int k = i + 1; k < m && board[k][j] == 'X'; k++) {
                        board[k][j] = '.';
                    }
                    ans++;
                }
            }
        }

        return ans;
    }

    /**
     * 有序矩阵的 Kth Element
     *
     * @param matrix
     * @param k
     * @return
     */
    public int kthSmallest(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        int lo = matrix[0][0], hi = matrix[m - 1][n - 1];
        while (lo <= hi) {
            int mid = (hi - lo) / 2 + lo;
            int cnt = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n && matrix[i][j] < mid; j++) {
                    cnt++;
                }
            }
            if (cnt < k) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return lo;
    }

    public int kthSmallest2(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        PriorityQueue<Tuple> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            pq.offer(new Tuple(0, i, matrix[0][i]));
        }
        for (int i = 0; i < k - 1; i++) {
            Tuple tuple = pq.poll();
            if (tuple.x == m - 1) continue; // 到了最下面一行了，不可添加数据
            pq.offer(new Tuple(tuple.x + 1, tuple.y, matrix[tuple.x + 1][tuple.y]));
        }
        return pq.peek().val;
    }

    class Tuple implements Comparable<Tuple> {
        int x, y, val;

        public Tuple(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }

        @Override
        public int compareTo(Tuple o) {
            return this.val - o.val;
        }
    }

    /**
     * 给你一个 m x n 的矩阵 matrix 。如果这个矩阵是托普利茨矩阵，返回 true ；否则，返回 false 。
     * 如果矩阵上每一条由左上到右下的对角线上的元素都相同，那么这个矩阵是 托普利茨矩阵 。
     * 进阶
     * 一次最多只能将矩阵的一行加载到内存中，我们将每一行复制到一个连续数组中，随后在读取下一行时，就与内存中此前保存的数组进行比较。
     *
     * @param matrix
     * @return
     */
    public boolean isToeplitzMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length - 1; i++) {
            for (int j = 0; j < matrix[i].length - 1; j++) {
                if (matrix[i][j] != matrix[i + 1][j + 1]) return false;
            }
        }
        return true;
    }

    public boolean isToeplitzMatrix2(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            if (!check(matrix, matrix[i][0], i + 1, 1)) return false;
        }

        for (int i = 0; i < matrix[0].length; i++) {
            if (!check(matrix, matrix[0][i], 1, i + 1)) return false;
        }
        return true;
    }

    private boolean check(int[][] matrix, int expect, int i, int j) {
        if (i >= matrix.length || j >= matrix[0].length) {
            return true;
        }
        if (matrix[i][j] != expect) {
            return false;
        }
        return check(matrix, expect, i + 1, j + 1);
    }

}
