package ch02.test4;


import java.util.ArrayList;
import java.util.List;

class MyList {
    private List list = new ArrayList();
    public synchronized void add(String data) {
        list.add(data);
    }
    public synchronized int getSize() {
        return list.size();
    }
}

class MyService {
    public MyList addServiceMethod(MyList list, String data) {
        try {
            synchronized (list) {
                if (list.getSize() < 1) {
                    Thread.sleep(2000);
                    list.add(data);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return list;
    }
}

class MyThread1 extends Thread {
    private MyList list;

    public MyThread1(MyList list) {
        this.list = list;
    }

    @Override
    public void run() {
        super.run();
        MyService myService = new MyService();
        myService.addServiceMethod(list,"A");
    }
}

class MyThread2 extends Thread {
    private MyList list;

    public MyThread2(MyList list) {
        this.list = list;
    }

    @Override
    public void run() {
        super.run();
        MyService myService = new MyService();
        myService.addServiceMethod(list,"B");
    }
}

public class Test {
    public static void main(String[] args) throws InterruptedException {
        MyList list = new MyList();
        MyThread1 myThread1 = new MyThread1(list);
        myThread1.setName("A");
        myThread1.start();
        MyThread2 myThread2 = new MyThread2(list);
        myThread2.setName("B");
        myThread2.start();
        Thread.sleep(6000);
        System.out.println("listSize="+list.getSize());
    }
}