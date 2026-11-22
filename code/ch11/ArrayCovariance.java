public class ArrayCovariance {
    public static void main(String[] args) {
        Object[] things = new String[2];       // compiles: String[] is an Object[]
        things[0] = "fine";
        try {
            things[1] = 42;                    // compiles too, but fails at run time
        } catch (ArrayStoreException e) {
            System.out.println("ArrayStoreException: " + e.getMessage());
        }
        // List<Object> list = new ArrayList<String>();   // generics reject this at compile time
    }
}
