public class Payments {
    sealed interface Payment permits Card, Upi, Cash { }
    record Card(String last4, long cents) implements Payment { }
    record Upi(String vpa, long cents) implements Payment { }
    record Cash(long cents) implements Payment { }

    static String receipt(Payment p) {
        return switch (p) {                                          // exhaustive: no default
            case Card(String last4, long cents) when cents > 100_000 -> "card *" + last4 + " (needs approval)";
            case Card(String last4, long cents)                      -> "card *" + last4 + " " + cents;
            case Upi(String vpa, long cents)                         -> "upi " + vpa + " " + cents;
            case Cash(long cents)                                    -> "cash " + cents;
        };
    }

    public static void main(String[] args) {
        Payment[] payments = { new Card("4242", 2_500), new Card("1881", 250_000),
                               new Upi("asha@bank", 900), new Cash(100) };
        for (Payment p : payments) System.out.println(receipt(p));
    }
}
