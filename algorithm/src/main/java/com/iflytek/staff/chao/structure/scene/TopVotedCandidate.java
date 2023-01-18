package com.iflytek.staff.chao.structure.scene;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : hamilton
 * @Description: 在线选举 查询得票多的人
 * @date Date : 2022年07月27日 17:51
 */
public class TopVotedCandidate {

    private int[] times;
    private Map<Integer, Integer> voteCount;
    private int[] tops;

    public TopVotedCandidate(int[] persons, int[] times) {
        int N = persons.length;
        tops = new int[N];
        voteCount = new HashMap<>();
        voteCount.put(-1, -1);
        int top = -1;
        for (int i = 0; i < N; i++) {
            int cur = voteCount.getOrDefault(persons[i], 0) + 1;
            if (cur >= voteCount.get(top)) {
                top = persons[i];
            }
            voteCount.put(persons[i], cur);
            tops[i] = top;
        }
        this.times = times;
    }

    public int q(int t) {
        int N = times.length;
        int l = 0, r = N - 1;
        while (l < r) {
            int m = (r - l + 1) / 2 + l;
            if (times[m] <= t) {
                l = m;
            } else {
                r = m - 1;
            }
        }
        return tops[l];
    }
}
