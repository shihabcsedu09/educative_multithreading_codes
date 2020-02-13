package basics;

import java.util.Random;

public class BadCounter {

    static Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) throws InterruptedException {
        ThreadUnsafeCounter threadUnsafeCounter = new ThreadUnsafeCounter();

        Thread t1 = new Thread(()-> {
            for(int i=0;i<100;i++) {
                threadUnsafeCounter.increment();
            }
        });

        Thread t2 = new Thread(()-> {
            for(int i=0;i<100;i++) {
                threadUnsafeCounter.decrement();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

    }

    static void sleepThreadRandmlyForLessThan10Secs() throws InterruptedException {
        Thread.sleep(random.nextInt());
    }



}


class ThreadUnsafeCounter {
    long counter = 0;

    void increment() {
        System.out.println("Incrementing counter from " + this.counter + " to " + (this.counter + 1));
        counter++;
    }

    void decrement() {
        System.out.println("Decrementing counter from " + this.counter + " to " + (this.counter -1));
        counter--;
    }

}
