package com.iflytek.staff.chao.structure.scene.loadbalance;

import java.util.Random;

/**
 * @author : wangchaodee
 * @Description: 服务节点
 */
public class Server {

    // 代表server 的唯一标识， 模拟 ， 要求不重复， 相当于ip 或 uri
    private int id ;

    private int processed;

    // 假定可设置 100 ~ 1000 范围
    private int maxConnect ;

    private int currentConnect ;

    public Server(int id) {
        this.id = id ;
        this.processed = 0;
        this.maxConnect = 100 ;
    }

    public Server(int id ,int maxConnect) {
        this.id = id ;
        this.processed = 0;
        if(maxConnect>1000){
            this.maxConnect = 1000 ;
        }else if(maxConnect<100) {
            this.maxConnect = 100 ;
        }else {
            this.maxConnect = maxConnect ;
        }

    }

    public void handle(Request request){
        processed++;
    }

    /**
     * 获取已处理的连接数量
     * @return
     */
    public int getProcessed(){
        return processed;
    }

    /**
     * 模拟返回 当前连接的数量  ,相当于监控检查后上报
     * @return
     */
    public int refreshCurrentConnect(Random random){
        currentConnect = random.nextInt(maxConnect);
        return currentConnect;
    }

    public int getCurrentConnect(){
        return currentConnect;
    }

    public void increaseCurrentConnect(){
         ++currentConnect;
    }

    /**
     *  服务器的最大处理链接  用于加权
     * @return
     */
    public int getMaxConnect() {
        return maxConnect;
    }

    public void setMaxConnect(int maxConnect) {
        this.maxConnect = maxConnect;
    }

    public int getId() {
        return id;
    }
}
