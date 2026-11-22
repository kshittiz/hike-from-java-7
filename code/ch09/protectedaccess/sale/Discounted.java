package sale;

import shop.Product;

public class Discounted extends Product {
    double halfPrice() { return price / 2; }               // OK: through inheritance, on this object

    double peek(Product other) { return other.price; }     // not OK: through another Product reference
}
