package com.gzy.forkjoin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class FindDirsFiles extends RecursiveAction {
    private File path;

    public FindDirsFiles(File path){
        this.path = path;
    }
    @Override
    protected void compute() {
        List<FindDirsFiles> subTasks = new ArrayList<>();
        File[] files = path.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    subTasks.add(new FindDirsFiles(file));
                } else {
                    if (file.getAbsolutePath().endsWith("txt")) {
                        System.out.println("文件：" + file.getAbsolutePath());
                    }
                }
            }
            if (!subTasks.isEmpty()) {
                for (FindDirsFiles subTask : invokeAll(subTasks)) {
                    subTask.join();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ForkJoinPool pool = new ForkJoinPool();
        FindDirsFiles task = new FindDirsFiles(new File("D:/"));
        pool.execute(task);
        System.out.println("Task is Running...");
        Thread.sleep(1);
        int otherWork = 0;
        for(int i = 0; i < 100; i++){
            otherWork = otherWork + i;
        }
        System.out.println("otherWork="+otherWork);
        task.join();
        System.out.println("Task end");
    }
}
