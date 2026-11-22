public class MainOverload {
    public static void main(String[] args) {
        System.out.println("JVM called main(String[]) with " + args.length + " args");
        main(42);               // an ordinary call: main is just a method
    }

    public static void main(int n) {
        System.out.println("I am an overload, called by ordinary code: " + n);
    }
}
