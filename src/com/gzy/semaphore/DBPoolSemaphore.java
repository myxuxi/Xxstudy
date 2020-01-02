package com.gzy.semaphore;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;

//一个数据库连接池的实现
public class DBPoolSemaphore {
    private final static int POOL_SIZE = 10;
    private final Semaphore useful,useless; //useful表现可用的，useless表示已用

    public DBPoolSemaphore() {
        this.useful = new Semaphore(POOL_SIZE);
        this.useless = new Semaphore(0);
    }

    //存放数据库连接池的容器
    private static LinkedList<Connection> pool = new LinkedList<>();
    //初始化连接池
    static{
        for(int i = 0; i < POOL_SIZE; i++){
            pool.addLast(SqlConnectImpl.fetchConnection())  ;
        }
    }

    //从数据库连接池拿
    public Connection takeConnection() throws InterruptedException {
        useful.acquire();
        Connection conn;
        synchronized(pool){
            conn = pool.removeFirst();
        }
        useless.release();
        return conn;
    }

    //归还连接
    public void returnConnect(Connection connection) throws InterruptedException {
        if(connection != null){
            System.out.println("当前有"+useful.getQueueLength()+"个线程等待数据库连接"
                +"可用连接数:"+useful.availablePermits());
            useless.acquire();
            synchronized (pool){
                pool.addLast(connection);
            }
            useful.release();
        }
    }
}
