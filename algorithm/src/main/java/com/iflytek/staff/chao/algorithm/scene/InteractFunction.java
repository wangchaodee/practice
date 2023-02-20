package com.iflytek.staff.chao.algorithm.scene;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author : wangchaodee
 * @Description: xxx
 * @date Date : 2023年02月19日 17:24
 */
public class InteractFunction {

    /**
     * 1237. 找出给定方程的正整数解
     * @param customfunction
     * @param z
     * @return
     */
    public List<List<Integer>> findSolution(CustomFunction customfunction, int z) {
        List<List<Integer>> ans = new ArrayList<>() ;
        for (int x = 1; x <= z; x++) {
            for (int y = z; y >=1 ; y--) {
                if(customfunction.f(x,y) == z ){
                    ans.add(Arrays.asList(x,y));
                }
            }
        }
        return ans ;
    }

    public List<List<Integer>> findSolution_2(CustomFunction customfunction, int z) {
        List<List<Integer>> ans = new ArrayList<>() ;
        for (int x = 1; x <= z; x++) {
            int yl = 1 , yr = z ;
            while (yl<=yr){
                int m =( yr + yl) >> 1 ;
                int res = customfunction.f(x,m);
                if( res== z ){
                    ans.add(Arrays.asList(x,m));
                    break;
                }
                if(res < z ){
                    yl=m+1;
                }else {
                    yr = m-1;
                }
            }
        }
        return ans ;
    }

      // This is the custom function interface.
              // You should not implement it, or speculate about its implementation
interface CustomFunction {
      // Returns f(x, y) for any given positive integers x and y.
              // Note that f(x, y) is increasing with respect to both x and y.
              // i.e. f(x, y) < f(x + 1, y), f(x, y) < f(x, y + 1)
               int f(int x, int y);
  };
}
