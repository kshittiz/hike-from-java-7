/// Converts between Java release numbers and years.
///
/// Since Java 10, a release ships every **March** and **September**:
///
/// | Release | Ships |
/// |---------|-------|
/// | 26      | March 2026 |
/// | 27      | September 2026 |
///
/// @see #yearOf(int)
public class MarkdownDocs {

    /// Returns the year a feature release (10 or later) shipped.
    ///
    /// @param release the feature release number, such as `27`
    /// @return the year it shipped
    static int yearOf(int release) {
        return 2018 + (release - 10) / 2;
    }

    public static void main(String[] args) {
        System.out.println("Java 27 shipped in " + yearOf(27));
        System.out.println("Java 21 shipped in " + yearOf(21));
    }
}
