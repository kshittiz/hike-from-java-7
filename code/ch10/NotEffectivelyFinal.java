public class NotEffectivelyFinal {
    public static void main(String[] args) {
        int count = 0;
        Runnable r = () -> System.out.println(count);
        count++;  // reassigned: no longer effectively final
    }
}
