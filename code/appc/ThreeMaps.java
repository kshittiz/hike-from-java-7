import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class ThreeMaps {
    public static void main(String[] args) {
        // the order hikers reached the summit, with their times
        String[] names = {"Ravi", "Asha", "Mia", "Leo"};
        int[] minutes = {95, 88, 102, 91};

        Map<String, Integer> arrival = new LinkedHashMap<>();
        Map<String, Integer> alphabetical = new TreeMap<>();
        for (int i = 0; i < names.length; i++) {
            arrival.put(names[i], minutes[i]);
            alphabetical.put(names[i], minutes[i]);
        }
        System.out.println("LinkedHashMap : " + arrival);
        System.out.println("TreeMap       : " + alphabetical);

        // TreeMap can also answer "nearest" questions
        TreeMap<Integer, String> byTime = new TreeMap<>();
        arrival.forEach((name, time) -> byTime.put(time, name));
        System.out.println("fastest       : " + byTime.firstEntry());
        System.out.println("first >= 90   : " + byTime.ceilingEntry(90));
    }
}
