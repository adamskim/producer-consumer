package pl.adamskim.pc;

import java.util.Deque;
import java.util.Random;

public class TaskProducer implements Runnable {
    private Deque<Task> queue;
    private final int capacity;
    private Random random = new Random();

    public TaskProducer(Deque<Task> queue, int capacity) {
        this.queue = queue;
        this.capacity = capacity;
    }


    @Override
    public void run() {
        boolean interrupted = false;
        while (!interrupted) {
            synchronized (queue) {
                if (queue.size() < capacity) {
                    Task task = produceTask();
                    queue.offer(task);
                } else {
                    try {
                        queue.wait();
                        queue.notifyAll();
                    } catch (InterruptedException e) {
                        interrupted = true;
                    }
                }
            }
        }
    }

    private Task produceTask() {
        int bound = 1000;
        return new Task(random.nextInt(bound), random.nextInt(bound));
    }
}
