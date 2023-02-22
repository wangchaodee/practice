package com.iflytek.staff.chao.solution.cache;

/**
 * @author : wangchaodee
 * @Description: 缓存管理接口 内部自动实现缓存失效功能
 * @date Date : 2023年02月14日 22:44
 */

/**
 * 模拟缓存
 * 用int类型的数据，
 * key代表数据标识，
 * value代表数据内容，
 * key和value的值可以一样，不影响模拟
 *
 * 缓存设置 capacity 表示容量大小
 */
public interface Cache {

     /**
      * get 方法代表读缓存
      * @param key
      * @return
      */
     int get(int key) ;

     /**
      * put方法代表写缓存
      * @param key
      * @param value
      */
     void put(int key, int value) ;
}
