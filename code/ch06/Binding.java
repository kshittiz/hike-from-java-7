public class Binding {
    static class Animal {
        String speak()            { return "..."; }
        static String kind()      { return "animal"; }
    }
    static class Dog extends Animal {
        @Override String speak()  { return "Woof"; }      // overrides
        static String kind()      { return "dog"; }       // hides; does not override
    }

    static String describe(Animal a) { return "describe(Animal)"; }
    static String describe(Dog d)    { return "describe(Dog)"; }

    public static void main(String[] args) {
        Animal a = new Dog();                 // static type Animal, runtime type Dog
        System.out.println(a.speak());        // dynamic binding: chosen at run time -> Dog
        System.out.println(Animal.kind() + " / " + Dog.kind());
        System.out.println(describe(a));      // overloads: chosen at compile time -> Animal
    }
}
