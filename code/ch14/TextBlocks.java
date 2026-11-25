public class TextBlocks {
    public static void main(String[] args) {
        String java7 = "{\n" +
                       "  \"title\": \"A Hike from Java 7\",\n" +
                       "  \"chapters\": 20\n" +
                       "}";

        // Java 15: text block
        String java15 = """
                {
                  "title": "A Hike from Java 7",
                  "chapters": 20
                }""";

        System.out.println(java15);
        System.out.println("same text: " + java7.equals(java15));

        // \ at a line end joins it to the next line (Java 15)
        String sql = """
                SELECT name
                  FROM releases
                 WHERE lts = true \
                ORDER BY year""";
        System.out.println(sql);
    }
}
