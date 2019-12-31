# Xxstudy
<h1>并发编程<br>
1.文件夹中的p2.wn是wait/notify/notifyAll的example，假设有Express类，里面有km和site两个属性，当km>100或者是site=BeiJing时进行通知，
在等待方法中进行判断，条件符合则进行业务逻辑处理。<br>
2.在Test类中分别实现检查km和site的线程，最后调用改变km或者是site方法，如果change使用的是notify，则只会在所有线程中找到一个进行通知，
如果被通知的线程不符合，则继续wait，信号不会继续传输，如果使用notifyAll，则会通知所有线程，不符合的继续wait，符合的执行业务逻辑。
