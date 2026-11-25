import java.util.StringTokenizer;

public class SplitVsTokenizer {
    public static void main(String[] args) {
        String line = "java,  seven ,,twenty-seven";

        // legacy, but still works
        StringTokenizer st = new StringTokenizer(line, ", ");
        StringBuilder tokens = new StringBuilder();
        while (st.hasMoreTokens()) {
            tokens.append(tokens.length() == 0 ? "" : "|");
            tokens.append(st.nextToken());
        }
        System.out.println("StringTokenizer: " + tokens);

        // keeps empties
        System.out.println("split(\",\"):    "
                + String.join("|", line.split(",")));
        System.out.println("split regex:     "
                + String.join("|", line.split("\\s*,+\\s*")));
    }
}
