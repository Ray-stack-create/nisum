package SPRINT_1_DAY_2;
import java.util.*;

class MenuItem {
    private String name;
    private String description;
    private double price;

    public MenuItem(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String toString() {
        return name + " - " + description + " ($" + price + ")";
    }
}

public class menu_8 {

    private LinkedHashMap<String, HashMap<String, MenuItem>> menu;

    public menu_8() {
        menu = new LinkedHashMap<>();
        menu.put("Appetizer", new HashMap<>());
        menu.put("Main Course", new HashMap<>());
        menu.put("Dessert", new HashMap<>());
    }

    public void addItem(String category, String name, String description, double price) {
        if (!menu.containsKey(category)) {
            menu.put(category, new HashMap<>());
        }
        menu.get(category).put(name, new MenuItem(name, description, price));
        System.out.println("Item added: " + name);
    }

    public void removeItem(String category, String name) {
        if (menu.containsKey(category) && menu.get(category).remove(name) != null) {
            System.out.println("Item removed: " + name);
        } else {
            System.out.println("Item not found.");
        }
    }

    public void updateItem(String category, String name, String newDescription, double newPrice) {
        if (menu.containsKey(category) && menu.get(category).containsKey(name)) {
            MenuItem item = menu.get(category).get(name);
            item.setDescription(newDescription);
            item.setPrice(newPrice);
            System.out.println("Item updated: " + name);
        } else {
            System.out.println("Item not found.");
        }
    }

    public void displayMenu() {
        System.out.println("\nRestaurant Menu:");
        for (String category : menu.keySet()) {
            System.out.println("\n-- " + category + " --");
            HashMap<String, MenuItem> items = menu.get(category);
            if (items.isEmpty()) {
                System.out.println("No items in this category.");
            } else {
                for (MenuItem item : items.values()) {
                    System.out.println(item);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        menu_8 rm = new menu_8();
        int choice;

        do {
            System.out.println("\n1. Add Menu Item");
            System.out.println("2. Remove Menu Item");
            System.out.println("3. Update Menu Item");
            System.out.println("4. Display Menu");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter category (Appetizer/Main Course/Dessert): ");
                    String category = sc.nextLine();
                    System.out.print("Enter item name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter description: ");
                    String desc = sc.nextLine();
                    System.out.print("Enter price: ");
                    double price = sc.nextDouble();
                    rm.addItem(category, name, desc, price);
                    break;

                case 2:
                    System.out.print("Enter category: ");
                    String remCat = sc.nextLine();
                    System.out.print("Enter item name to remove: ");
                    String remItem = sc.nextLine();
                    rm.removeItem(remCat, remItem);
                    break;

                case 3:
                    System.out.print("Enter category: ");
                    String updCat = sc.nextLine();
                    System.out.print("Enter item name to update: ");
                    String updName = sc.nextLine();
                    System.out.print("Enter new description: ");
                    String newDesc = sc.nextLine();
                    System.out.print("Enter new price: ");
                    double newPrice = sc.nextDouble();
                    rm.updateItem(updCat, updName, newDesc, newPrice);
                    break;

                case 4:
                    rm.displayMenu();
                    break;

                case 5:
                    System.out.println("Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 5);

        sc.close();
    }
}
