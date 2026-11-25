public class Binding {
    static class Animal {
        String speak()            { return "..."; }
        static String kind()      { return "animal"; }
    }
    static class Dog extends Animal {
        // overrides
        @Override String speak()  { return "Woof"; }
        // hides; does not override
        static String kind()      { return "dog"; }
    }

    static String describe(Animal a) { return "describe(Animal)"; }
    static String describe(Dog d)    { return "describe(Dog)"; }

    public static void main(String[] args) {
        // static type Animal, runtime type Dog
        Animal a = new Dog();
        // dynamic binding: chosen at run time -> Dog
        System.out.println(a.speak());
        System.out.println(Animal.kind() + " / " + Dog.kind());
        // overloads: chosen at compile time -> Animal
        System.out.println(describe(a));
    }
}
