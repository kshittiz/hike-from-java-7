public class Literals {
    public static void main(String[] args) {
        int binary  = 0b1010;          // Java 7
        int hex     = 0x1F;
        int octal   = 017;             // a leading zero means octal!
        long million = 1_000_000L;     // Java 7 underscores, L for long
        double rate = 6.022_140_76e23;
        System.out.println(binary + " " + hex + " " + octal + " " + million + " " + rate);
    }
}
