package SPRINT_1_DAY_2;
import java.util.ArrayList;
import java.util.Scanner;

class Item {
    String name;
    double price;
    int quant;

    Item(String name, double price, int quant) {
        this.name = name;
        this.price = price;
        this.quant = quant;
    }

    public double totalPrice() {
        return price * quant;
    }

    public String toString() {
        return name + " : $" + price + " x " + quant + " = $" + totalPrice();
    }
}

class ShoppingCart {
    private ArrayList<Item> cart;

    public ShoppingCart() {
        cart = new ArrayList<>();
    }

    public void addItem(String name, double price, int quant) {
        cart.add(new Item(name, price, quant));
        System.out.println(name + " added to the cart.");
    }

    public void removeItem(String name) {
        boolean removed = false;
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).name.equalsIgnoreCase(name)) {
                cart.remove(i);
                System.out.println(name + " removed from the cart.");
                removed = true;
                break;
            }
        }
        if (!removed) {
            System.out.println(name + " not found in the cart.");
        }
    }

    public void viewCart() {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }
        System.out.println("Items in the cart:");
        for (Item item : cart) {
            System.out.println(item);
        }
    }

    public double calculateTotal() {
        double total = 0;
        for (Item item : cart) {
            total += item.totalPrice();
        }
        return total;
    }
}

public class CartApp_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ShoppingCart cart = new ShoppingCart();
        int choice;

        do {
            System.out.println("Select Options from the below");
            System.out.println("1. Add item");
            System.out.println("2. Remove item");
            System.out.println("3. View cart");
            System.out.println("4. Calculate total");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter item name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter price: ");
                    double price = scanner.nextDouble();
                    System.out.print("Enter quantity: ");
                    int quant = scanner.nextInt();
                    cart.addItem(name, price, quant);
                    break;

                case 2:
                    System.out.print("Enter item name to remove: ");
                    String removeName = scanner.nextLine();
                    cart.removeItem(removeName);
                    break;

                case 3:
                    cart.viewCart();
                    break;

                case 4:
                    double total = cart.calculateTotal();
                    System.out.println("Total Price: $" + total);
                    break;

                case 5:
                    System.out.println("Exiting, Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 5);

        scanner.close();
    }
}
