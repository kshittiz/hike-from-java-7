public class NestedKinds {
    private int counter = 7;

    static class StaticNested {                     // no outer object needed
        String hi() { return "static nested"; }
    }

    class Inner {                                   // tied to an outer object
        String hi() { return "inner sees counter = " + counter; }
    }

    Runnable makeTasks(String label) {
        class Local implements Runnable {           // visible only inside this method
            public void run() { System.out.println("local class: " + label); }
        }
        Runnable anonymous = new Runnable() {       // no name; one instance
            public void run() { System.out.println("anonymous class: " + label); }
        };
        new Local().run();
        return anonymous;
    }

    public static void main(String[] args) {
        System.out.println(new StaticNested().hi());
        NestedKinds outer = new NestedKinds();
        NestedKinds.Inner inner = outer.new Inner();   // note the outer.new syntax
        System.out.println(inner.hi());
        outer.makeTasks("hello").run();
        Runnable lambda = () -> System.out.println("lambda: no class file of its own");
        lambda.run();
    }
}
