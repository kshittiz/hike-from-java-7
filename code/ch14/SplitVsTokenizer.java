import java.util.StringTokenizer;

public class SplitVsTokenizer {
    public static void main(String[] args) {
        String line = "java,  seven ,,twenty-seven";

        StringTokenizer st = new StringTokenizer(line, ", ");     // legacy, but still works
        StringBuilder tokens = new StringBuilder();
        while (st.hasMoreTokens()) tokens.append(tokens.length() == 0 ? "" : "|").append(st.nextToken());
        System.out.println("StringTokenizer: " + tokens);

        System.out.println("split(\",\"):    " + String.join("|", line.split(",")));            // keeps empties
        System.out.println("split regex:     " + String.join("|", line.split("\\s*,+\\s*")));
    }
}
