package ch01.test3;

public class MyThread extends Thread {
    @Override
    public void run() {
        long beginTime = System.currentTimeMillis();
        int count = 0;
        for(int i = 0; i < 5000000; i++) {
            Thread.yield();
            count = count + (i + 1);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("用时："+ (endTime - beginTime) + "毫秒！");
    }
}

class Test {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
    }
}