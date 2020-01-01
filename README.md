# Xxstudy
<h1>并发编程<br>
1.package p2.wn<br>
(1)文件夹中的p2.wn是wait/notify/notifyAll的example，假设有Express类，里面有km和site两个属性，当km>100或者是site=BeiJing时进行通知，
在等待方法中进行判断，条件符合则进行业务逻辑处理。<br>
(2)在Test类中分别实现检查km和site的线程，最后调用改变km或者是site方法，如果change使用的是notify，则只会在所有线程中找到一个进行通知，
如果被通知的线程不符合，则继续wait，信号不会继续传输，如果使用notifyAll，则会通知所有线程，不符合的继续wait，符合的执行业务逻辑。
<p><h1>
2.package forkjoin
(1)MakeArray中定义了一个数组，并赋予每一个元素随机值。<br>
(2)SumNormal类是一个单线程计算数组元素的累加总值，SumArray类是一个fork_join线程并发工具类，核心思想是分而治之，当问题规模小于一个阈值时可以直接解决，如果不行则把问题规模继续缩小。<br>
Fork/Join的标准范式如下：<br>
![image](https://github.com/myxuxi/Xxstudy/blob/master/forkjoin.png)
