public class FinalizeDemo {
    @Override
    protected void finalize() {  // deprecated (Java 9), for removal (Java 18)
        System.out.println("finalizing");
    }
}
