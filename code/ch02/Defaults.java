public class Defaults {
    int count;          // fields get default values
    boolean ready;
    String name;
    double ratio;

    public static void main(String[] args) {
        Defaults d = new Defaults();
        System.out.println(d.count + " " + d.ready + " "
                + d.name + " " + d.ratio);
    }
}
