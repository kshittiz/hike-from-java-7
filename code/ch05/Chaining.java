public class Chaining {
    final String name;
    final int age;

    Chaining() {
        this("unknown");                  // must be the first statement (before Java 25)
        System.out.println("  no-arg constructor");
    }
    Chaining(String name) {
        this(name, 0);
        System.out.println("  one-arg constructor");
    }
    Chaining(String name, int age) {      // the one constructor that does the real work
        this.name = name;
        this.age = age;
        System.out.println("  two-arg constructor");
    }

    public static void main(String[] args) {
        Chaining c = new Chaining();
        System.out.println(c.name + ", " + c.age);
    }
}
