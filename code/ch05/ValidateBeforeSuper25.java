public class ValidateBeforeSuper25 {
    static class Account {
        Account(String id) { System.out.println("Account " + id); }
    }

    static class Savings extends Account {
        Savings(String id) {
            if (id.isBlank()) throw new IllegalArgumentException("empty id");   // Java 25:
            String clean = id.strip().toUpperCase();                            // statements
            super(clean);                                                       // before super()
        }
    }

    public static void main(String[] args) {
        new Savings("  sb-01 ");
        try {
            new Savings("   ");
        } catch (IllegalArgumentException e) {
            System.out.println("rejected: " + e.getMessage());
        }
    }
}
