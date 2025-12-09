// package pc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Demo / entry point for the Producer-Consumer implementation.
 */
public class ProducerConsumerDemo {

    public static void main(String[] args) throws InterruptedException {
        // List<Integer> source = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> source = new ArrayList<Integer>();
        for (int i = 1; i <= 100; i++) {
            source.add(i);
        }
        List<Integer> destination =
                Collections.synchronizedList(new ArrayList<>());

        int capacity = 2;
        Integer poisonPill = Integer.MIN_VALUE;

        BoundedBlockingQueue<Integer> queue =
                new BoundedBlockingQueue<>(capacity);

        Producer<Integer> producer =
                new Producer<>(source, queue, poisonPill);
        Consumer<Integer> consumer =
                new Consumer<>(queue, destination, poisonPill);

        Thread producerThread = new Thread(producer, "producer-1");
        Thread consumerThread = new Thread(consumer, "consumer-1");

        producerThread.start();
        consumerThread.start();

        producerThread.join();
        consumerThread.join();

        System.out.println("Source data     : " + source);
        System.out.println("Destination data: " + destination);
    }
}