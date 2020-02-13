package basics;

public class LongSum {
    public static void main(String[] args) throws InterruptedException {
        SumExample.runTest();
    }
}


class SumExample {
    private long startRange;
    private long endRange;
    private long counter = 0;

    public SumExample(long startRange, long endRange) {
        this.startRange = startRange;
        this.endRange = endRange;
    }

    public void add() {
        synchronized(this){
            for(long i = startRange; i<= endRange; i++) {
                counter += i;
            }
        }

    }

    public static void runWithOneThread() {
        long startTime = System.currentTimeMillis();

        SumExample sumExample = new SumExample(1, Integer.MAX_VALUE);
        sumExample.add();

        long endTime = System.currentTimeMillis();
        System.out.println("Single Thread execution sum : " + sumExample.counter + " Time needed : " +  (endTime-startTime) + " milliseconds");
    }

    public static void runWithMultipleThreads() throws InterruptedException {
        SumExample sumExample1 = new SumExample(1, Integer.MAX_VALUE / 2);
        SumExample sumExample2 = new SumExample((Integer.MAX_VALUE / 2) + 1, Integer.MAX_VALUE);

        long startTime = System.currentTimeMillis();

        Thread t1 = new Thread(() -> {
            sumExample1.add();
        });

        Thread t2 = new Thread(() -> {
            sumExample2.add();
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        long finalCount = sumExample1.counter + sumExample2.counter;
        long endTime = System.currentTimeMillis();

        System.out.println("Multiple Thread execution sum : " + finalCount + " Time needed : " +  (endTime-startTime) + " milliseconds");
    }

    public static void runTest() throws InterruptedException {
        runWithOneThread();
        runWithMultipleThreads();
    }
}



