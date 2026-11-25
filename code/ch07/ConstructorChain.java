public class ConstructorChain {
    static class Vehicle {
        Vehicle() { System.out.println("1. Vehicle()"); }
    }
    static class Car extends Vehicle {
        Car(String model) {  // compiler inserts super() here
            System.out.println("2. Car(" + model + ")");
        }
    }
    static class SportsCar extends Car {
        SportsCar() {
            super("GT");  // explicit call to a parent constructor
            System.out.println("3. SportsCar()");
        }
    }

    public static void main(String[] args) {
        new SportsCar();
    }
}
