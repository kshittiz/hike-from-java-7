import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ZonesAndDst {
    public static void main(String[] args) {
        ZoneId seattle = ZoneId.of("America/Los_Angeles");
        ZonedDateTime beforeJump = ZonedDateTime.of(
                LocalDateTime.of(2026, 3, 8, 1, 30), seattle);
        // clocks jump from 2:00 to 3:00
        ZonedDateTime oneHourLater = beforeJump.plusHours(1);
        System.out.println(beforeJump);
        System.out.println(oneHourLater);
        System.out.println("elapsed: "
                + Duration.between(beforeJump, oneHourLater));

        // a point on the timeline, in UTC
        Instant release = Instant.parse("2026-09-15T17:00:00Z");
        ZoneId kolkata = ZoneId.of("Asia/Kolkata");
        System.out.println("in Kolkata: "
                + release.atZone(kolkata).toLocalDateTime());
        System.out.println("in Seattle: "
                + release.atZone(seattle).toLocalDateTime());
    }
}
