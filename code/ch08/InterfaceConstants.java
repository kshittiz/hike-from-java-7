public class InterfaceConstants {
    interface Limits {
        int MAX_USERS = 100;               // implicitly public static final
    }
    public static void main(String[] args) {
        System.out.println(Limits.MAX_USERS);
        Limits.MAX_USERS = 200;
    }
}
