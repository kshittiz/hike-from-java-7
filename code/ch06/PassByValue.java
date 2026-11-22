public class PassByValue {
    static class Box { int value; }

    static void swap(int a, int b) { int t = a; a = b; b = t; }            // swaps copies
    static void replace(Box box)   { box = new Box(); box.value = 99; }    // repoints the copy
    static void mutate(Box box)    { box.value = 42; }                     // changes the shared object

    public static void main(String[] args) {
        int x = 1, y = 2;
        swap(x, y);
        System.out.println("after swap:    x=" + x + " y=" + y);

        Box b = new Box();
        replace(b);
        System.out.println("after replace: " + b.value);
        mutate(b);
        System.out.println("after mutate:  " + b.value);
    }
}
