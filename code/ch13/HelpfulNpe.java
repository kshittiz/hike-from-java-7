public class HelpfulNpe {
    static class Address { String city; }
    static class Customer { Address address; }
    static class Order { Customer customer; }

    public static void main(String[] args) {
        Order order = new Order();
        order.customer = new Customer();
        try {
            System.out.println(order.customer.address.city.length());
        } catch (NullPointerException e) {
            // Java 14+: says exactly what was null
            System.out.println(e.getMessage());
        }
    }
}
