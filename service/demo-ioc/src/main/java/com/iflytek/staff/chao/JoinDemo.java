package com.iflytek.staff.chao;

/**
 * @author : hamilton
 * @Description: join 异步阻塞
 * @date Date : 2022年11月14日 13:37
 */
public class JoinDemo {

    public static final int SLEEP_GAP = 500 ;

    public static String getCurThreadName(){
        return Thread.currentThread().getName() ;
    }

    static class HotWaterThread extends Thread {
        public HotWaterThread(){
            super("** 烧水 -Thread");
        }

        public void run(){
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
        }
    }

    static class WashThread extends Thread {
        public WashThread(){
            super("** 清洗 -Thread");
        }

        public void run(){
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
        }
    }

    public static void main(String[] args) {
        Thread hThread = new HotWaterThread();
        Thread wThread = new WashThread();
        hThread.start();
        wThread.start();
        try {
            // 合并烧水 县城
            hThread.join();
            // 合并清洗 线程
            wThread.join();

            Thread.currentThread().setName("主线程");
            Logger.info("泡茶喝");

        } catch (InterruptedException e) {
            Logger.info(getCurThreadName() +" 发生异常被中断 ");
        }

        Logger.info(getCurThreadName() + "运行结束 ");
    }
}
