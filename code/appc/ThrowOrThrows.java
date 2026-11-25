import java.io.IOException;

public class ThrowOrThrows {
    // "throws" is the warning label on the method's signature
    static String readPermit(String trail) throws IOException {
        if (trail.isBlank()) {
            // "throw" is the act of throwing, at the moment it happens
            throw new IOException("no permit for an unnamed trail");
        }
        return "permit for " + trail;
    }

    public static void main(String[] args) {
        for (String trail : new String[] {"Summit", " "}) {
            try {
                System.out.println(readPermit(trail));
            } catch (IOException e) {
                System.out.println("caught: " + e.getMessage());
            }
        }
    }
}
