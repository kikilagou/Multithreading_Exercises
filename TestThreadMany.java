
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestThreadMany extends Thread {

    int threadCount;
    Scanner input;

    protected TestThreadMany() {
        System.out.println("How many threads should I initialise?");
        this.input  = new Scanner(System.in);
        this.threadCount = input.nextInt();
        initThreads();
    }

    protected void initThreads() {

        List<Thread> threads = new ArrayList<Thread>();

        for(int i = 0; i < threadCount; i++) {
            Thread thread = new Thread();
            thread.setName("Thread " + i);
            threads.add(thread);
        }

        for(Thread thread : threads) {
            thread.start();
            System.out.println("Started thread " + thread.getName());

        }
    }

    public static void main(String[] args) {
        TestThreadMany ttm = new TestThreadMany();
    }

}

//class MyThread extends Thread {
//
//    public MyThread (String s) {
//        super(s);
//    }
//
//    public void run() {
//        System.out.println("Hello, I am "+ getName());
//    }
//}
//
//
//class TestThread {
//    public static void main (String[] args) {
//        MyThread t1, t2;
//
//        t1 = new MyThread ("Thread #1");
//        t2 = new MyThread ("Thread #2");
//
//        t2.start();
//        t1.start();
//    }
//}
