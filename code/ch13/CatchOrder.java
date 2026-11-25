import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CatchOrder {
    public static void main(String[] args) {
        try {
            new FileReader("missing.txt").close();
        } catch (IOException e) {             // the parent first...
            System.out.println("I/O problem");
        } catch (FileNotFoundException e) {   // ...makes this unreachable
            System.out.println("no such file");
        }
    }
}
