import static java.lang.IO.println;

public class Demonstration {
    static void main() throws InterruptedException {
        SumUpExample.runTest();
    }
}

class SumUpExample {
    long startRange;
    long endRange;
    long counter = 0;

    static long MAX_NUM = Integer.MAX_VALUE;

    public SumUpExample(long startRange, long endRange) {
        this.startRange = startRange;
        this.endRange = endRange;
    }

    public void add() {
        for (long i = startRange; i <= endRange; i++) {
            counter += i;
        }
    }

    public static void twoThreads() throws InterruptedException {
        long start = System.currentTimeMillis();
        var s1 = new SumUpExample(start, MAX_NUM / 2);
        var s2 = new SumUpExample(1 + MAX_NUM / 2, MAX_NUM);

        Thread t1 = new Thread(s1::add);
        Thread t2 = new Thread(s2::add);

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        long finalCount = s1.counter + s2.counter;
        long end = System.currentTimeMillis();

        println("Two threads final count = " + finalCount + " took " + (end - start));
    }

    public static void oneThread() {
        long start = System.currentTimeMillis();
        var s1 = new SumUpExample(0, MAX_NUM);
        s1.add();
        long finalCount = s1.counter;
        long end = System.currentTimeMillis();
        println("One thread final count = " + finalCount + " took " + (end - start));
    }

    public static void runTest() throws InterruptedException {
        oneThread();
        twoThreads();
    }
}
