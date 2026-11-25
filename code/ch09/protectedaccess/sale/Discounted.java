package sale;

import shop.Product;

public class Discounted extends Product {
    // OK: through inheritance, on this object
    double halfPrice() { return price / 2; }

    // not OK: through another Product reference
    double peek(Product other) { return other.price; }
}
