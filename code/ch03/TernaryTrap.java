public class TernaryTrap {
    public static void main(String[] args) {
        System.out.println(true ? 1 : 2.0);                 // both sides promoted to double
        Object o = true ? Integer.valueOf(1) : "one";      // no common numeric type: stays Integer
        System.out.println(o);
        char grade = 'A';
        System.out.println(false ? 1 : grade);             // constant int that fits in char: stays char
    }
}
