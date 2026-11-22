import module java.base;              // Java 25: every exported package of java.base

public class ModuleImport {
    public static void main(String[] args) {
        List<String> versions = List.of("7", "17", "27");                      // java.util
        Map<String, Integer> lengths = new TreeMap<>();
        for (String v : versions) lengths.put(v, v.length());
        System.out.println(lengths);
        System.out.println(Path.of("book", "chapter09.md").getFileName());     // java.nio.file
    }
}
