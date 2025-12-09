// package pc;

import java.util.Collection;

/**
 * Generic consumer that reads items from the BoundedBlockingQueue
 * and stores them into a destination collection.
 */
public class Consumer<T> implements Runnable {

    private final BoundedBlockingQueue<T> queue;
    private final Collection<T> destination;
    private final T poisonPill;

    public Consumer(BoundedBlockingQueue<T> queue,
                    Collection<T> destination,
                    T poisonPill) {
        this.queue = queue;
        this.destination = destination;
        this.poisonPill = poisonPill;
    }

    @Override
    public void run() {
        try {
            while (true) {
                T value = queue.take();
                if (value.equals(poisonPill)) {
                    break;
                }
                destination.add(value);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}