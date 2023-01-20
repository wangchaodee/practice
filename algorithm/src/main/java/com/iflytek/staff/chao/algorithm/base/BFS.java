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

    /**
     * 方法三 通过增加过渡单词 如 d*g  ，减少构建图的复杂度  主要减少比较判断
     */
    Map<String, Integer> wordId = new HashMap<>();
    List<List<Integer>> edge = new ArrayList<>();
    int sequence = 0;

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

        Map<Integer,Integer> wordStep = new HashMap<>();

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
                    if(wordStep.containsKey(next) && wordStep.get(next)==step) {
                        links.get(next).add(cur);
                    }
                    // 以前出现过的不需要再放入队列
                    if(wordStep.containsKey(next))  continue;

                    queue.offer(next);
                    wordStep.put(next,step);

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

}
