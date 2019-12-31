package com.gzy.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

public class SumArray {
    private static class SumTask extends RecursiveTask<Integer>{
        private final static int THRESHOLD = MakeArray.ARRAY_LENGTH / 10;
        private int[] src;
        private int fromIndex;
        private int toIndex;

        public SumTask(int[] src, int fromIndex, int toIndex){
            this.src = src;
            this.fromIndex = fromIndex;
            this.toIndex = toIndex;
        }

        @Override
        protected Integer compute() {
            if(toIndex - fromIndex < THRESHOLD){
                int count = 0;
                for(int i = fromIndex; i <= toIndex; i++){
                    try {
                        TimeUnit.MILLISECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count = count + src[i];
                }
                return count;
            }else{
                int mid = (fromIndex + toIndex) / 2;
                SumTask left = new SumTask(src, fromIndex, mid);
                SumTask right = new SumTask(src, mid+1, toIndex);
                invokeAll(left, right);
                return left.join() + right.join();
            }
        }

        public static void main(String[] args) {
            ForkJoinPool pool = new ForkJoinPool();
            int[] src = MakeArray.makeArray();
            SumTask innerFind = new SumTask(src, 0, src.length-1);
            long start = System.currentTimeMillis();
            pool.invoke(innerFind);
            System.out.println("Task is Running..");
            System.out.println("The count is "+innerFind.join()+" spend time:"+(System.currentTimeMillis()-start)+"ms");
        }
    }


}
