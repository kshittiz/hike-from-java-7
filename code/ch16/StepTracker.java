import java.util.List;
import java.util.stream.Gatherer;
import java.util.stream.Gatherers;

public class StepTracker {
    public static void main(String[] args) {
        // steps counted in each 10-minute slot of a 70-minute walk
        List<Integer> steps = List.of(120, 340, 410, 90, 0, 560, 380);

        // 1. half-hour totals: boxes of three slots
        //    (the last box may hold fewer)
        List<Integer> halfHours = steps.stream()
                .gather(Gatherers.windowFixed(3))
                .map(StepTracker::total)
                .toList();
        System.out.println("half-hour totals : " + halfHours);

        // 2. rolling 30-minute total: a window of three
        //    that slides forward one slot at a time
        List<Integer> rolling = steps.stream()
                .gather(Gatherers.windowSliding(3))
                .map(StepTracker::total)
                .toList();
        System.out.println("rolling 30 min   : " + rolling);

        // 3. running total: every in-between result is sent on
        List<Integer> soFar = steps.stream()
                .gather(Gatherers.scan(() -> 0, Integer::sum))
                .toList();
        System.out.println("steps so far     : " + soFar);

        // 4. our own gatherer: drop a reading that repeats
        //    the one just before it
        List<Integer> heartRate = List.of(72, 72, 72, 75, 75, 80, 72);
        List<Integer> changes = heartRate.stream()
                .gather(dropRepeats())
                .toList();
        System.out.println("heart rate       : " + changes);
    }

    static int total(List<Integer> box) {
        return box.stream().mapToInt(Integer::intValue).sum();
    }

    /** A station that remembers the last value it let through. */
    static <T> Gatherer<T, ?, T> dropRepeats() {
        class Memory {
            T last;
            boolean seenAny;
        }
        return Gatherer.ofSequential(
                Memory::new,              // fresh memory per stream
                Gatherer.Integrator.<Memory, T, T>ofGreedy(
                        (memory, value, downstream) -> {
                    if (memory.seenAny && value.equals(memory.last)) {
                        return true;      // a repeat: send nothing
                    }
                    memory.last = value;
                    memory.seenAny = true;
                    return downstream.push(value); // pass it on
                }));
    }
}
