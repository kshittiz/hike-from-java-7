import java.lang.ref.WeakReference;

public class Eligibility {
    static class Node { Node next; }

    public static void main(String[] args) throws InterruptedException {
        Object data = new Object();
        // does not keep the object alive
        WeakReference<Object> watch = new WeakReference<>(data);
        data = null;  // last strong reference gone

        Node a = new Node(), b = new Node();
        a.next = b; b.next = a;  // an island: a <-> b
        WeakReference<Node> island = new WeakReference<>(a);
        a = null; b = null;  // nothing outside points in

        System.gc();  // a request, not a command
        Thread.sleep(100);
        System.out.println("plain object collected: "
                + (watch.get() == null));
        System.out.println("island collected:       "
                + (island.get() == null));
    }
}
