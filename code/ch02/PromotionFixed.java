public class PromotionFixed {
    public static void main(String[] args) {
        byte a = 10;
        byte b = 20;
        int sum = a + b;            // fix 1: accept the int
        byte cast = (byte) (a + b); // fix 2: narrow it yourself
        a += b;                     // fix 3: compound assignment casts for you
        System.out.println(sum + " " + cast + " " + a);
    }
}
