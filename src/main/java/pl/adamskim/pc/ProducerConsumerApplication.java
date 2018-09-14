package pl.adamskim.pc;

import java.util.ArrayDeque;
import java.util.Deque;

public class ProducerConsumerApplication {
	public final static int CAPACITY = 100;
	public final static int PROD_COUNT = 2;
	public final static int CONS_COUNT = 4;

	public static void main(String[] args) {
		Deque<Task> queue = new ArrayDeque<>(CAPACITY);
		TaskProducer taskProducer = new TaskProducer(queue, CAPACITY);
		TaskConsumer taskConsumer = new TaskConsumer(queue, CAPACITY);
		runThreads(taskProducer, PROD_COUNT);
		runThreads(taskConsumer, CONS_COUNT);
	}

	public static void runThreads(Runnable runnable, int count) {
		for (int i = 0; i < count; i++) {
			new Thread(runnable).start();
		}
	}
}
