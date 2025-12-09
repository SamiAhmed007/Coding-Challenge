// package pc;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * A simple, generic bounded blocking queue implementation.
 * Uses intrinsic locking (synchronized) and wait/notifyAll
 * to coordinate producers and consumers.
 */
public class BoundedBlockingQueue<T> {

    private final Queue<T> queue;
    private final int capacity;

    public BoundedBlockingQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be > 0");
        }
        this.capacity = capacity;
        this.queue = new ArrayDeque<>(capacity);
    }

    public synchronized void put(T item) throws InterruptedException {
        while (queue.size() == capacity) {
            wait();
        }
        queue.add(item);
        notifyAll();
    }

    public synchronized T take() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        T item = queue.remove();
        notifyAll();
        return item;
    }

    public synchronized int size() {
        return queue.size();
    }
}