public class TernaryTrap {
    public static void main(String[] args) {
        System.out.println(true ? 1 : 2.0);  // both sides promoted to double
        // no common numeric type: stays Integer
        Object o = true ? Integer.valueOf(1) : "one";
        System.out.println(o);
        char grade = 'A';
        // constant int that fits in char: stays char
        System.out.println(false ? 1 : grade);
    }
}
