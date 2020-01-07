package com.gzy.bq;

import java.util.concurrent.DelayQueue;

public class FetchOrder implements Runnable {

    private DelayQueue<ItemVo<Order>> queue;

    public FetchOrder(DelayQueue<ItemVo<Order>> queue){
        super();
        this.queue = queue;
    }

    @Override
    public void run() {
        while(true){
            try{
                ItemVo<Order> item = queue.take();
                Order order = (Order) item.getDate();
                System.out.println("get from queue:"+order.getOrderNo());
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
