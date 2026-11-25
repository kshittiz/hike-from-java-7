import java.util.ArrayList;
import java.util.List;

public class WindowsTheOldWay {
    public static void main(String[] args) {
        // steps counted in each 10-minute slot of a 70-minute walk
        List<Integer> steps = List.of(120, 340, 410, 90, 0, 560, 380);

        // half-hour totals: boxes of three slots, built by hand
        List<Integer> halfHours = new ArrayList<>();
        int sum = 0, count = 0;
        for (int slot : steps) {
            sum += slot;
            count++;
            if (count == 3) {           // box full: ship it, start a new one
                halfHours.add(sum);
                sum = 0;
                count = 0;
            }
        }
        if (count > 0) halfHours.add(sum);   // the half-full last box
        System.out.println("half-hour totals : " + halfHours);
    }
}
