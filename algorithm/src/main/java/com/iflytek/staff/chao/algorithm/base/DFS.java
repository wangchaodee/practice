package com.iflytek.staff.chao.algorithm.base;

import com.iflytek.staff.chao.util.DirectionUtil;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author : wangchaodee
 * @Description: 深度优先搜索
 * 求解可达性
 * 在程序实现 DFS 时需要考虑以下问题：
 * 栈：用栈来保存当前节点信息，当遍历新节点返回时能够继续遍历当前节点。可以使用递归栈。
 * 标记：和 BFS 一样同样需要对已经遍历过的节点进行标记。
 * @date Date : 2022年06月24日 下午1:10
 */
public class DFS {


    /**
     * 733. 图像渲染
     * @param image
     * @param sr
     * @param sc
     * @param color
     * @return
     */
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int curColor = image[sr][sc];
        if (curColor != color) {
            dfs(image, sr, sc, curColor, color);
        }
        return image;
    }


    /**
     * 深度优先搜索
     *
     * @param image
     * @param sr
     * @param sc
     * @param color
     * @param newColor
     */
    public void dfs(int[][] image, int sr, int sc, int color, int newColor) {
        int curColor = image[sr][sc];
        if (curColor == color) {
            image[sr][sc] = newColor;
            for (int[] xy : DirectionUtil.directions) {
                int srr = sr + xy[0];
                int scc = sc + xy[1];
                if (0 <= srr && srr < image.length && 0 <= scc && scc < image[sr].length) {
                    dfs(image, srr, scc, color, newColor);
                }
            }
        }
    }

    /**
     * 463. 岛屿的周长
     * @param grid
     * @return
     */
    public int islandPerimeter(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if(grid[i][j]==1) {
                    boolean[][] seen = new boolean[grid.length][grid[i].length] ;
                    return  dfsSide(grid,i,j,seen);
                }
            }
        }
        return 0;
    }

    public int dfsSide(int[][] grid, int sr, int sc, boolean[][] seen ) {
        seen[sr][sc] = true ;
        int cnt = 0 ;

        for (int[] xy : DirectionUtil.directions) {
            int srr = sr + xy[0];
            int scc = sc + xy[1];
            if(0 > srr || srr >= grid.length || 0 > scc || scc >= grid[sr].length || grid[srr][scc]==0){
                cnt++;
            } else if(!seen[srr][scc] && grid[srr][scc]==1) {
                cnt +=dfsSide(grid,srr,scc,seen);
            }
        }
        return cnt ;
    }

    /**
     * 695. 岛屿的最大面积
     *
     * @param grid
     * @return
     */
    public int maxAreaOfIsland(int[][] grid) {

        int depth = 0;
        int n = grid.length;
        int m = grid[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                depth = Math.max(depth, dfsDepth(grid, i, j, n, m));
            }
        }
        return depth;
    }

    private int dfsDepth(int[][] grid, int r, int l, int n, int m) {
        if (r < 0 || r >= n || l < 0 || l >= m || grid[r][l] == 0) {
            return 0;
        }
        int d = 1;
        grid[r][l] = 0;
        for (int[] xy : DirectionUtil.directions) {
            int srr = r + xy[0];
            int scc = l + xy[1];
            d += dfsDepth(grid, srr, scc, n, m);
        }
        return d;
    }

    public int[][] updateMatrix(int[][] mat) {

        int n = mat.length;
        int m = mat[0].length;
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] seen = new boolean[n][m];

        int[][] dist = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                    seen[i][j] = true;

                }
            }
        }

        while (!queue.isEmpty()) {
            int[] xy = queue.poll();
            int sr = xy[0];
            int sc = xy[1];
            for (int[] dir : DirectionUtil.directions) {
                int srr = sr + dir[0];
                int scc = sc + dir[1];
                if (0 <= srr && srr < n && 0 <= scc && scc < m && seen[srr][scc] == false) {
                    queue.offer(new int[]{srr, scc});
                    dist[srr][scc] = dist[sr][sc] + 1;
                    seen[srr][scc] = true;
                }
            }
        }

        return mat;
    }


    public int orangesRotting(int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] seen = new boolean[n][m];

        int[][] dist = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                    seen[i][j] = true;
                }
            }
        }
        int ans = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            ans++;
            for (int j = 0; j < size; j++) {
                int[] xy = queue.poll();
                int sr = xy[0];
                int sc = xy[1];
                for (int[] dir : DirectionUtil.directions) {
                    int srr = sr + dir[0];
                    int scc = sc + dir[1];
                    if (0 <= srr && srr < n && 0 <= scc && scc < m && seen[srr][scc] == false && grid[srr][scc] == 1) {
                        queue.offer(new int[]{srr, scc});
                        grid[srr][scc] = 2;
                        seen[srr][scc] = true;
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    return -1;
                }
            }
        }


        return ans;
    }




    /**
     * 200 岛屿数量
     *
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1') {
                    //
                    numOfIslandDfs(i, j, grid, n, m);
                    count++;
                }
            }
        }
        return count;
    }

    private void numOfIslandDfs(int i, int j, char[][] grid, int n, int m) {

        grid[i][j] = '0';
        int sr = i;
        int sc = j;
        for (int[] dir : DirectionUtil.directions) {
            int srr = sr + dir[0];
            int scc = sc + dir[1];
            if (0 <= srr && srr < n && 0 <= scc && scc < m && grid[srr][scc] == '1') {
                numOfIslandDfs(srr, scc, grid, n, m);
            }
        }
    }

    private int numOfIsland(int i, int j, char[][] grid, int n, int m, boolean[][] seen) {

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});
        seen[i][j] = true;

        int ans = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            ans++;
            int[] xy = queue.poll();
            int sr = xy[0];
            int sc = xy[1];
            for (int[] dir : DirectionUtil.directions) {
                int srr = sr + dir[0];
                int scc = sc + dir[1];
                if (0 <= srr && srr < n && 0 <= scc && scc < m && seen[srr][scc] == false && grid[srr][scc] == '1') {
                    queue.offer(new int[]{srr, scc});
                    seen[srr][scc] = true;
                }
            }
        }

        return ans;
    }


    public int openLock(String[] deadends, String target) {

        if (Arrays.stream(deadends).collect(Collectors.toList()).contains("0000")) {
            return -1;
        }
        ;
        if (deadends.length >= 8) {
            //检查是否不可达
            List<String> starts = getNextKeys("0000");

            if (Arrays.stream(deadends).collect(Collectors.toList()).containsAll(starts)) {
                return -1;
            }
            ;

            List<String> targets = getNextKeys(target);

            if (Arrays.stream(deadends).collect(Collectors.toList()).containsAll(targets)) {
                return -1;
            }
            ;
        }

        if ("0000".equals(target)) {
            return 0;
        }

        Queue<String> queue = new LinkedList<>();
        Set<String> seen = new HashSet<>();
        queue.add("0000");
        seen.add("0000");


        Set<String> dead = Arrays.stream(deadends).collect(Collectors.toSet());
        int count = 0;
        while (!queue.isEmpty()) {
            count++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String key = queue.poll();
                List<String> nextkeys = getNextKeys(key);
                for (String nextkey : nextkeys) {
                    if (!seen.contains(nextkey) && !dead.contains(nextkey)) {
                        if (nextkey.equals(target)) {
                            return count;
                        }
                        queue.add(nextkey);
                        seen.add(nextkey);
                    }
                }
            }
        }
        return -1;

    }

    public List<String> getNextKeys(String currentKey) {
        List<String> keys = new ArrayList<>();
        for (int i = 0; i < currentKey.length(); i++) {
            // +1 , -1
            char ch = currentKey.charAt(i);
            keys.add(currentKey.substring(0, i) + (ch == '9' ? 0 : ch - '0' + 1) + currentKey.substring(i + 1));
            // 三目表达式 后 的值类型 要一直， ？后写'9' 有被转为 ascii 码
            keys.add(currentKey.substring(0, i) + (ch == '0' ? 9 : ch - '0' - 1) + currentKey.substring(i + 1));

        }
        return keys;
    }


    private int count = 0;

    public int findTargetSumWays(int[] nums, int target) {
        findTargetSumWays(nums, target, 0, 0);
        return count;
    }

    private void findTargetSumWays(int[] nums, int target, int idx, int sum) {
        if (idx == nums.length) {
            if (sum == target) count++;
        }
        findTargetSumWays(nums, target, idx + 1, sum + nums[idx]);
        findTargetSumWays(nums, target, idx + 1, sum + nums[idx]);

    }

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int N = rooms.size();
        boolean[] seen = new boolean[N];
        Stack<Integer> stack = new Stack<>();

        visitRoomDfs(rooms, seen, 0);
        for (boolean ss : seen) {
            if (!ss) return false;
        }
        return true;
    }

    private void visitRoomDfs(List<List<Integer>> rooms, boolean[] seen, int roomNo) {
        seen[roomNo] = true;

        for (Integer room : rooms.get(roomNo)) {
            if (seen[room]) continue;
            visitRoomDfs(rooms, seen, room);
        }
    }


    /**
     * 统计被水域完全包围的 岛屿数量
     *
     * @param grid
     * @return
     */
    public int closedIsland(int[][] grid) {
        int count = 0;
        int n = grid.length;
        int m = grid[0].length;

        for (int i = 0; i < n; i++) {
            if (grid[i][0] == 0) dfsClosed(grid, i, 0, n, m);
            if (grid[i][m - 1] == 0) dfsClosed(grid, i, m - 1, n, m);
        }
        for (int j = 0; j < m; j++) {
            if (grid[0][j] == 0) dfsClosed(grid, 0, j, n, m);
            if (grid[n - 1][j] == 0) dfsClosed(grid, n - 1, j, n, m);
        }
        //先将贴边的清楚掉
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0) {
                    dfsClosed(grid, i, j, n, m);
                    count++;
                }
            }
        }
        return count;
    }


    private void dfsClosed(int[][] grid, int r, int l, int n, int m) {
        grid[r][l] = 1;

        for (int[] dir : DirectionUtil.directions) {
            int r1 = r + dir[0];
            int l1 = l + dir[1];
            if (0 <= r1 && r1 < n && 0 <= l1 && l1 < m && grid[r1][l1] == 0) {
                dfsClosed(grid, r1, l1, n, m);
            }
        }
    }

    public int numEnclaves(int[][] grid) {
        int count = 0;
        int n = grid.length;
        int m = grid[0].length;

        //先将贴边的清楚掉
        for (int i = 0; i < n; i++) {
            if (grid[i][0] == 0) dfsClosed(grid, i, 0, n, m);
            if (grid[i][m - 1] == 0) dfsClosed(grid, i, m - 1, n, m);
        }
        for (int j = 0; j < m; j++) {
            if (grid[0][j] == 0) dfsClosed(grid, 0, j, n, m);
            if (grid[n - 1][j] == 0) dfsClosed(grid, n - 1, j, n, m);
        }

        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < m - 1; j++) {
                if (grid[i][j] == 0) {
                    count += dfsDepth(grid, i, j, n, m);
                }
            }
        }
        return count;
    }

    public int countSubIslands(int[][] grid1, int[][] grid) {

        int count = 0;
        int n = grid.length;
        int m = grid[0].length;

        //统计岛屿数量，
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    if (dfsIslandValid(grid, grid1, i, j, n, m)) count++;
                }
            }
        }
        return count;
    }

    private boolean dfsIslandValid(int[][] grid, int[][] gridCheck, int r, int l, int n, int m) {

        grid[r][l] = 0;
        boolean res = true;
        for (int[] dir : DirectionUtil.directions) {
            int r1 = r + dir[0];
            int l1 = l + dir[1];
            if (0 <= r1 && r1 < n && 0 <= l1 && l1 < m && grid[r1][l1] == 1) {
                res &= dfsIslandValid(grid, gridCheck, r1, l1, n, m);
            }
        }

        return res & gridCheck[r][l] == 1;
    }

    public int maxDistance(int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();

        //放陆地进队列
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    queue.offer(new int[]{i, j});
                }
            }
        }

        int ans = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            ans++;
            for (int i = 0; i < size; i++) {
                int[] xy = queue.poll();
                int sr = xy[0];
                int sc = xy[1];
                for (int[] dir : DirectionUtil.directions) {
                    int srr = sr + dir[0];
                    int scc = sc + dir[1];
                    if (0 <= srr && srr < n && 0 <= scc && scc < m && grid[srr][scc] == 0) {
                        grid[srr][scc] = ans;
                        queue.offer(new int[]{srr, scc});
                    }
                }
            }
        }

        return ans;

    }

    /**
     * 417. 太平洋大西洋水流问题
     *
     * @param heights
     * @return
     */
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int N = heights.length;
        int M = heights.length;
        boolean[][] pOean = new boolean[N][M];
        boolean[][] aOean = new boolean[N][M];
        // 上  下
        for (int i = 0; i < N; i++) {
            dfsOean(heights, 0, i, N, M, pOean);
            dfsOean(heights, N - 1, i, N, M, aOean);
        }
        // 左 右
        for (int i = 0; i < M; i++) {
            dfsOean(heights, i, 0, N, M, pOean);
            dfsOean(heights, i, N - 1, N, M, aOean);
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (pOean[i][j] && aOean[i][j]) ans.add(Arrays.asList(i, j));
            }
        }
        return ans;
    }

    private void dfsOean(int[][] heights, int r, int l, int n, int m, boolean[][] oean) {
        if (oean[r][l]) return;
        oean[r][l] = true;
        for (int[] dir : DirectionUtil.directions) {
            int r1 = r + dir[0];
            int l1 = l + dir[1];
            if (0 <= r1 && r1 < n && 0 <= l1 && l1 < m && heights[r1][l1] >= heights[r][l]) {
                dfsOean(heights, r1, l1, n, m, oean);
            }
        }
    }

    public int[][] updateMatrix2(int[][] mat) {

        int n = mat.length;
        int m = mat[0].length;
        Queue<int[]> queue = new LinkedList<>();

        int[][] dist = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                }
            }
        }
        int distance = 0;
        while (!queue.isEmpty()) {
            distance++;
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                int[] xy = queue.poll();
                int sr = xy[0];
                int sc = xy[1];
                for (int[] dir : DirectionUtil.directions) {
                    int srr = sr + dir[0];
                    int scc = sc + dir[1];
                    if (0 <= srr && srr < n && 0 <= scc && scc < m && mat[srr][scc] == 1) {
                        queue.offer(new int[]{srr, scc});
                        dist[srr][scc] = distance;
                        mat[srr][scc] = 0;
                    }
                }
            }
        }

        return dist;
    }

    /**
     * 547. 省份数量
     *
     * @param isConnected
     * @return
     */
    public int findCircleNum(int[][] isConnected) {

        int n = isConnected.length;

        boolean[] seen = new boolean[n];
        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (!seen[i]) {
                queue.offer(i);
                seen[i] = true;
                while (!queue.isEmpty()) {
                    int size = queue.size();
                    int idx = queue.poll();
                    // seen[idx]=true;
                    for (int j = 0; j < n; j++) {
                        if (!seen[j] && isConnected[idx][j] == 1) {
                            queue.offer(j);
                            seen[j] = true;
                        }
                    }
                }
                count++;
            }
        }
        return count;
    }


    public int makeConnected(int n, int[][] connections) {
        int N = connections.length;
        if (N < n - 1) return -1;

        List<Integer>[] grid = new List[n];

        for (int i = 0; i < n; ++i) {
            grid[i] = new ArrayList<Integer>();
        }

        //转化关联关系， 方便迭代时查找到下一节点的关联值
        for (int i = 0; i < N; i++) {
            int x = connections[i][0];
            int y = connections[i][1];
            grid[x].add(y);
            grid[y].add(x);
        }

        int count = findCircleNum(grid);
        return count - 1;
    }

    public int findCircleNum(List<Integer>[] isConnected) {

        int n = isConnected.length;

        boolean[] seen = new boolean[n];
        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (!seen[i]) {
                queue.offer(i);
                seen[i] = true;
                while (!queue.isEmpty()) {
                    int idx = queue.poll();
                    isConnected[idx].stream().forEach((j) -> {
                        if (!seen[j]) {
                            queue.offer(j);
                            seen[j] = true;
                        }
                    });

                }

                count++;
            }

        }
        return count;
    }


    /**
     * 只有两个岛屿， 求它们之间的最小距离
     *
     * @param grid
     * @return
     */
    public int shortestBridge(int[][] grid) {
        int N = grid.length;
        int color = 2;
        int target = 1;

        //将其中一个岛标记为2 ，
        colorIsland(grid, N, color);

        Queue<NodeXy> stack = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == color) {
                    stack.add(new NodeXy(i, j));
                }
            }
        }

        int step = 0;
        while (!stack.isEmpty()) {
            step++;
            int size = stack.size();
            for (int k = 0; k < size; k++) {
                NodeXy idx = stack.poll();
                for (int[] xy : DirectionUtil.directions) {
                    int r1 = idx.x + xy[0];
                    int l1 = idx.y + xy[1];
                    if (0 <= r1 && r1 < N && 0 <= l1 && l1 < N && grid[r1][l1] != color) {
                        if (grid[r1][l1] == 0) {
                            grid[r1][l1] = color;
                            stack.add(new NodeXy(r1, l1));
                        } else {
                            // 遇到target 的岛屿了
                            return step;
                        }
                    }
                }
            }
        }
        return step;
    }

    private void colorIsland(int[][] grid, int N, int color) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == 1) {
                    dfsColorIsland(grid, i, j, N, N, color);
                    return;
                }
            }
        }
    }


    private void dfsColorIsland(int[][] grid, int r, int l, int n, int m, int color) {
        grid[r][l] = color;
        for (int[] xy : DirectionUtil.directions) {
            int r1 = r + xy[0];
            int l1 = l + xy[1];
            if (0 <= r1 && r1 < n && 0 <= l1 && l1 < m && grid[r1][l1] == 1) {
                dfsColorIsland(grid, r1, l1, n, m, color);
            }
        }
    }


    class NodeXy {
        int x;
        int y;

        public NodeXy(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


    public int nearestExit(char[][] maze, int[] entrance) {
        int m = maze.length;
        int n = maze[0].length;

        //将其中一个岛标记为2
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(entrance);
        maze[entrance[0]][entrance[1]] = '+';
        int step = 0;
        while (!queue.isEmpty()) {
            step++;
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                int[] idx = queue.poll();
                for (int[] dir : DirectionUtil.directions) {
                    int r1 = idx[0] + dir[0];
                    int l1 = idx[1] + dir[1];
                    if (0 <= r1 && r1 < m && 0 <= l1 && l1 < n && maze[r1][l1] == '.') {
                        if (r1 == 0 || r1 == m - 1 || l1 == 0 || l1 == n - 1) {
                            return step;
                        } else {
                            maze[r1][l1] = '+';
                            queue.add(new int[]{r1, l1});
                        }
                    }
                }
            }
        }
        return -1;
    }

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> ans = new ArrayList<>();
        Deque<Integer> deque = new ArrayDeque<>();
        dfsGraph(graph, deque, 0, graph.length - 1, ans);
        return ans;
    }

    private void dfsGraph(int[][] graph, Deque<Integer> deque, int next, int target, List<List<Integer>> ans) {
        deque.addLast(next);
        if (next == target) {
            ans.add(new ArrayList<>(deque));
        } else {
            for (int n : graph[next]) {
                dfsGraph(graph, deque, n, target, ans);
            }
        }
        deque.removeLast();
    }

    public boolean canVisitAllRooms2(List<List<Integer>> rooms) {
        int N = rooms.size();
        int[] seen = new int[N];

        visitRoomDfs2(rooms, seen, 0);

        int count = Arrays.stream(seen).sum();

        return count == N;
    }

    private void visitRoomDfs2(List<List<Integer>> rooms, int[] seen, int roomNo) {
        seen[roomNo] = 1;

        for (Integer room : rooms.get(roomNo)) {
            if (seen[room] == 0) visitRoomDfs2(rooms, seen, room);

        }
    }

    /**
     * 130 被围绕的区域
     * 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
     *
     * @param board
     */
    public void solve(char[][] board) {
        int n = board.length;
        int m = board[0].length;

        int[][] grid = new int[n][m];
        //先将边上的标记出来
        for (int i = 0; i < n; i++) {
            if (grid[i][0] == 0 && board[i][0] == 'O') dfsMark(board, grid, i, 0, n, m);
            if (grid[i][m - 1] == 0 && board[i][m - 1] == 'O') dfsMark(board, grid, i, m - 1, n, m);
        }
        for (int j = 0; j < m; j++) {
            if (grid[0][j] == 0 && board[0][j] == 'O') dfsMark(board, grid, 0, j, n, m);
            if (grid[n - 1][j] == 0 && board[n - 1][j] == 'O') dfsMark(board, grid, n - 1, j, n, m);
        }


        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < m - 1; j++) {
                if (board[i][j] == 'O' && grid[i][j] == 0) {
                    dfsReplace(board, grid, i, j, n, m);
                }
            }
        }
    }

    private void dfsMark(char[][] board, int[][] grid, int r, int l, int n, int m) {
        grid[r][l] = 1;
        for (int[] dir : DirectionUtil.directions) {
            int r1 = r + dir[0];
            int l1 = l + dir[1];
            if (0 <= r1 && r1 < n && 0 <= l1 && l1 < m && grid[r1][l1] == 0 && board[r1][l1] == 'O') {
                dfsMark(board, grid, r1, l1, n, m);
            }
        }
    }

    private void dfsReplace(char[][] board, int[][] grid, int r, int l, int n, int m) {
        board[r][l] = 'X';
        for (int[] dir : DirectionUtil.directions) {
            int r1 = r + dir[0];
            int l1 = l + dir[1];
            if (0 <= r1 && r1 < n && 0 <= l1 && l1 < m && grid[r1][l1] == 0 && board[r1][l1] == 'O') {
                dfsReplace(board, grid, r1, l1, n, m);
            }
        }
    }

    /**
     * 1376. 通知所有员工所需的时间
     */
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        // 每个员工收到通知的时间
        int[] total = new int[n];
        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, dfsInformTime(i, manager, informTime, total));
        }

        return max;
    }

    // 通知到第i个员工的时间
    private int dfsInformTime(int i, int[] manager, int[] informTime, int[] total) {
        if (manager[i] == -1 || total[i] != 0) {
            return total[i];
        }
        total[i] =  informTime[manager[i]] + dfsInformTime(manager[i], manager, informTime, total);
        return total[i];
    }

    public List<Integer> eventualSafeNodes(int[][] graph) {
        List<Integer> endPoints = new ArrayList<>();
        int[] color = new int[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (safe(graph, color, i)) {
                endPoints.add(i);
            }
        }

        return endPoints;
    }

    private boolean safe(int[][] graph, int[] color, int i) {
        if (color[i] > 0) {
            return color[i] == 2;
        }
        color[i] = 1;
        for (int x : graph[i]) {
            if (!safe(graph, color, x)) {
                return false;
            }
        }
        color[i] = 2;
        return true;
    }

    public boolean canReach(int[] arr, int start) {
        int N = arr.length;
        boolean[] seen = new boolean[N];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        int idx = start;
        while (!queue.isEmpty()) {
            idx = queue.poll();

            if (arr[idx] == 0) return true;
            seen[idx] = true;
            if (idx + arr[idx] < N && !seen[idx + arr[idx]]) {
                queue.offer(idx + arr[idx]);
            }

            if (idx - arr[idx] >= 0 && !seen[idx - arr[idx]]) {
                queue.offer(idx - arr[idx]);
            }
        }
        return false;
    }

    public int minimumJumps(int[] forbidden, int a, int b, int x) {
        if (x == 0) return 0;

        int max = Math.max(Arrays.stream(forbidden).max().getAsInt() + a + b, x + b);

        boolean[] seen = new boolean[6001];
        for (int f : forbidden) {
            seen[f] = true;
        }

        Queue<int[]> queue = new LinkedList<>();
        // 用二元数组表示 跳跃后的坐标 及跳跃的方向 ，1 向右， -1 向左
        queue.offer(new int[]{0, 1});
        seen[0] = true;
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            step++;
            for (int i = 0; i < size; i++) {
                int[] idx = queue.poll();

                //上次向右
                if (idx[1] == 1) {
                    //可以增加向左跳的
                    int left = idx[0] - b;
                    if (left == x) return step;
                    if (left >= 0 && !seen[left]) {
                        queue.offer(new int[]{left, -1});
                        seen[left] = true;
                    }
                }
                // 向右跳
                int right = idx[0] + a;
                if (right == x) return step;
                if (right <= max && !seen[right]) {
                    queue.offer(new int[]{right, 1});
                    seen[right] = true;
                }
            }
        }
        return -1;
    }

    public int minMutation(String start, String end, String[] bank) {

        if (start.equals(end)) {
            return 0;
        }

        Set bankSet = new HashSet();
        for (String c : bank) {
            bankSet.add(c);
        }
        if (!bankSet.contains(end)) {
            return -1;
        }
        char[] replace = {'A', 'C', 'G', 'T'};
        Set visited = new HashSet();
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        visited.add(start);
        int step = 0;
        while (!queue.isEmpty()) {
            step++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String tem = queue.poll();

                for (int j = 0; j < tem.length(); j++) {
                    if (tem.charAt(j) == end.charAt(j)) {
                        continue;
                    }
                    for (int k = 0; k < replace.length; k++) {

                        if (tem.charAt(j) == replace[k]) continue;

                        StringBuilder sb = new StringBuilder(tem);
                        sb.setCharAt(j, replace[k]);
                        String rep = sb.toString();

                        if (!visited.contains(rep) && bankSet.contains(rep)) {
                            if (rep.equals(end)) {
                                return step;
                            }
                            queue.offer(rep);
                            visited.add(rep);
                        }

                    }
                }
            }
        }
        return -1;
    }

    public int openLock2(String[] deadends, String target) {
        if ("0000".equals(target)) return 0;
        Set<String> deadSet = new HashSet<>();
        for (String c : deadends) {
            deadSet.add(c);
        }
        if (deadSet.contains("0000")) {
            return -1;
        }
        Set<String> seen = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer("0000");
        seen.add("0000");
        int step = 0;
        while (!queue.isEmpty()) {
            step++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String tem = queue.poll();
                List<String> keys = getNextKeys(tem);
                for (String key : keys) {
                    if (key.equals(target)) return step;
                    if (!seen.contains(key) && !deadSet.contains(key)) {
                        queue.offer(key);
                        seen.add(key);
                    }
                }
            }
        }
        return -1;
    }


    public int findJudge(int n, int[][] trust) {
        int[] count = new int[n + 1];
        for (int[] t : trust) {
            count[t[0]]--;
            count[t[1]]++;
        }
        for (int i = 1; i < n + 1; i++) {
            if (count[i] == n - 1) return i;
        }
        return -1;
    }

    /**
     * 785 判断是否为二分图
     * 编号 0 到 n-1
     *
     * @param graph
     * @return
     */
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] parted = new int[n];
        for (int i = 0; i < n; i++) {
            if (parted[i] == 0) {
                //分两个 ， 0 未分  1 ，part1 ,-1 part2
                if (!partDfs(graph, i, parted, 1)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean partDfs(int[][] graph, int idx, int[] parted, int flag) {
        if (parted[idx] == 0) {
            parted[idx] = flag;
        } else if (parted[idx] != flag) {
            return false;
        } else {
            return true;
        }

        for (Integer d : graph[idx]) {
            boolean res = partDfs(graph, d, parted, -flag);
            if (!res) return false;
        }
        return true;
    }

    public boolean possibleBipartition(int n, int[][] dislikes) {
        List<List<Integer>> disList = new ArrayList<>();

        //设置n+1个 ，便于按1 到n 编号
        for (int i = 0; i < n + 1; i++) {
            disList.add(new ArrayList<>());
        }

        for (int[] dis : dislikes) {
            disList.get(dis[0]).add(dis[1]);
            disList.get(dis[1]).add(dis[0]);
        }

        int[] parted = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            if (parted[i] == 0) {

                //分两个 ， 0 未分  1 ，part1 ,-1 part2
                if (!partDfs(disList, i, parted, 1)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean partDfs(List<List<Integer>> disList, int idx, int[] parted, int flag) {
        if (parted[idx] == 0) {
            parted[idx] = flag;
        } else if (parted[idx] != flag) {
            return false;
        } else {
            return true;
        }

        for (Integer d : disList.get(idx)) {
            boolean res = partDfs(disList, d, parted, -flag);
            if (!res) return false;
        }
        return true;
    }


    /**
     * 3
     * [[0,1],[0,2]]
     * [[1,0]]
     * <p>
     * 5
     * [[0,1],[1,2],[2,3],[3,4]]
     * [[1,2],[2,3],[3,1]]
     *
     * @param n
     * @param redEdges
     * @param blueEdges
     * @return
     */
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        List<Integer>[] reds = new List[n];
        List<Integer>[] blues = new List[n];
        for (int i = 0; i < n; i++) {
            reds[i] = new ArrayList();
            blues[i] = new ArrayList();
        }

        for (int[] red : redEdges) {
            reds[red[0]].add(red[1]);
//            reds[red[1]].add(red[0]);
        }

        for (int[] blue : blueEdges) {
            blues[blue[0]].add(blue[1]);
//            blues[blue[1]].add(blue[0]);
        }

        int[] ansb = new int[n];
        int[] ansr = new int[n];
        Arrays.fill(ansb, -1);
        Arrays.fill(ansr, -1);
        int step = 0;
        // 1 代表红色， -1 代表蓝色
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 1});
        queue.offer(new int[]{0, -1});
        ansb[0] = step;
        ansr[0] = step;
        while (!queue.isEmpty()) {
            step++;
            int sz = queue.size();
            for (int i = 0; i < sz; i++) {
                int[] idx = queue.poll();
                //红色过来的要找 蓝色线
                if (idx[1] == 1) {
                    for (Integer next : blues[idx[0]]) {
                        if (ansr[next] != -1) {
                            continue;
                        }
                        ansr[next] = step;
                        queue.offer(new int[]{next, -1});
                    }
                } else {
                    //蓝色过来的
                    for (Integer next : reds[idx[0]]) {
                        if (ansb[next] != -1) {
                            continue;
                        }
                        ansb[next] = step;
                        queue.offer(new int[]{next, 1});
                    }
                }
            }
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            if (ansb[i] == -1 || ansr[i] == -1) {
                ans[i] = Math.max(ansb[i], ansr[i]);
            } else {
                ans[i] = Math.min(ansb[i], ansr[i]);
            }
        }
        return ans;
    }


    public int closestMeetingNode(int[] edges, int node1, int node2) {
        if (node1 == node2) return node1;

        if (node1 < node2) return closestMeetingNode(edges, node2, node1);

        Set<Integer> set1 = new HashSet<>();
        while (edges[node1] != -1) {
            set1.add(node1);
            node1 = edges[node1];
            if (set1.contains(node1)) {
                break;
            }
        }
        set1.add(node1);

        Set<Integer> set2 = new HashSet<>();
        while (!set1.contains(node2) && edges[node2] != -1) {

            set2.add(node2);
            node2 = edges[node2];

            if (set2.contains(node2)) {
                break;
            }
        }

        return set1.contains(node2) ? node2 : -1;

    }

    public int minReorder(int n, int[][] connections) {
        int N = connections.length;
        List<int[]>[] points = new List[n];
        for (int i = 0; i < n; i++) {
            points[i] = new ArrayList<>();
        }
        for (int i = 0; i < N; i++) {
            //  现有路方向 1 ，-1 为逆向 ，  遍历时从0开始 ，则反过来
            points[connections[i][0]].add(new int[]{connections[i][1], 1});
            points[connections[i][1]].add(new int[]{connections[i][0], -1});
        }
        Queue<Integer> queue = new LinkedList<>();
        boolean[] seen = new boolean[n];
        queue.offer(0);
        seen[0] = true;
        int count = 0;
        while (!queue.isEmpty()) {
            int i = queue.poll();
            for (int[] p : points[i]) {
                // p0 坐标  p1 方向
                if (!seen[p[0]] && p[1] == 1) {
                    queue.offer(p[0]);
                    seen[p[0]] = true;
                    count++;
                }

                if (!seen[p[0]] && p[1] == -1) {
                    queue.offer(p[0]);
                    seen[p[0]] = true;
                }
            }
        }
        return count;
    }

    public boolean canMeasureWater(int jug1Capacity, int jug2Capacity, int targetCapacity) {
        Deque<int[]> deque = new LinkedList<>();
        Set<Long> seen = new HashSet<>();
        deque.offer(new int[]{0, 0});
        while (!deque.isEmpty()) {
            int[] remain = deque.poll();
            int x = remain[0], y = remain[1];
            long hash = getHash(x, y);
            if (seen.contains(hash)) {
                continue;
            }
            seen.add(hash);
            if (x == targetCapacity || y == targetCapacity || x + y == targetCapacity) {
                return true;
            }
            deque.add(new int[]{jug1Capacity, y});
            deque.add(new int[]{x, jug2Capacity});
            deque.add(new int[]{0, y});
            deque.add(new int[]{x, 0});
            deque.add(new int[]{x - Math.min(x, jug2Capacity - y), y + Math.min(x, jug2Capacity - y)});
            deque.add(new int[]{x + Math.min(y, jug1Capacity - x), y - Math.min(y, jug1Capacity - x)});
        }
        return false;
    }

    private long getHash(int x, int y) {
        return x * 1000001 + y;
    }


    public int shortestPathLength(int[][] graph) {
        int N = graph.length;
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] seen = new boolean[N][1 << N];

        for (int i = 0; i < N; i++) {
            //节点 ，mask， dist 距离
            queue.add(new int[]{i, 1 << i, 0});
            seen[i][1 << i] = true;
        }

        while (!queue.isEmpty()) {
            int[] temp = queue.poll();
            int u = temp[0], mask = temp[1], dist = temp[2];
            if (mask == (1 << N) - 1) {
                return dist;
            }

            for (int v : graph[u]) {
                int maskV = mask | (1 << v);
                if (!seen[v][maskV]) {
                    queue.offer(new int[]{v, maskV, dist + 1});
                    seen[v][maskV] = true;
                }
            }
        }
        return -1;
    }


}
