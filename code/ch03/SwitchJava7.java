public class SwitchJava7 {
    static int daysIn(String month) {
        int days = 0;
        switch (month) {               // strings in switch: Java 7
            case "APR":
            case "JUN":
            case "SEP":
            case "NOV":
                days = 30;
                break;
            case "FEB":
                days = 28;
                // forgot break: falls through!
            default:
                days = 31;
        }
        return days;
    }

    public static void main(String[] args) {
        System.out.println("JUN " + daysIn("JUN"));
        System.out.println("FEB " + daysIn("FEB") + "   <- fall-through bug");
        System.out.println("JAN " + daysIn("JAN"));
    }
}
