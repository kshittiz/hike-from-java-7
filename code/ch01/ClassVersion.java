import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ClassVersion {
    public static void main(String[] args) throws IOException {
        // The first 8 bytes of every .class file: magic number, minor, major.
        try (InputStream in =
                     ClassVersion.class.getResourceAsStream("Probe.class");
             DataInputStream data = new DataInputStream(in)) {
            int magic = data.readInt();
            int minor = data.readUnsignedShort();
            int major = data.readUnsignedShort();
            System.out.printf("magic = 0x%X%n", magic);
            System.out.println("class file major version = " + major
                    + "  ->  built for Java " + (major - 44));
        }
    }
}
