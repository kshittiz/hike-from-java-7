import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

public class JavaTimeBasics {
    public static void main(String[] args) {
        LocalDate start  = LocalDate.of(2013, Month.JULY, 1);           // months are 1-based, or use the enum
        LocalDate book = LocalDate.of(2026, 11, 21);
        System.out.println(start + " was a " + start.getDayOfWeek());
        System.out.println("plus one month: " + start.plusMonths(1));   // returns a new object
        System.out.println("start unchanged: " + start);

        Period gap = Period.between(start, book);
        System.out.println("gap: " + gap + " = " + gap.getYears() + " years, " + gap.getMonths() + " months, " + gap.getDays() + " days");
        System.out.println("days: " + ChronoUnit.DAYS.between(start, book));

        System.out.println("2024 leap? " + LocalDate.of(2024, 1, 1).isLeapYear());
        System.out.println("next Monday after release day: "
                + LocalDate.of(2026, 9, 15).with(TemporalAdjusters.next(DayOfWeek.MONDAY)));
    }
}
