package ch01.test2;

public class MyThread extends Thread {
    @Override
    public void run() {
        super.run();
        System.out.println(this.currentThread().getName()+" begin");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.currentThread().getName()+" end");
    }
}

class test2 {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        System.out.println("begin = " + System.currentTimeMillis());
//        体会直接调用run方法和调用start方法的区别
        myThread.run();
//        myThread.start();
        System.out.println("end = " + System.currentTimeMillis());
    }
}
