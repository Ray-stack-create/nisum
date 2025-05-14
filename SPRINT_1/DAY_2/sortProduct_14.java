package SPRINT_1_DAY_2;
import java.util.*;

class Product {
    String name;
    String category;
    double price;

    Product(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String toString() {
        return name + " (" + category + ") - $" + price;
    }
}

public class sortProduct_14 {
    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Laptop", "Electronics", 1200));
        products.add(new Product("Shirt", "Clothing", 40));
        products.add(new Product("TV", "Electronics", 800));
        products.add(new Product("Jeans", "Clothing", 60));

        products.sort(Comparator.comparing((Product p) -> p.category).thenComparing(p -> p.price));

        System.out.println("Sorted Products:");
        for (Product p : products) {
            System.out.println(p);
        }
    }
}
