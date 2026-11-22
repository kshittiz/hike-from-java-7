public class ConcatOperator {
    public static void main(String[] args) {
        System.out.println(1 + 2 + "3");      // left to right: 3 + "3"
        System.out.println("1" + 2 + 3);      // "12" + 3
        System.out.println('a' + 'b' + "c");  // char + char is an int: 195 + "c"
        System.out.println("a" + 'b' + 'c');
        String nothing = null;
        System.out.println("value: " + nothing);
    }
}
