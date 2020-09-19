package ch03.test3;
/*
    基于wait和notify的生产者消费者模式实现
    一生产一消费：操作值
 */

class VauleObject {
    public static String value = "";
}

/*
生产者
 */
class Producer {
    private String lock;

    public Producer(String lock) {
        this.lock = lock;
    }

    public void setVaule() {
        try {
            synchronized(lock) {
                if(!VauleObject.value.equals("")) {
                    lock.wait();
                }
                String value = System.currentTimeMillis() + "_"
                        + System.nanoTime();
                System.out.println("设置的值为" + value);
                VauleObject.value = value;
                lock.notify();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

//消费者
class Consumer {
    private String lock;

    public Consumer(String lock) {
        this.lock = lock;
    }

    public void getValue() {
        try {
            synchronized (lock) {
                if(VauleObject.value.equals("")) {
                    lock.wait();
                }
                System.out.println("获取的值为" + VauleObject.value);
                VauleObject.value = "";
                lock.notify();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ThreadP extends Thread {
    private Producer p;

    public ThreadP(Producer p) {
        this.p = p;
    }

    @Override
    public void run() {
        super.run();
        while(true) {
            p.setVaule();
        }
    }
}
class ThreadC extends Thread {
    private Consumer c;

    public ThreadC(Consumer c) {
        this.c = c;
    }

    @Override
    public void run() {
        super.run();
        while (true) {
            c.getValue();
        }

    }
}
public class Test3 {
    public static void main(String[] args) {
        String lock = new String("");
        Producer producer = new Producer(lock);
        new ThreadP(producer).start();
        Consumer consumer = new Consumer(lock);
        new ThreadC(consumer).start();
    }
}
