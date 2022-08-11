package com.iflytek.staff.chao.structure;

import java.util.LinkedList;
import java.util.Queue;

public class WallsAndGates {

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

        //  上，下  ,左，右，
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int step = 0;
        while (!queue.isEmpty()) {
            step++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] node = queue.poll();
                for (int[] dire : directions) {
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

}
