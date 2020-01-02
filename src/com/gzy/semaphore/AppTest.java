package com.gzy.semaphore;

import java.sql.Connection;
import java.util.Random;

public class AppTest {
    private static DBPoolSemaphore dbPool = new DBPoolSemaphore();

    //业务线程
    private static class BusyThread extends Thread{
        @Override
        public void run() {
            Random r = new Random();
            long start = System.currentTimeMillis();
            try {
                Connection connect = dbPool.takeConnection();
                System.out.println("Thread_"+Thread.currentThread().getId()+"_获取数据库连接耗时："
                    +(System.currentTimeMillis()-start)+" ms");
                Thread.sleep(100+r.nextInt(100));
                System.out.println("查询数据库完成，归还连接");
                dbPool.returnConnect(connect);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        for(int i = 0; i <= 50; i++){
            Thread thread = new BusyThread();
            thread.start();
        }
    }
}
