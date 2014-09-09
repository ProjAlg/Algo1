import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by caynan on 9/6/14.
 */
public class ArrayGenerator {

    public int[] arrayGen(int size) {
        int[] A = new int[size];
        Random rand = new Random();

        // Generate and inserts n elements into out array (n == size)
        for(int i = 0; i < size; i++) {
            A[i] = rand.nextInt(size);
        }
        // Sort array A
        Arrays.sort(A);

        // return sorted array
        return A;
    }
}
