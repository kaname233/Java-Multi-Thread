package ch02.test2;
/*
    可重入锁
 */
public class Service {
    public synchronized void service1() {
        service2();
        System.out.println("service1");
    }
    public synchronized void service2() {
        service3();
        System.out.println("service2");
    }
    public synchronized void service3() {
        System.out.println("service3");
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        super.run();
        new Service().service1();
    }
}
 class Test {
     public static void main(String[] args) {
         MyThread myThread = new MyThread();
         myThread.start();
     }
 }