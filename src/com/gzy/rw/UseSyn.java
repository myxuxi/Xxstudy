package com.gzy.rw;

//用内置锁来实现商品服务接口
public class UseSyn implements GoodsService{
    private GoodsInfo goodsInfo;

    public UseSyn(GoodsInfo goodsInfo){
        this.goodsInfo = goodsInfo;
    }

    @Override
    public synchronized GoodsInfo getNum() {
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this.goodsInfo;
    }

    @Override
    public synchronized void setNum(int number) {
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        goodsInfo.changeNumber(number);
    }
}
