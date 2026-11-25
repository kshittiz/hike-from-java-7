public class InnerWithMain {
    class Inner {                // an inner (non-static) class
        static int created = 0;  // static members allowed since Java 16
        public static void main(String[] args) {
            System.out.println("main inside an inner class");
        }
    }
}
