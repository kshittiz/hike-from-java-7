public class DiamondDefault {
    interface Walker  { default String move() { return "walk"; } }
    interface Swimmer { default String move() { return "swim"; } }

    static class Duck implements Walker, Swimmer {
        @Override public String move() {                       // required: the defaults conflict
            return Walker.super.move() + " and " + Swimmer.super.move();
        }
    }

    public static void main(String[] args) {
        System.out.println(new Duck().move());
    }
}
