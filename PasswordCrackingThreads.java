
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PasswordCrackingThreadst {

    public static int MAX_PASSWORD = 9999;

    public static void main(String[] args) {
        Random random = new Random();

        Vault vault = new Vault(random.nextInt(MAX_PASSWORD));
//        AscendingHackerThread asc = new AscendingHackerThread(vault);
//        DescendingHackerThread desc = new DescendingHackerThread(vault);
//        PoliceThread police = new PoliceThread();

        List<Thread> threads = new ArrayList<Thread>();
        threads.add(new AscendingHackerThread(vault));
        threads.add(new DescendingHackerThread(vault));
        threads.add(new PoliceThread());

        for(Thread thread : threads) {
            thread.start();
        }
    }

    /*
    We have a vault we are going to try to guess the password of
     */
    private static class Vault {

        int password;
        protected Vault(int password) {
            this.password = password;
        }

        protected boolean guessedPasswordCorrectly(int password) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return this.password == password;
        }
    }

    private static abstract class HackerThread extends Thread {

        protected Vault vault;
        public HackerThread(Vault vault) {
            this.vault = vault;
            this.setName(this.getClass().getSimpleName());
            this.setPriority(Thread.MAX_PRIORITY);
        }

        @Override
        public void start() {
            System.out.println("Starting thread " + this.getName());
            super.start();
        }
    }

    private static class AscendingHackerThread extends HackerThread {

        protected AscendingHackerThread(Vault vault) {
            super(vault);
        }

        @Override
        public void run() {
            for(int i = 0; i <= MAX_PASSWORD; i++) {
                if(vault.guessedPasswordCorrectly(i)) {
                    System.out.println(this.getName() + " guessed the password!  " + i);
                    System.exit(0);
                }
            }
        }
    }

    private static class DescendingHackerThread extends HackerThread {

        protected DescendingHackerThread(Vault vault) {
            super(vault);
        }

        @Override
        public void run() {
            for(int i = MAX_PASSWORD; i >= 0; i--) {
                if(vault.guessedPasswordCorrectly(i)) {
                    System.out.println(this.getName() + " guessed the password " + i);
                    System.exit(0);
                }
            }
        }
    }

    private static class PoliceThread extends Thread {

        @Override
        public void run() {
            for(int i = 10; i > 0; i--) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i);
            }
            System.out.println("You're caught...");
            System.exit(0);
        }
    }

}

