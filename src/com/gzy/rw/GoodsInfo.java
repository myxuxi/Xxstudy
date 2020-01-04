package com.gzy.rw;
//商品的实体类
public class GoodsInfo {
    private String name;
    private double totalMoney;
    private int storeNumber;

    public GoodsInfo(String name, double totalMoney, int storeNumber){
        this.name = name;
        this.totalMoney = totalMoney;
        this.storeNumber = storeNumber;
    }

    public double getTotalMoney(){
        return totalMoney;
    }

    public int getStoreNumber(){
        return storeNumber;
    }

    public void changeNumber(int sellNumber){
        this.totalMoney += sellNumber * 25;
        this.storeNumber -= sellNumber;
    }
}
