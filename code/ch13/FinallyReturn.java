public class FinallyReturn {
    @SuppressWarnings("finally")
    static int show() {
        try {
            int x = 10;
            return 20;
        } finally {
            return 60;              // overrides the try block's return
        }
    }

    static int counter() {
        int n = 1;
        try {
            return n;               // the value 1 is saved here...
        } finally {
            n = 99;                 // ...so changing n now has no effect
        }
    }

    public static void main(String[] args) {
        System.out.println("show()    = " + show());
        System.out.println("counter() = " + counter());
    }
}
