package pl.adamskim.pc;

import java.util.Deque;

public class TaskConsumer implements Runnable {
    private Deque<Task> queue;
    private final int capacity;

    public TaskConsumer(Deque<Task> queue, int capacity) {
        this.queue = queue;
        this.capacity = capacity;
    }

    @Override
    public void run() {
        boolean interrupted = false;
        while (!interrupted) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                interrupted = true;
            }
            synchronized (queue) {
                if (queue.size() < capacity / 2) {
                    queue.notifyAll();
                }
                if (!queue.isEmpty()) {
                    queue.poll().execute();
                } else {
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        interrupted = true;
                    }
                }
            }
        }
    }
}
