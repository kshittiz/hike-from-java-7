import java.util.Arrays;

public class ArrayBasics {
    public static void main(String[] args) {
        int[] scores = new int[3];                         // fixed size, filled with defaults
        String[] names = new String[2];
        System.out.println(Arrays.toString(scores) + " " + Arrays.toString(names));

        int[] primes = {2, 3, 5, 7};                       // array initializer
        System.out.println("length = " + primes.length);   // a field, not a method

        int[][] triangle = new int[3][];                   // an array of arrays; rows can differ
        for (int r = 0; r < triangle.length; r++) {
            triangle[r] = new int[r + 1];
            Arrays.fill(triangle[r], r + 1);
        }
        System.out.println(Arrays.deepToString(triangle));

        System.out.println(sum(new int[] {10, 20, 30}));   // an anonymous array

        try {
            System.out.println(primes[4]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    static int sum(int[] values) {
        int total = 0;
        for (int v : values) total += v;
        return total;
    }
}
