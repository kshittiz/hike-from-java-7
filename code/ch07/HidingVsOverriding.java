public class HidingVsOverriding {
    static class Parent {
        String name = "parent field";
        String name() { return "parent method"; }
    }
    static class Child extends Parent {
        String name = "child field";                         // hides Parent.name
        @Override String name() { return "child method"; }   // overrides Parent.name()
        String both() { return name + " / " + super.name + " / " + super.name(); }
    }

    public static void main(String[] args) {
        Parent p = new Child();
        System.out.println(p.name);         // fields: chosen by the reference type
        System.out.println(p.name());       // methods: chosen by the object
        System.out.println(new Child().both());
    }
}
