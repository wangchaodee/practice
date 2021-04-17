package com.iflytek.staff.chao.array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

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

}
