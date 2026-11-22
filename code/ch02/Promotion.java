public class Promotion {
    public static void main(String[] args) {
        byte a = 10;
        byte b = 20;
        byte sum = a + b;      // does not compile: a + b is an int
        System.out.println(sum);
    }
}
