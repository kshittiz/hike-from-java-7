import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class SameOrEqual {
    // A class that forgets equals and hashCode
    static class TicketV1 {
        final String trail;
        final int seat;

        TicketV1(String trail, int seat) {
            this.trail = trail;
            this.seat = seat;
        }
    }

    // The Java 7 way: write both, using java.util.Objects
    static class TicketV2 {
        final String trail;
        final int seat;

        TicketV2(String trail, int seat) {
            this.trail = trail;
            this.seat = seat;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof TicketV2)) return false;
            TicketV2 t = (TicketV2) o;
            return seat == t.seat && trail.equals(t.trail);
        }

        @Override
        public int hashCode() {
            return Objects.hash(trail, seat);
        }
    }

    // The Java 16 way: a record writes both for you
    record Ticket(String trail, int seat) { }

    public static void main(String[] args) {
        TicketV1 a = new TicketV1("Summit", 7);
        TicketV1 b = new TicketV1("Summit", 7);
        System.out.println("a == b          : " + (a == b));
        System.out.println("a.equals(b)     : " + a.equals(b));

        Set<TicketV1> v1 = new HashSet<>(Set.of(a));
        System.out.println("V1 set has b?   : " + v1.contains(b));

        Set<TicketV2> v2 = new HashSet<>();
        v2.add(new TicketV2("Summit", 7));
        System.out.println("V2 set has it?  : "
                + v2.contains(new TicketV2("Summit", 7)));

        Set<Ticket> v3 = new HashSet<>();
        v3.add(new Ticket("Summit", 7));
        System.out.println("record set has? : "
                + v3.contains(new Ticket("Summit", 7)));
    }
}
