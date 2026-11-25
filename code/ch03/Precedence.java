public class Precedence {
    public static void main(String[] args) {
        int i = 5;
        int r = i++ + ++i;        // 5 (then i = 6) + 7 (i becomes 7 first)
        System.out.println("r = " + r + ", i = " + i);

        // * / % bind tighter than +, left to right
        int x = 10 * 20 + -30 * 2 / 4 % 3;
        System.out.println("x = " + x);

        int a, b, c;
        a = b = c = 42;           // assignment is right-associative
        System.out.println(a + " " + b + " " + c);
    }
}
