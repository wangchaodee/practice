package com.iflytek.staff.chao;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author : hamilton
 * @Description: Future 异步调用
 * @date Date : 2022年11月14日 14:19
 */
public class FutureDemo {

    public static final int SLEEP_GAP = 500 ;

    public static String getCurThreadName(){
        return Thread.currentThread().getName() ;
    }

    static class HotWaterJob implements Callable<Boolean> {

        public Boolean  call() throws Exception{
            try{
                Logger.info("洗好水壶");
                Logger.info("灌上凉水");
                Logger.info("放在火上");
                // 休眠 代表烧水
                Thread.sleep(SLEEP_GAP);
                Logger.info("水开了");
            } catch (InterruptedException e) {
                Logger.info("发生异常被中断 ");
            }
            Logger.info("烧水运行结束  ");
            return true ;
        }
    }

    static class WashJob implements Callable<Boolean> {

        public Boolean call() throws Exception {
            try{
                Logger.info("洗茶壶");
                Logger.info("洗茶杯");
                Logger.info("拿茶叶");
                // 休眠 代表清洗
                Thread.sleep(SLEEP_GAP);
                Logger.info("洗完了");
            } catch (InterruptedException e) {
                Logger.info("发生异常被中断 ");
            }
            Logger.info("清洗运行结束  ");
            return true ;
        }
    }

    public static void main(String[] args) {

        Callable<Boolean>  hJob = new HotWaterJob();
        FutureTask<Boolean> hTask = new FutureTask<>(hJob);
        Thread hThread = new Thread( hTask,"** 烧水-Thread");

        Callable<Boolean>  wJob = new WashJob();
        FutureTask<Boolean> wTask = new FutureTask<>(wJob);
        Thread wThread = new Thread( wTask,"** 清洗-Thread");

        hThread.start();
        wThread.start();
        Thread.currentThread().setName("主线程");
        try {

            boolean waterOk = hTask.get() ;
            boolean cupOk = wTask.get();

            drinkTea(waterOk, cupOk);



        } catch (InterruptedException e) {
            Logger.info(getCurThreadName() +" 发生异常被中断 ");
        } catch (ExecutionException e) {
            Logger.info(getCurThreadName() +" 发生异常被中断  2 ");
        }

        Logger.info(getCurThreadName() + "运行结束 ");
    }

    private static void drinkTea(boolean waterOk, boolean cupOk) {
        if(waterOk && cupOk) {

            Logger.info("泡茶喝");
        }else if(!waterOk){
            Logger.info("烧水失败 ，没茶喝");
        }else if(!cupOk){
            Logger.info("杯子洗不了，没茶喝");
        }
    }
}
