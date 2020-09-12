package ch02.test3;
/*
    synchronized 同步代码块
 */
public class Task {
    private String getData1;
    private String getData2;
    public void doLongTimeTask() {
        try{
            System.out.println("begin task");
            Thread.sleep(3000);
            String tmpGetData1 = "长时间执行任务返回结果1 threadname=" + Thread.currentThread().getName();
            String tmpGetData2 = "长时间执行任务返回结果2 threadname=" + Thread.currentThread().getName();
            synchronized (this) {
                getData1 = tmpGetData1;
                getData2 = tmpGetData2;
            }
            System.out.println(getData1);
            System.out.println(getData2);
            System.out.println("end task");
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class CommonUtils {
    public static long beginTime1;
    public static long endTime1;
    public static long beginTime2;
    public static long endTime2;
}

class MyThread1 extends Thread {
    private Task task;

    public MyThread1(Task task) {
        this.task = task;
    }

    @Override
    public void run() {
        super.run();
        CommonUtils.beginTime1 = System.currentTimeMillis();
        this.task.doLongTimeTask();
        CommonUtils.endTime1 = System.currentTimeMillis();
    }
}

class MyThread2 extends Thread {
    private Task task;

    public MyThread2(Task task) {
        this.task = task;
    }

    @Override
    public void run() {
        super.run();
        CommonUtils.beginTime2 = System.currentTimeMillis();
        this.task.doLongTimeTask();
        CommonUtils.endTime2 = System.currentTimeMillis();
    }
}

class Run {
    public static void main(String[] args) {
        Task task = new Task();
        MyThread1 myThread1 = new MyThread1(task);
        myThread1.start();
        MyThread2 myThread2 = new MyThread2(task);
        myThread2.start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long beginTime = Math.min(CommonUtils.beginTime1, CommonUtils.beginTime2);
        long endTime = Math.max(CommonUtils.endTime1, CommonUtils.endTime2);
        System.out.println("总耗时："+ (endTime - beginTime) / 1000 + "s");
    }
}