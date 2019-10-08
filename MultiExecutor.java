// Creating threads to run a list of tasks concurrently 

public class MultiExecutor {

    private final List<Runnable> tasks;

    protected MultiExecutor(List<Runnable> tasks) {
        this.tasks = tasks;
    }

    public void executeAll() {
        // For each task we create a new thread and execute it

        ArrayList<Thread> threads = new ArrayList<Thread>(tasks.size());

        for(Runnable task : tasks) {
            Thread thread = new Thread(task);
            threads.add(thread);
        }

        for(Thread thread : threads) {
            thread.start();
        }
    }
}
