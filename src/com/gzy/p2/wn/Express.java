package com.gzy.p2.wn;

/**
 * 快递实体类
 */
public class Express {
    public final static String CITY = "ShangHai";
    private int km; //里程数
    private String site;    //到达地点

    public Express(){
    }

    public Express(int km, String site){
        this.km = km;
        this.site = site;
    }

    //让里程数发生变化，通知wait状态并进行业务处理
    public synchronized void changeKm(){
        this.km = 101;
        notifyAll();
        //其它的业务方法
    }

    //让地点变化，通知wait状态并进行业务处理
    public synchronized void changSite(){
        this.site = "BeiJing";
        notifyAll();
    }

    public synchronized void waitKm(){
        while(this.km <= 100){
            try {
                wait();
                System.out.println("check km thread["+Thread.currentThread().getId()+"] is be notifed.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("the km is"+this.km+",I will change db.");
    }

    public synchronized void waitSite(){
        while(CITY.equals(this.site)){
            try {
                wait();
                System.out.println("check site thread["+Thread.currentThread().getId()+"] is be notifed.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("the site is"+this.site+",I will call user");
    }
}
