public class RunTimeError {
    public static void main(String[] args) {
        // 0, because we pass no arguments
        int zero = args.length;
        // the compiler cannot know this will fail
        System.out.println(10 / zero);
    }
}
