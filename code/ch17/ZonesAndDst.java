import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ZonesAndDst {
    public static void main(String[] args) {
        ZoneId seattle = ZoneId.of("America/Los_Angeles");
        ZonedDateTime beforeJump = ZonedDateTime.of(LocalDateTime.of(2026, 3, 8, 1, 30), seattle);
        ZonedDateTime oneHourLater = beforeJump.plusHours(1);          // clocks jump from 2:00 to 3:00
        System.out.println(beforeJump);
        System.out.println(oneHourLater);
        System.out.println("elapsed: " + Duration.between(beforeJump, oneHourLater));

        Instant release = Instant.parse("2026-09-15T17:00:00Z");      // a point on the timeline, in UTC
        System.out.println("in Kolkata: " + release.atZone(ZoneId.of("Asia/Kolkata")).toLocalDateTime());
        System.out.println("in Seattle: " + release.atZone(seattle).toLocalDateTime());
    }
}
