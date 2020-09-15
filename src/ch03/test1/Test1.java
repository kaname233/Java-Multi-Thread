package ch03.test1;

/*
wait()方法和notfy()方法的使用
 */
 class MyThread1 extends Thread {
    private Object lock;

    public MyThread1(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        super.run();
        synchronized (lock) {
            try {
                System.out.println("开始wait time=" + System.currentTimeMillis());
                lock.wait();
                System.out.println("结束wait time=" + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class MyThread2 extends Thread {
    private Object lock;

    public MyThread2(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        super.run();
        synchronized (lock) {
            System.out.println("开始notify time=" + System.currentTimeMillis());
            lock.notify();
            System.out.println("结束notify time=" + System.currentTimeMillis());
        }
    }
}

public class Test1 {
    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        MyThread1 myThread1 = new MyThread1(lock);
        myThread1.start();
        Thread.sleep(3000);
        MyThread2 myThread2 = new MyThread2(lock);
        myThread2.start();
    }
}

