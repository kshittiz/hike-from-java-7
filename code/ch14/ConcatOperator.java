public class ConcatOperator {
    public static void main(String[] args) {
        // left to right: 3 + "3"
        System.out.println(1 + 2 + "3");
        // "12" + 3
        System.out.println("1" + 2 + 3);
        // char + char is an int: 195 + "c"
        System.out.println('a' + 'b' + "c");
        System.out.println("a" + 'b' + 'c');
        String nothing = null;
        System.out.println("value: " + nothing);
    }
}
