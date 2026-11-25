import java.lang.ref.Cleaner;

public class CleanerDemo {
    private static final Cleaner CLEANER = Cleaner.create();  // Java 9

    static class TempFile implements AutoCloseable {
        private final Cleaner.Cleanable cleanable;
        TempFile(String name) {
            // The cleanup action must not refer to 'this',
            // or the object could never be collected.
            cleanable = CLEANER.register(this,
                    () -> System.out.println("deleted " + name));
        }
        // runs at most once
        @Override public void close() { cleanable.clean(); }
    }

    public static void main(String[] args) {
        // deterministic: close()
        try (TempFile f = new TempFile("report.tmp")) {
            System.out.println("using the file");
        }
        System.out.println("done");
    }
}
