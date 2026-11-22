public class InitOrder {
    static int created;

    static {                                   // runs once, when the class is initialized
        System.out.println("1. static block");
    }

    {                                          // runs for every object, before the constructor body
        created++;
        System.out.println("2. instance initializer (object #" + created + ")");
    }

    InitOrder() {
        System.out.println("3. constructor");
    }

    public static void main(String[] args) {
        System.out.println("main starts");
        new InitOrder();
        new InitOrder();
        System.out.println("objects created: " + InitOrder.created);
    }
}
