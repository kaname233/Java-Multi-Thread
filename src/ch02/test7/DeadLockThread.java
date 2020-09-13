package ch02.test7;
/*
  线程死锁
 */
public class DeadLockThread implements Runnable {
    public String username;
    public Object lock1 = new Object();
    public Object lock2 = new Object();

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void run() {
        if(username.equals("a")) {
            synchronized (lock1) {
                try {
                    System.out.println("username="+username);
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2) {
                    System.out.println("按lock1->lock2顺序执行了");
                }
            }
        }
        if(username.equals("b")){
            synchronized (lock2) {
                try {
                    System.out.println("username="+username);
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1) {
                    System.out.println("按lock2->lock1顺序执行了");
                }
            }
        }
    }
}

class Test7 {
    public static void main(String[] args) throws InterruptedException {
        DeadLockThread deadLockThread = new DeadLockThread();
        deadLockThread.setUsername("a");
        Thread t1 = new Thread(deadLockThread);
        t1.start();
        Thread.sleep(100);
        Thread t2 = new Thread(deadLockThread);
        deadLockThread.setUsername("b");
        t2.start();
    }
}
