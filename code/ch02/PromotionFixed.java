public class PromotionFixed {
    public static void main(String[] args) {
        byte a = 10;
        byte b = 20;
        // fix 1: accept the int
        int sum = a + b;
        // fix 2: narrow it yourself
        byte cast = (byte) (a + b);
        // fix 3: compound assignment casts for you
        a += b;
        System.out.println(sum + " " + cast + " " + a);
    }
}
