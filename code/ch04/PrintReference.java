public class PrintReference {
    public static void main(String[] args) {
        Point p = new Point();
        String text = p.toString();  // what println(p) prints
        String expected = "Point@" + Integer.toHexString(p.hashCode());
        System.out.println("looks like Point@<hex>: "
                + text.startsWith("Point@"));
        System.out.println("hex is the hash code:   "
                + text.equals(expected));
    }
}
