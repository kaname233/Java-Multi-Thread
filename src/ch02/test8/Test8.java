package ch02.test8;

class PrintString implements Runnable {
    private volatile boolean isContinuePrint = true;
    public boolean isContinuePrint() {
        return isContinuePrint;
    }

    public void setContinuePrint(boolean continuePrint) {
        isContinuePrint = continuePrint;
    }
    public void printStringMethod() {
        while(true == isContinuePrint) {
            try {
                System.out.println("run printStringMethod thread is " + Thread.currentThread().getName());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void run() {
        printStringMethod();
    }
}

public class Test8 {
    public static void main(String[] args) {
        PrintString printString = new PrintString();
        new Thread(printString).start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("我要停止它！ stopThread=" + Thread.currentThread().getName());
        printString.setContinuePrint(false);
    }
}