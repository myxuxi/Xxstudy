package com.gzy.p2.wn;

/**
 * 测试wait/notify/notifyAll
 */
public class TestWN {
    private static Express express = new Express(0,Express.CITY);
    //检查里程数的变化，不满足条件则一直等待
    private static class CheckKm extends Thread{
        @Override
        public void run() {
            express.waitKm();
        }
    }

    //检查地点变化，不满足条件则一直等待
    private static class CheckSite extends Thread{
        @Override
        public void run() {
            express.waitSite();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for(int i = 0; i < 3; i++){
            new CheckSite().start();
        }
        for(int i = 0; i < 3; i++){
            new CheckKm().start();
        }
        Thread.sleep(1000);
        express.changSite();
    }
}
