// package pc;

/**
 * Generic producer that reads items from a source Iterable
 * and places them into the shared BoundedBlockingQueue.
 */
public class Producer<T> implements Runnable {

    private final Iterable<T> source;
    private final BoundedBlockingQueue<T> queue;
    private final T poisonPill;

    public Producer(Iterable<T> source,
                    BoundedBlockingQueue<T> queue,
                    T poisonPill) {
        this.source = source;
        this.queue = queue;
        this.poisonPill = poisonPill;
    }

    @Override
    public void run() {
        try {
            for (T value : source) {
                queue.put(value);
            }
            queue.put(poisonPill); // signal completion
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}