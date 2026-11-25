import java.io.Closeable;

public class CloseableOrAutoCloseable {
    // AutoCloseable (Java 7): anything try-with-resources can close
    static class Lantern implements AutoCloseable {
        @Override
        public void close() {              // may throw any Exception
            System.out.println("lantern off");
        }
    }

    // Closeable (Java 5): the I/O version; close() throws IOException
    // and calling it twice must be harmless
    static class Logbook implements Closeable {
        private boolean closed;

        @Override
        public void close() {
            if (closed) return;            // second call does nothing
            closed = true;
            System.out.println("logbook closed");
        }
    }

    public static void main(String[] args) {
        try (Lantern lantern = new Lantern();
             Logbook log = new Logbook()) {
            System.out.println("walking in the dark");
            log.close();                   // closing early is allowed
        }                                  // closed in reverse order
        System.out.println("Closeable is an AutoCloseable: "
                + AutoCloseable.class.isAssignableFrom(Closeable.class));
    }
}
