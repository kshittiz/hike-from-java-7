public class CharMath {
    public static void main(String[] args) {
        char c = 'A';
        System.out.println(c + 1);            // int arithmetic: 66
        System.out.println((char) (c + 1));   // B
        c++;                                  // ++ keeps the char type
        System.out.println(c);                // B

        String coffee = "☕";             // hot beverage, one UTF-16 unit
        String grin   = "😀";       // grinning face, a surrogate pair
        System.out.println("coffee length = " + coffee.length());
        System.out.println("grin length   = " + grin.length());
        System.out.println("grin code points = " + grin.codePointCount(0, grin.length()));
    }
}
