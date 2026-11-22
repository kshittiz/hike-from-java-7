public class ValidateBeforeSuper7 {
    static class Account {
        Account(String id) { System.out.println("Account " + id); }
    }

    static class Savings extends Account {
        Savings(String id) {
            super(normalize(id));                 // before Java 25: work hides in a static helper
        }
        private static String normalize(String id) {
            if (id.isBlank()) throw new IllegalArgumentException("empty id");
            return id.strip().toUpperCase();
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
