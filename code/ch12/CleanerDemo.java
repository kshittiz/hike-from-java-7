import java.lang.ref.Cleaner;

public class CleanerDemo {
    private static final Cleaner CLEANER = Cleaner.create();          // Java 9

    static class TempFile implements AutoCloseable {
        private final Cleaner.Cleanable cleanable;
        TempFile(String name) {
            // The cleanup action must not refer to 'this', or the object could never be collected.
            cleanable = CLEANER.register(this, () -> System.out.println("deleted " + name));
        }
        @Override public void close() { cleanable.clean(); }            // runs at most once
    }

    public static void main(String[] args) {
        try (TempFile f = new TempFile("report.tmp")) {                  // deterministic: close()
            System.out.println("using the file");
        }
        System.out.println("done");
    }
}
