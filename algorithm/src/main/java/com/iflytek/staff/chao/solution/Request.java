package com.iflytek.staff.chao.solution;

/**
 * @author : wangchaodee
 * @Description: xxx
 * @date Date : 2023年02月16日 17:14
 */
// 代表请求
public class Request implements Cloneable {

    // 模拟地域 或  userType 类型
    private String area ;

    // 地址  以便后续做一致性哈希映射
    private int ip ;


    protected Request clone()  {
        try {
            return (Request) super.clone();
        } catch (CloneNotSupportedException e) {
            return new Request();
        }
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setIp(int ip) {
        this.ip = ip;
    }

    public String getArea() {
        return area;
    }

    public int getIp() {
        return ip;
    }
}
