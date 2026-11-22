public class FinallyExit {
    public static void main(String[] args) {
        try {
            System.out.println("in try");
            System.exit(0);                     // the JVM stops here
        } finally {
            System.out.println("in finally");   // never printed
        }
    }
}
