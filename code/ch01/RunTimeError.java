public class RunTimeError {
    public static void main(String[] args) {
        int zero = args.length;          // 0, because we pass no arguments
        System.out.println(10 / zero);   // the compiler cannot know this will fail
    }
}
