public class MoneyRecord {
    record Money(String currency, long cents) { }      // Java 16: one line

    public static void main(String[] args) {
        Money a = new Money("USD", 1250);
        Money b = new Money("USD", 1250);
        System.out.println(a + "  ==: " + (a == b) + "  equals: " + a.equals(b));
        System.out.println("same hashCode: " + (a.hashCode() == b.hashCode()));
        System.out.println("accessor: " + a.cents());
    }
}
