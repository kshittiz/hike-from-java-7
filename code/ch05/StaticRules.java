public class StaticRules {
    static int instances;          // one copy, shared by every object
    int id;                        // one copy per object

    StaticRules() { id = ++instances; }

    static String describe() {
        // return "id " + id;      // would not compile: no 'this' in a static method
        return instances + " instances so far";
    }

    public static void main(String[] args) {
        StaticRules a = new StaticRules();
        StaticRules b = new StaticRules();
        System.out.println("a.id=" + a.id + " b.id=" + b.id);
        System.out.println(StaticRules.describe());
    }
}
