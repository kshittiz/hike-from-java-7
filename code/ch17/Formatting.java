import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class Formatting {
    public static void main(String[] args) {
        // immutable, thread-safe
        DateTimeFormatter f =
                DateTimeFormatter.ofPattern("dd MMM uuuu", Locale.US);
        LocalDate d = LocalDate.of(2013, 7, 1);
        System.out.println(d.format(f));
        System.out.println(LocalDate.parse("21 Nov 2026", f));
        System.out.println(d.format(DateTimeFormatter.ISO_DATE));
        try {
            LocalDate.parse("2026-02-30");
        } catch (DateTimeParseException e) {
            System.out.println("rejected: " + e.getMessage());
        }
    }
}
