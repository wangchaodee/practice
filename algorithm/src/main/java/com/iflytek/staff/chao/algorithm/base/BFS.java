package com.iflytek.staff.chao.algorithm.base;

import com.iflytek.staff.chao.util.DirectionUtil;

import java.util.*;

/**
 * 广度优先算法
 * 在程序实现 BFS 时需要考虑以下问题：
 * <p>
 * 队列：用来存储每一轮遍历得到的节点；
 * 标记：对于遍历过的节点，应该将它标记，防止重复遍历。
 */
public class BFS {

    /**
     * 方法三 通过增加过渡单词 如 d*g  ，减少构建图的复杂度  主要减少比较判断
     */
    Map<String, Integer> wordId = new HashMap<>();
    List<List<Integer>> edge = new ArrayList<>();
    int sequence = 0;

    /**
     * 墙与门
     * -1 表示墙或是障碍物
     * 0 表示一扇门
     * 计算该房间到最近的门的距离
     *
     * @param rooms
     */
    public void wallsAndGates(int[][] rooms) {
        int rows = rooms.length;
        int cols = rooms[0].length;
        if (rows == 1 && cols == 1) {
            return;
        }

        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (rooms[i][j] == 0) queue.add(new int[]{i, j});
                if (rooms[i][j] <= 0) visited[i][j] = true;
            }
        }

        int step = 0;
        while (!queue.isEmpty()) {
            step++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] node = queue.poll();
                for (int[] dire : DirectionUtil.directions) {
                    int row = node[0] + dire[0];
                    int col = node[1] + dire[1];
                    boolean indexValid = (row >= 0 && row < rows && col >= 0 && col < cols);
                    if (indexValid) {
                        if (!visited[row][col]) {
                            visited[row][col] = true;
                            rooms[row][col] = step;
                            queue.add(new int[]{row, col});
                        }
                    }

                }
            }
        }


    }

    /**
     * 1091. 二进制矩阵中的最短路径
     *
     * @param grid
     * @return
     */
    public int shortestPathBinaryMatrix(int[][] grid) {

        int n = grid.length;
        if (grid[0][0] == 1) return -1;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        grid[0][0] = 1;
        int distance = 0;
        while (!queue.isEmpty()) {
            distance++;

            int size = queue.size();
            for (int k = 0; k < size; k++) {
                int[] xy = queue.poll();
                int sr = xy[0];
                int sc = xy[1];
                if (sr == n - 1 && sc == n - 1) return distance;
                for (int i = 0; i < 8; i++) {
                    int srr = sr + DirectionUtil.eightDots[i][0];
                    int scc = sc + DirectionUtil.eightDots[i][1];
                    if (0 <= srr && srr < n && 0 <= scc && scc < n && grid[srr][scc] == 0) {
                        queue.offer(new int[]{srr, scc});
                        grid[srr][scc] = 1;
                    }
                }
            }
        }
        return -1;
    }

    /**
     * 279. 完全平方数
     *
     * @param n
     * @return
     */
    public int numSquares(int n) {
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> seen = new HashSet<>();

        queue.add(0);
        seen.add(0);
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            level++;
            for (int i = 0; i < size; i++) {
                int pre_num = queue.poll();
                for (int j = 1; j < n; j++) {
                    int cur_num = pre_num + j * j;
                    if (cur_num == n) {
                        return level;
                    } else if (cur_num > n) {
                        break;
                    }
                    if (seen.contains(cur_num)) {
                        continue;
                    }

                    queue.add(cur_num);
                    seen.add(cur_num);
                }
            }
        }

        return level;
    }

    /**
     * 从n 按照层遍历的方式 找到到达0 的最短路径
     */
    public int numSquares2(int n) {

        List<Integer> squares = getSmallSquare(n);
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> seen = new HashSet<>();

        queue.add(n);
        seen.add(n);
        int level = 0;
        while (!queue.isEmpty()) {
            level++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                for (int s : squares) {
                    int next = cur - s;
                    if (next < 0) break;
                    if (next == 0) return level;
                    if (seen.contains(next)) {
                        continue;
                    }
                    seen.add(next);
                    queue.add(next);
                }
            }
        }

        return level;
    }

    List<Integer> getSmallSquare(int n) {
        List<Integer> ans = new LinkedList<>();
        int start = 1;
        int diff = 3;
        while (start <= n) {
            ans.add(start);
            start += diff;
            diff += 2;
        }
        return ans;
    }

    /**
     * 127. 单词接龙   以替换换字母 建立关联
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        HashSet<String> wordset = new HashSet<>(wordList);
        if (wordset.size() == 0 || !wordset.contains(endWord)) return 0;

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);

        Set<String> seen = new HashSet<>();
        seen.add(beginWord);

        int step = 1;
        while (!queue.isEmpty()) {
            step++;
            int sz = queue.size();
            for (int i = 0; i < sz; i++) {

                String temp = queue.poll();

                for (int j = 0; j < temp.length(); j++) {
                    char[] chs = temp.toCharArray();

                    for (char k = 'a'; k <= 'z'; k++) {
                        if (chs[j] == k) continue;
                        chs[j] = k;
                        String w = String.valueOf(chs);

                        if (w.equals(endWord)) return step;

                        if (wordset.contains(w) && !seen.contains(w)) {
                            queue.offer(w);
                            seen.add(w);
                        }
                    }
                }
            }
        }

        return 0;
    }

    /**
     * 判断单词之间是否有关联  构建图之后再次搜索
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        wordList.add(beginWord);
        int N = wordList.size();
        int start = N - 1;
        int end = 0;
        while (end < N && !endWord.equals(wordList.get(end))) {
            end++;
        }
        if (end == N) return 0;
        List<Integer>[] graphic = buildGraphic(wordList);
        return getShortestPath(graphic, start, end);
    }

    private int getShortestPath(List<Integer>[] graphic, int start, int end) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);

        boolean[] seen = new boolean[graphic.length];
        seen[start] = true;

        int step = 0;
        while (!queue.isEmpty()) {
            step++;
            int sz = queue.size();
            for (int i = 0; i < sz; i++) {
                Integer cur = queue.poll();
                for (int next : graphic[cur]) {
                    if (next == end) return step;
                    if (seen[next]) continue;
                    queue.offer(next);
                    seen[next] = true;
                }
            }
        }
        // 无路径 返回 0
        return 0;
    }

    private List<Integer>[] buildGraphic(List<String> wordList) {
        int N = wordList.size();
        List<Integer>[] graphic = new List[N];
        for (int i = 0; i < N; i++) {
            graphic[i] = new ArrayList<>();
            for (int j = 0; j < N; j++) {
                if (j != i && isConnect(wordList.get(i), wordList.get(j))) {
                    graphic[i].add(j);
                }
            }
        }
        return graphic;
    }

    private boolean isConnect(String word1, String word2) {
        int diffCnt = 0;
        for (int i = 0; i < word1.length() && diffCnt <= 1; i++) {
            if (word1.charAt(i) != word2.charAt(i)) diffCnt++;
        }
        return diffCnt == 1;
    }

    public int ladderLength3(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) return 0;
        for (String word : wordList) {
            addEdge(word);
        }
        addEdge(beginWord);
        int start = wordId.get(beginWord);
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);

        boolean[] seen = new boolean[wordId.size()];
        seen[start] = true;

        int end = wordId.get(endWord);

        int step = 0;
        while (!queue.isEmpty()) {
            step++;
            int sz = queue.size();
            for (int i = 0; i < sz; i++) {
                Integer cur = queue.poll();
                for (int next : edge.get(cur)) {
                    if (next == end) return step / 2 + 1;
                    if (seen[next]) continue;
                    queue.offer(next);
                    seen[next] = true;
                }
            }
        }
        // 无路径 返回 0
        return 0;
    }

    private void addEdge(String word) {
        addWord(word);
        int id1 = wordId.get(word);
        char[] array = word.toCharArray();
        for (int i = 0; i < word.length(); i++) {
            char tmp = array[i];
            array[i] = '*';
            String newWord = new String(array);
            addWord(newWord);
            int id2 = wordId.get(newWord);
            edge.get(id1).add(id2);
            edge.get(id2).add(id1);
            array[i] = tmp;
        }
    }

    private void addWord(String word) {
        if (!wordId.containsKey(word)) {
            wordId.put(word, sequence++);
            edge.add(new ArrayList<>());
        }
    }

    /**
     * 126. 单词接龙 II
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        wordList.add(beginWord);
        int N = wordList.size();
        int start = N - 1;
        int end = 0;
        while (end < N && !endWord.equals(wordList.get(end))) {
            end++;
        }

        List<List<String>> res = new ArrayList<>();
        if (end == N) return res;

        List<Integer>[] graphic = buildGraphic(wordList);
        Map<Integer, List<Integer>> links = new HashMap<>();

        int pathCnt = getShortestPath(graphic, start, end, links);
        if (pathCnt > 0) {
            Deque<String> path = new ArrayDeque<>();
            path.add(endWord);
            backTracking(end, start, wordList, path, res, links);
        }
        return res;
    }

    private int getShortestPath(List<Integer>[] graphic, int start, int end, Map<Integer, List<Integer>> links) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);

        Map<Integer, Integer> wordStep = new HashMap<>();

        int step = 0;
        boolean found = false;
        while (!queue.isEmpty()) {
            step++;
            int sz = queue.size();
            for (int i = 0; i < sz; i++) {
                Integer cur = queue.poll();

                for (int next : graphic[cur]) {
                    if (next == end) found = true;
                    // 同层出现  代表另一路径 同样的距离， 边的关联要加入 ， 如果不是意味路径长 ,边的关联就无须添加
                    if (wordStep.containsKey(next) && wordStep.get(next) == step) {
                        links.get(next).add(cur);
                    }
                    // 以前出现过的不需要再放入队列
                    if (wordStep.containsKey(next)) continue;

                    queue.offer(next);
                    wordStep.put(next, step);

                    links.putIfAbsent(next, new ArrayList<>());
                    links.get(next).add(cur);
                }
            }

            if (found) {
                return step;
            }
        }
        // 无路径 返回 0
        return 0;
    }

    private void backTracking(int cur, int end, List<String> wordList,
                              Deque<String> path, List<List<String>> res, Map<Integer, List<Integer>> links) {
        if (cur == end) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int next : links.get(cur)) {
            path.addFirst(wordList.get(next));
            backTracking(next, end, wordList, path, res, links);
            path.removeFirst();
        }
    }

    /**
     * 面试题13. 机器人的运动范围
     *
     * @param m
     * @param n
     * @param k
     * @return
     */
    public int movingCount(int m, int n, int k) {
        boolean[][] seen = new boolean[m][n];
        int cnt = 0;

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        seen[0][0] = true;
        while (!queue.isEmpty()) {

            cnt++;
            int[] node = queue.poll();
            for (int[] dire : DirectionUtil.directions) {
                int row = node[0] + dire[0];
                int col = node[1] + dire[1];
                boolean indexValid = (row >= 0 && row < m && col >= 0 && col < n);
                if (indexValid && seen[row][col] != true && check(row, col, k)) {
                    seen[row][col] = true;
                    queue.add(new int[]{row, col});
                }
            }
        }
        return cnt;
    }


    private boolean check(int i, int j, int k) {
        int cnt = 0;
        while (i > 0) {
            cnt += i % 10;
            i /= 10;
        }

        while (j > 0) {
            cnt += j % 10;
            j /= 10;
        }
        return cnt <= k;
    }


    /**
     * 815. 公交路线
     *
     * @param routes
     * @param source
     * @param target
     * @return
     */
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) return 0;
        int n = routes.length;
        // 公交是否有换乘
        boolean[][] edges = new boolean[n][n];
        Map<Integer, List<Integer>> sites = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int site : routes[i]) {
                List<Integer> lines = sites.getOrDefault(site, new ArrayList<>());
                for (int j : lines) {
                    edges[i][j] = edges[j][i] = true;
                }
                lines.add(i);
                sites.put(site, lines);
            }
        }

        int[] dis = new int[n];
        Arrays.fill(dis, -1);
        // 初始站 可能有多个公交线路
        Queue<Integer> queue = new LinkedList<>();
        for (int line : sites.get(source)) {
            dis[line] = 1;
            queue.offer(line);
        }

        // 广度优先方式 计算线路在第几次的搭乘上
        while (!queue.isEmpty()) {
            int a = queue.poll();
            for (int b = 0; b < n; b++) {
                // a  b  换乘
                if (edges[a][b] && dis[b] == -1) {
                    dis[b] = dis[a] + 1;
                    queue.offer(b);
                }
            }
        }

        // 用终点站 来查验 哪个线路是 最少换乘的
        int ret = Integer.MAX_VALUE;
        for (int line : sites.getOrDefault(target, new ArrayList<>())) {
            if (dis[line] == -1) continue;
            ret = Math.min(ret, dis[line]);
        }
        return ret == Integer.MAX_VALUE ? -1 : ret;
    }

    /**
     * 剑指 Offer II 111. 计算除法
     * @param equations
     * @param values
     * @param queries
     * @return
     */
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
            int nvars = 0 ;
            Map<String,Integer> variables = new HashMap<>();
            int n = equations.size();
        for (int i = 0; i < n; i++) {
            if(!variables.containsKey(equations.get(i).get(0))){
                variables.put(equations.get(i).get(0),nvars++);
            }
            if(!variables.containsKey(equations.get(i).get(1))){
                variables.put(equations.get(i).get(1),nvars++);
            }
        }

        List<Pair>[] edges = new List[nvars];
        for (int i = 0; i < nvars; i++) {
            edges[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            int idx = variables.get(equations.get(i).get(0)) , idy= variables.get(equations.get(i).get(1));
            edges[idx].add(new Pair(idy,values[i]));
            edges[idy].add(new Pair(idx,1/values[i]));
        }

        int queriesCount = queries.size() ;
        double[] ret = new double[queriesCount];
        for (int i = 0; i < queriesCount; i++) {
            List<String> query = queries.get(i);
            double result = -1.0 ;
            if(variables.containsKey(query.get(0)) && variables.containsKey(query.get(1)) ){
                int va = variables.get(query.get(0)) , vb = variables.get(query.get(1));
                if(va == vb) {
                    result = 1.0 ;
                }else{
                    Queue<Integer> points = new LinkedList<>();
                    points.offer(va);
                    double[] ratios = new double[nvars];
                    Arrays.fill(ratios,-1.0);
                    ratios[va] = 1.0;

                    while (!points.isEmpty() && ratios[vb] <0){
                        int x = points.poll();
                        for(Pair pair : edges[x]){
                            int y = pair.index;
                            double var = pair.value ;
                            if(ratios[y] < 0){
                                ratios[y] = ratios[x] * var;
                                points.offer(y);
                            }
                        }
                    }
                    result = ratios[vb];
                }
            }
            ret[i] = result ;
        }
        return ret;
    }

    class Pair{
        int index ;
        double value;
        Pair(int index,double value){
            this.index = index ;
            this.value = value ;
        }
    }

    /**
     * 剑指 Offer II 114. 外星文字典
     * @param words
     * @return
     */
    Map<Character ,List<Character>> edges = new HashMap<>();
    Map<Character,Integer> indegrees = new HashMap<>();
    boolean valid = true ;
    public String alienOrder(String[] words) {
        int length = words.length ;
        for(String word : words){
            int wl = word.length() ;
            for (int i = 0; i < wl; i++) {
                char c = word.charAt(i);
                edges.putIfAbsent(c,new ArrayList<>());
            }
        }

        for (int i = 1; i < length && valid ; i++) {
            addEdge(words[i-1] , words[i]);
        }
        if(!valid){
            return "";
        }
        Queue<Character> queue = new ArrayDeque<>();
        Set<Character> letters = edges.keySet();
        for(char u : letters){
            if(!indegrees.containsKey(u)){
                queue.offer(u);
            }
        }
        StringBuffer order = new StringBuffer();
        while (!queue.isEmpty()){
            char u = queue.poll();
            order.append(u);
            List<Character> afters = edges.get(u);
            for(char v : afters){
                indegrees.put(v, indegrees.get(v) -1);
                if(indegrees.get(v) ==0){
                    queue.offer(v);
                }
            }
        }
        return order.length() == edges.size()?order.toString():"";
    }

    private void addEdge(String pre ,String next){
        int l1 = pre.length() , l2 = next.length() , i = 0;
        int l = Math.min(l1,l2)  ;
        while (i<l){
            char c1 = pre.charAt(i) , c2 = next.charAt(i);
            if(c1 !=c2){
                edges.get(c1).add(c2);
                indegrees.put(c2, indegrees.getOrDefault(c2,0)+1);
                break;
            }
            i++;
        }

        if(i==l && l1 > l2){
            valid = false ;
        }
    }
}
