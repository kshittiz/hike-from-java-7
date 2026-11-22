public class Checked {
    static class InsufficientFunds extends Exception {                 // checked: extends Exception
        InsufficientFunds(long shortBy) { super("short by " + shortBy + " cents"); }
    }

    static long balance = 5_000;

    static void withdraw(long cents) throws InsufficientFunds {          // must be declared
        if (cents > balance) throw new InsufficientFunds(cents - balance);
        balance -= cents;
    }

    public static void main(String[] args) {
        try {
            withdraw(2_000);
            withdraw(4_000);
        } catch (InsufficientFunds e) {
            System.out.println("declined: " + e.getMessage() + ", balance " + balance);
        }
        int[] data = new int[2];
        try {
            data[5] = 1;                                                  // unchecked: no declaration
        } catch (RuntimeException e) {
            System.out.println("unchecked: " + e.getClass().getSimpleName());
        }
    }
}
