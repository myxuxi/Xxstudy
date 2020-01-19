package jvm;

/**
 * 这段代码演示了：
 * 1.对象可以在被GC时进行自我拯救
 * 2.这种自救的机会只有一次，Finalizer方法只会被系统自动调用一次
 * 3.finalizer()能做的事情，try-finally能做的更好，更及时（由于finalizer()的优先级很低）所以不推荐使用
 */
public class FinalizeEscapeGC {
    public static FinalizeEscapeGC SAVE_HOOK = null;

    public void isAlive(){
        System.out.println("yes, i am still alive :)");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed!");
        FinalizeEscapeGC.SAVE_HOOK = this;
    }

    public static void main(String[] args) throws InterruptedException {
        SAVE_HOOK = new FinalizeEscapeGC();
        //对象第一次成功拯救自己
        SAVE_HOOK = null;
        System.gc();
        //因为Finalizer方法优先级很低，暂停0.5秒等待
        Thread.sleep(500);
        if(SAVE_HOOK != null){
            SAVE_HOOK.isAlive();
        }else{
            System.out.println("no, i am dead :(");
        }

        //下面与上面的代码一样，但是Finalizer方法只能被系统调用一次，所以它仍然会被回收
        SAVE_HOOK = null;
        System.gc();
        Thread.sleep(500);
        if(SAVE_HOOK != null){
            SAVE_HOOK.isAlive();
        }else{
            System.out.println("no, i am dead :(");
        }
    }
}
