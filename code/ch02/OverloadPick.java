public class OverloadPick {
    static void show(byte b)  { System.out.println("show(byte)"); }
    static void show(short s) { System.out.println("show(short)"); }
    static void show(long l)  { System.out.println("show(long)"); }
    static void show(double d){ System.out.println("show(double)"); }

    public static void main(String[] args) {
        show(10);           // 10 is an int; there is no show(int)
        show('A');          // char widens to int, then to long
        show(10.5f);        // float widens to double
        byte b = 10;
        show(b);            // exact match wins
    }
}
