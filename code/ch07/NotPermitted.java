public class NotPermitted {
    sealed interface Shape permits Circle { }
    record Circle(double r) implements Shape { }
    record Triangle(double base) implements Shape { }     // not in the permits list
}
