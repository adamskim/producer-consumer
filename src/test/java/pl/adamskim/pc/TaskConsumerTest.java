package pl.adamskim.pc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;
import org.mockito.verification.VerificationMode;

import java.util.ArrayDeque;
import java.util.Deque;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class TaskConsumerTest {

    private Deque<Task> queue = new ArrayDeque<>();
    private TaskConsumer taskConsumer = new TaskConsumer(queue, ProducerConsumerApplication.CAPACITY);
    
    @Test
    public void shouldConsumeTasks() throws InterruptedException {
        // given
        Task task1 = Mockito.mock(Task.class);
        Task task2 = Mockito.mock(Task.class);
        queue.offer(task1);
        queue.offer(task2);
        
        // when
        Thread thread = new Thread(taskConsumer);
        thread.start();
        while (!queue.isEmpty()) {
            Thread.sleep(200);
        }
        thread.interrupt();
        
        // then
        Mockito.verify(task1, Mockito.only()).execute();
        Mockito.verify(task2, Mockito.only()).execute();
    }
}