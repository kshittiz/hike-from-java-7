public class ReturnTypeOverload {
    int show(int n)    { return n; }
    String show(int n) { return "n"; }     // differs only by return type
}
