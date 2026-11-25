public class WeakerAccess {
    static class Parent { public void greet() { } }
    static class Child extends Parent {
        @Override void greet() { }  // package-private is weaker than public
    }
}
