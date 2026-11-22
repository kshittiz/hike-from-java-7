public class Labeled {
    public static void main(String[] args) {
        int[][] grid = { {1, 2, 3}, {4, -5, 6}, {7, 8, 9} };
        search:
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                if (grid[r][c] < 0) {
                    System.out.println("first negative at [" + r + "][" + c + "]");
                    break search;      // leaves both loops
                }
            }
        }
        for (int n : grid[2]) {        // enhanced for: Java 5
            System.out.print(n + " ");
        }
        System.out.println();
    }
}
