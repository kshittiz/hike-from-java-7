import java.util.Objects;

public class Money7 {                  // the Java 7 way: write it all by hand
    private final String currency;
    private final long cents;

    public Money7(String currency, long cents) {
        this.currency = Objects.requireNonNull(currency);   // Objects: Java 7
        this.cents = cents;
    }
    public String currency() { return currency; }
    public long cents() { return cents; }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Money7)) return false;
        Money7 m = (Money7) o;
        return cents == m.cents && currency.equals(m.currency);
    }
    @Override public int hashCode() { return Objects.hash(currency, cents); }
    @Override public String toString() {
        return "Money7[currency=" + currency + ", cents=" + cents + "]";
    }

    public static void main(String[] args) {
        Money7 a = new Money7("USD", 1250);
        Money7 b = new Money7("USD", 1250);
        System.out.println(a + "  ==: " + (a == b)
                + "  equals: " + a.equals(b));
    }
}
