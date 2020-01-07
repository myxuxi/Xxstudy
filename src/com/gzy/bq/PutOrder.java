package com.gzy.bq;

import java.util.concurrent.DelayQueue;

//将订单放入队列
public class PutOrder implements Runnable{
    private DelayQueue<ItemVo<Order>> queue;
    public PutOrder(DelayQueue<ItemVo<Order>> queue){
        super();
        this.queue = queue;
    }

    @Override
    public void run() {
        //5秒到期
        Order orderTb = new Order("Tb12345",366);
        ItemVo<Order> itemTb = new ItemVo<Order>(5000,orderTb);
        queue.offer(itemTb);
        System.out.println("订单5秒后到期："+orderTb.getOrderNo());

        //8秒到期
        Order orderJd = new Order("Jd54321",366);
        ItemVo<Order> itemJd = new ItemVo<Order>(8000,orderJd);
        queue.offer(itemJd);
        System.out.println("订单8秒后到期："+orderJd.getOrderNo());
    }
}
