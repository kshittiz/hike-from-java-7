public class ThisMeaning {
    void show() {
        Runnable anon = new Runnable() {
            public void run() {
                System.out.println("anonymous this: "
                        + this.getClass().getName());
            }
        };
        Runnable lambda = () -> System.out.println("lambda this:    "
                + this.getClass().getName());
        anon.run();
        lambda.run();
    }

    public static void main(String[] args) {
        new ThisMeaning().show();
    }
}
