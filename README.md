# Xxstudy
并发编程
======
1.package p2.wn
----
(1)文件夹中的p2.wn是wait/notify/notifyAll的example，假设有Express类，里面有km和site两个属性，当km>100或者是site=BeiJing时进行通知，
在等待方法中进行判断，条件符合则进行业务逻辑处理。<br>
(2)在Test类中分别实现检查km和site的线程，最后调用改变km或者是site方法，如果change使用的是notify，则只会在所有线程中找到一个进行通知，
如果被通知的线程不符合，则继续wait，信号不会继续传输，如果使用notifyAll，则会通知所有线程，不符合的继续wait，符合的执行业务逻辑。

2.package forkjoin
----
(1)MakeArray中定义了一个数组，并赋予每一个元素随机值。<br>
(2)SumNormal类是一个单线程计算数组元素的累加总值，SumArray类是一个fork_join线程并发工具类，核心思想是分而治之，当问题规模小于一个阈值时可以直接解决，如果不行则把问题规模继续缩小。<br>
Fork/Join的标准范式如下：
![image](https://github.com/myxuxi/gzy/blob/master/forkjoin.png)
(3)如果中间没有Thread.sleep，那么使用单线程的效率更高，因为多线程使用CPU时的轮询机制，在线程切换间需要进行上下文切换，更加耗时。

3.package tools
----
(1)并发工具中的CountDownLatch,可以等待一个线程完全结束后再执行其它线程，加强版的join(),代码内容是先运行启动Spring全家桶的线程，再执行mysql线程，然后是主线程后再执行业务代码，定义CountDownLatch时要初始化count，需要先执行的线程执行后调用latch.countDown(),使count-1，当count=0时才可以执行await(),否则将一直等待。<br>
(2)并发工具中的CyclicBarrier,指的是屏障阻塞了一组线程，需要等待所有的线程到达后才能继续执行，与CountDownLatch的区别是CountDownLatch的放行是由第三者控制的，CyclicBarrier放行是由一组线程本身控制的，前者放行条件>=线程数，后者放行条件=线程数。
