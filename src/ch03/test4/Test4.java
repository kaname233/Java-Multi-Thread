package ch03.test4;


/*
多消费者多生产者---假死情况
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
                    System.out.println(Thread.currentThread().getName() + " is WAITING ⭐");
                    lock.wait();
                }
                System.out.println(Thread.currentThread().getName() + " is RUNNABLE");
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
                    System.out.println(Thread.currentThread().getName() + " is WAITING ⭐⭐");
                    lock.wait();
                }
                System.out.println(Thread.currentThread().getName()+"is RUNNABLE");
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

public class Test4 {
    public static void main(String[] args) throws InterruptedException {
        String lock = new String("");
        Producer producer = new Producer(lock);
        Consumer consumer = new Consumer(lock);
        ThreadP[] pThread = new ThreadP[2];
        ThreadC[] cThread = new ThreadC[2];
        for (int i = 0; i < 2; i++) {
            pThread[i] = new ThreadP(producer);
            pThread[i].setName("producer" + (i+1));
            cThread[i] = new ThreadC(consumer);
            cThread[i].setName("consumer" + (i+1));
            pThread[i].start();
            cThread[i].start();
        }
        Thread.sleep(5000);
        Thread[] threadArray = new Thread[Thread.currentThread().getThreadGroup().activeCount()];
        Thread.currentThread().getThreadGroup().enumerate(threadArray);
        for (int i = 0; i < threadArray.length; i++) {
            System.out.println(threadArray[i].getName()+" "+threadArray[i].getState());

        }
    }
}
