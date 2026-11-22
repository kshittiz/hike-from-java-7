public class References {
    public static void main(String[] args) {
        Point p = new Point();     // one object on the heap
        Point q = p;               // a second reference to the SAME object
        q.x = 10;
        System.out.println("p.x = " + p.x);         // 10: one object, two names

        q = new Point();           // q now refers to a new object; p is unaffected
        q.x = 99;
        System.out.println("p.x = " + p.x + ", q.x = " + q.x);
        System.out.println("p == q ? " + (p == q));
    }
}
