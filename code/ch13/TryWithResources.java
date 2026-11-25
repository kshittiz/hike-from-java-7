public class TryWithResources {
    static class Resource implements AutoCloseable {
        private final String name;
        private final boolean failOnClose;
        Resource(String name, boolean failOnClose) {
            this.name = name; this.failOnClose = failOnClose;
            System.out.println("open  " + name);
        }
        @Override public void close() {
            System.out.println("close " + name);
            if (failOnClose) {
                throw new IllegalStateException("close failed: " + name);
            }
        }
    }

    public static void main(String[] args) {
        try (Resource db = new Resource("db", true);              // Java 7
             Resource file = new Resource("file", false)) {
            System.out.println("working");
            throw new RuntimeException("work failed");
        } catch (RuntimeException e) {
            System.out.println("caught:     " + e.getMessage());
            for (Throwable s : e.getSuppressed()) {                // Java 7
                System.out.println("suppressed: " + s.getMessage());
            }
        }

        Resource shared = new Resource("shared", false);
        try (shared) {  // Java 9: an effectively final variable
            System.out.println("using shared");
        }
    }
}
