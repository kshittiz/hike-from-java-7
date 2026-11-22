public class SwitchExpression {
    static int daysIn(String month, boolean leap) {
        return switch (month) {                       // Java 14: switch is an expression
            case "APR", "JUN", "SEP", "NOV" -> 30;    // several labels, no fall-through
            case "FEB" -> {
                int d = leap ? 29 : 28;
                yield d;                              // yield returns a value from a block
            }
            default -> 31;
        };
    }

    public static void main(String[] args) {
        System.out.println("JUN " + daysIn("JUN", false));
        System.out.println("FEB " + daysIn("FEB", false));
        System.out.println("FEB " + daysIn("FEB", true) + " (leap)");
        System.out.println("JAN " + daysIn("JAN", false));
    }
}
