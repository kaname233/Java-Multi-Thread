package ch02.test1;
/*
   脏读
 */
public class PublicVar {
    private String name = "a";
    private String pwd = "aa";

    public void setValue(String name, String pwd) {

        try {
            this.name = name;
            Thread.sleep(5000);

            this.pwd = pwd;
            System.out.println("setValue method thread name = " + Thread.currentThread().getName()
                    + "name=" + this.name + " pwd=" + this.pwd);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void getValue() {
        System.out.println("getValue method thread name=" + Thread.currentThread().getName()
            +"name=" + this.name + " pwd=" + this.pwd);
    }
}

class MyThread extends Thread {
    private PublicVar publicVar;

    public MyThread(PublicVar publicVar) {
        this.publicVar = publicVar;
    }

    @Override
    public void run() {
        super.run();
        this.publicVar.setValue("b","bb");
    }
}

class Test {
    public static void main(String[] args) {
        try {
            PublicVar publicVar = new PublicVar();
            MyThread myThread = new MyThread(publicVar);
            myThread.start();
            Thread.sleep(200);

            publicVar.getValue();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
