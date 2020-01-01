package com.gzy.tools;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;

public class UseCyclicBarrier {
    private static CyclicBarrier barrier = new CyclicBarrier(5);
    private static ConcurrentHashMap<String, Long> resultMap = new ConcurrentHashMap<>();

    //工作线程
    private static class SubThread implements Runnable{

        @Override
        public void run() {
            long id = Thread.currentThread().getId(); //线程本身处理结果
            resultMap.put(Thread.currentThread().getId()+"",id);
            Random r = new Random(); //随机决定工作线程的是否睡眠
            if(r.nextBoolean()){
                try {
                    Thread.sleep(2000+id);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread_"+id+" ... do something");
            }
            System.out.println(id+"...is await");
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(1000+id);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
                System.out.println("Thread_"+id+" ...do its business");
        }
    }

    public static void main(String[] args) {
        for(int i = 0; i <= 4; i++){
            Thread thread = new Thread(new SubThread());
            thread.start();
        }
    }
}
