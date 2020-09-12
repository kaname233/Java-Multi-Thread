package ch02.test5;

/*
类锁和对象锁
 */

public class Test {
    Service service = new Service();

    public static void main(String[] args) {
        Service service = new Service();
        ThreadA threadA = new ThreadA(service);
        threadA.setName("a");
        threadA.start();
        ThreadB threadB = new ThreadB(service);
        threadB.setName("b");
        threadB.start();
        ThreadC threadC = new ThreadC(service);
        threadC.setName("c");
        threadC.start();
    }
}

class Service {
    synchronized public static void printA() {
        try {
            System.out.println("线程"+Thread.currentThread().getName()+"在"+System.currentTimeMillis()+"进入printA");
            Thread.sleep(3000);
            System.out.println("线程"+Thread.currentThread().getName()+"在"+System.currentTimeMillis()+"离开printA");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    synchronized public static void printB() {
            System.out.println("线程"+Thread.currentThread().getName()+"在"+System.currentTimeMillis()+"进入printB");
            System.out.println("线程"+Thread.currentThread().getName()+"在"+System.currentTimeMillis()+"离开printB");
    }
    synchronized public void printC() {
            System.out.println("线程"+Thread.currentThread().getName()+"在"+System.currentTimeMillis()+"进入printC");
            System.out.println("线程"+Thread.currentThread().getName()+"在"+System.currentTimeMillis()+"离开printC");
    }
}

class ThreadA extends Thread {
    private Service service;

    public ThreadA(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        super.run();
        service.printA();
    }
}

class ThreadB extends Thread {
    private Service service;

    public ThreadB(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        super.run();
        service.printB();
    }
}
class ThreadC extends Thread {
    private Service service;

    public ThreadC(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        super.run();
        service.printC();
    }
}