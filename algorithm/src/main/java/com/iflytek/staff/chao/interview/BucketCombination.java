package com.iflytek.staff.chao.interview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author : wangchaodee
 * @Description: 6个颜色不一的通的组合
 */
public class BucketCombination {

    public static void main(String[] args) {

        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            path.add(i);
        }

        backtrack(path, 6, ans, 0);

        System.out.println("种类数：" + ans.size());
//        for (List<Integer> sequence : ans) {
//            for (Integer e : sequence) {
//                System.out.print(e);
//            }
//            System.out.println();
//        }
    }

    public static void backtrack(List<Integer> path, int n, List<List<Integer>> ans, int index) {
        if (index == n) {
            ans.add(new ArrayList<>(path));
            return;
        }

        for (int j = index; j < n; j++) {
            Collections.swap(path, index, j);
            // 不相等时继续
            if (path.get(index) != index) {
                backtrack(path, n, ans, index + 1);
            }
            Collections.swap(path, index, j);
        }
    }
}
