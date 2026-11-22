public class Shadowing {
    int width = 1;

    void resize(int width) {
        width = width * 2;            // changes the PARAMETER (data shadowing)
        System.out.println("parameter = " + width + ", field = " + this.width);
        this.width = width;           // 'this' reaches the field
        System.out.println("field now = " + this.width);
    }

    public static void main(String[] args) {
        new Shadowing().resize(5);    // an anonymous object: used once, never named
    }
}
