public class HidingVsOverriding {
    static class Parent {
        String name = "parent field";
        String name() { return "parent method"; }
    }
    static class Child extends Parent {
        // hides Parent.name
        String name = "child field";
        // overrides Parent.name()
        @Override String name() { return "child method"; }
        String both() {
            return name + " / " + super.name + " / " + super.name();
        }
    }

    public static void main(String[] args) {
        Parent p = new Child();
        System.out.println(p.name);    // fields: chosen by the reference type
        System.out.println(p.name());  // methods: chosen by the object
        System.out.println(new Child().both());
    }
}
