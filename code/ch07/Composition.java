import java.util.ArrayList;
import java.util.List;

public class Composition {
    // Inheritance would say "a Playlist IS-A ArrayList" and expose add/remove/clear to everyone.
    // Composition says "a Playlist HAS-A list" and exposes only what makes sense.
    static class Playlist {
        private final List<String> songs = new ArrayList<>();
        void add(String song) { if (!songs.contains(song)) songs.add(song); }
        int size() { return songs.size(); }
        List<String> songs() { return List.copyOf(songs); }      // Java 10: read-only copy
    }

    public static void main(String[] args) {
        Playlist p = new Playlist();
        p.add("Duke's Theme");
        p.add("Duke's Theme");      // duplicate ignored: the Playlist enforces its own rule
        p.add("Coffee Break");
        System.out.println(p.size() + " " + p.songs());
    }
}
