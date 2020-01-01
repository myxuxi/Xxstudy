package com.gzy.tools;

import java.util.concurrent.CountDownLatch;

public class UseCountDownLawn {
    static CountDownLatch latch = new CountDownLatch(3);

    //初始化框架线程
    private static class InitThread implements Runnable{

        @Override
        public void run() {
            System.out.println("Init Spring Family");
            latch.countDown();
        }
    }

    //业务线程
    private static class BusyThread implements Runnable{

        @Override
        public void run() {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Start working");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Init mysqsl service");
                latch.countDown();
            }
        }).start();
        new Thread(new BusyThread()).start();
        for(int i = 0; i < 2; i++){
            Thread thread = new Thread(new InitThread());
            thread.start();
        }
        latch.await();
        System.out.println("Main Thread is running");
    }
}
