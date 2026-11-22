import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class OldDateTraps {
    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));      // the old API silently uses a global default
        Calendar cal = new GregorianCalendar(2013, 7, 1);       // month 7 is AUGUST: months start at 0
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        System.out.println("asked for 2013-7-1, got " + fmt.format(cal.getTime()));

        Date date = cal.getTime();
        Date copy = date;
        copy.setTime(0);                                       // Date is mutable: shared objects change
        System.out.println("original changed too: " + (date.getTime() == 0));
        System.out.println("year field of new Date(0): " + new Date(0).getYear() + " (years since 1900)");
    }
}
