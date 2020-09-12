package ch01.test1;

public class MyThread extends Thread {
    public MyThread() {
        System.out.println("MyThread begin");
        System.out.println("Thread.currentThread().getName()= "+Thread.currentThread().getName());
        System.out.println("this.currentThread().getName()= "+this.currentThread().getName());
        System.out.println("this.getName()="+ this.getName());
        System.out.println("MyThread end");
    }

    @Override
    public void run() {
        super.run();
        System.out.println("run begin");
        System.out.println("Thread.currentThread().getName()= "+Thread.currentThread().getName());
        System.out.println("this.currentThread().getName()= "+this.currentThread().getName());
        System.out.println("this.getName()="+ this.getName());
        System.out.println("run end");
    }
}

class Test {
    public static void main(String[] args) {
        MyThread myThread1 = new MyThread();
        Thread t1 = new Thread(myThread1);
        t1.setName("A");
        t1.start();
    }
}