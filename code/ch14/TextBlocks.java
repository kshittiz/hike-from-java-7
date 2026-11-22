public class TextBlocks {
    public static void main(String[] args) {
        String java7 = "{\n" +
                       "  \"title\": \"A Hike from Java 7\",\n" +
                       "  \"chapters\": 20\n" +
                       "}";

        String java15 = """
                {
                  "title": "A Hike from Java 7",
                  "chapters": 20
                }""";                                        // Java 15: text block

        System.out.println(java15);
        System.out.println("same text: " + java7.equals(java15));

        String sql = """
                SELECT name
                  FROM releases
                 WHERE lts = true \
                ORDER BY year""";                             // \ joins lines (Java 15)
        System.out.println(sql);
    }
}
