package com.iflytek.staff.chao.structure.scene;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author : wangchaodee
 * @Description: 519. 随机翻转矩阵
 */
public class RandomFlipMatrix {

    int m ,n ;
    int total ;
    Map<Integer, Integer> map = new HashMap<>();
    Random random = new Random() ;

    public RandomFlipMatrix(int m, int n) {
       this.m = m ;
       this.n =n ;
        this.total= m*n;
    }

    public int[] flip() {
        // 取得total 内的随机一个值
        int x = random.nextInt(total);
        total--;
        // 如果之前有映射 则对应 非x值 是 x 和另一个total
        int idx = map.getOrDefault(x,x);
        // 映射为 上限的值
        map.put(x ,map.getOrDefault(total,total));
        return new int[]{idx/n,idx%n};
    }

    public void reset() {
        this.total= m*n;
        map.clear();;
    }
}
