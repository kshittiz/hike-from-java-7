public class ScopedValues {
    // Java 25 (preview in 21-24)
    static final ScopedValue<String> USER = ScopedValue.newInstance();

    static void audit(String action) {
        String who = USER.isBound() ? USER.get() : "anonymous";
        System.out.println(who + " " + action);
    }

    public static void main(String[] args) {
        ScopedValue.where(USER, "asha").run(() -> {
            audit("opened chapter 18");
            audit("ran the samples");
        });
        audit("left");  // outside the scope: not bound
    }
}
