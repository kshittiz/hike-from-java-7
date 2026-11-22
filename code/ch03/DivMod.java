public class DivMod {
    public static void main(String[] args) {
        System.out.println(" 7 / 2  = " + (7 / 2));
        System.out.println("-7 / 2  = " + (-7 / 2));       // truncates toward zero
        System.out.println(" 7 % 3  = " + (7 % 3));
        System.out.println("-7 % 3  = " + (-7 % 3));       // sign follows the dividend
        System.out.println(" 7 % -3 = " + (7 % -3));
        System.out.println("floorMod(-7, 3) = " + Math.floorMod(-7, 3));   // Java 8
        System.out.println("isOdd(-3) with % 2 == 1 ? " + (-3 % 2 == 1));
    }
}
