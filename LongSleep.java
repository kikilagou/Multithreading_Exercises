
public class LongSleep {

    public static void main(String[] args) {
        Thread thread = new Thread(new MyThread());
        thread.start();

        // Trying out interrupt method.
        thread.interrupt();
    }


}

class MyThread implements Runnable {

    @Override
    public void run() {
        try {
            Thread.sleep(50000);
        } catch (InterruptedException e) {
            System.out.println("Breaking out of sleeping thread.");
        }
    }
}

