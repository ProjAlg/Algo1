import java.util.Arrays;

/**
 * Created by caynan on 9/6/14.
 */
public class Timer {
    public static void main (String[] args) {
        ArrayGenerator gen = new ArrayGenerator();

        final int LIMIT = (int) Math.pow(2, 30);
        final int LOOP = 1000;
        // Class that generates the array with random values to be searched
        int[] val = gen.arrayGen(LOOP);

        // Timing Sequential Search
        long[] sequentialTimes = sequentialSearchExperiment(val, LOOP);
        // Timing Jump Search
        long[] jumpTimes = jumpSearchExperiment(val, LOOP);
        // Timing Recursive Search
        long[] recursiveTimes = recursiveSearchExperiment(val, LOOP);

        // CREATE CSV AND PUT VALUES THERE

        System.out.println("Sequential times: " + Arrays.toString(sequentialTimes));
        System.out.println("Jump times: " + Arrays.toString(jumpTimes));
        System.out.println("Recursive times: " + Arrays.toString(recursiveTimes));
    }

    private static long[] sequentialSearchExperiment (int val[], int LOOP) {
        SequentialSearch sequentialSearch = new SequentialSearch();
        ArrayGenerator gen = new ArrayGenerator();
        long startTime = 0, endTime = 0;
        long[] times = new long[6];
        int[] A;

        for (int j = 1; j <= 5; j++) {
            // Create Array of size 2^5j
            A = gen.arrayGen((int) Math.pow(2, 5 * j));

            startTime = System.nanoTime();
            for (int i = 0; i < LOOP; i++) {
                sequentialSearch.search(A, val[i]);
            }
            endTime = System.nanoTime();
            // save total time of the experiment into times array
            times[j] = endTime - startTime;
        }
        return times;
    }

    private static long[] jumpSearchExperiment(int val[], int LOOP) {
        JumpSearch jumpSearch = new JumpSearch();
        ArrayGenerator gen = new ArrayGenerator();
        long startTime = 0, endTime = 0;
        long[] times = new long[6];
        int[] A;

        for (int j = 1; j <= 5; j++) {
            // Create Array of size 2^5j
            A = gen.arrayGen((int) Math.pow(2, 5 * j));

            startTime = System.nanoTime();
            for (int i = 0; i < LOOP; i++) {
                jumpSearch.search(A, val[i], 2);
            }
            endTime = System.nanoTime();
            // save total time of the experiment into times array
            times[j] = endTime - startTime;

        }
        return times;
    }

    private static long[] recursiveSearchExperiment(int val[], int LOOP) {
        RecursiveSearch recursiveSearch = new RecursiveSearch();
        ArrayGenerator gen = new ArrayGenerator();
        long startTime = 0, endTime = 0;
        long[] times = new long[6];
        int[] A;

        for (int j = 1; j <= 5; j++) {
            // Create Array of size 2^5j
            A = gen.arrayGen((int) Math.pow(2, 5 * j));

            //Note to Cay, I've configured this execution with 4 partitions.
            startTime = System.nanoTime();
            for (int i = 0; i < LOOP; i++) {
                recursiveSearch.searchWithMultiplePartitions(A, val[i], 4);
            }
            endTime = System.nanoTime();
            // save total time of the experiment into times array
            times[j] = endTime - startTime;

        }
        return times;
    }

}

