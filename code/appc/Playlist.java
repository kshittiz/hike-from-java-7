import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Playlist {
    // The class itself defines ONE natural order for songs:
    // alphabetical by title.
    record Song(String title, int seconds, int plays)
            implements Comparable<Song> {
        @Override
        public int compareTo(Song other) {
            return title.compareTo(other.title);
        }
    }

    public static void main(String[] args) {
        List<Song> songs = new ArrayList<>(List.of(
                new Song("Summit", 307, 98),
                new Song("Base Camp", 266, 41),
                new Song("River Crossing", 295, 98),
                new Song("Night Hike", 185, 12)));

        // null means "use the natural order" (compareTo)
        songs.sort(null);
        System.out.println("by title    : " + titles(songs));

        // an order supplied from outside: shortest first
        songs.sort(Comparator.comparingInt(Song::seconds));
        System.out.println("by length   : " + titles(songs));

        // most played first; ties broken by title
        songs.sort(Comparator.comparingInt(Song::plays)
                             .reversed()
                             .thenComparing(Song::title));
        System.out.println("most played : " + titles(songs));
    }

    static List<String> titles(List<Song> songs) {
        return songs.stream().map(Song::title).toList();
    }
}
