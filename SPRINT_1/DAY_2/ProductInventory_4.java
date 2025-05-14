package SPRINT_1_DAY_2;

import java.util.HashMap;
import java.util.Scanner;

public class ProductInventory_4 {
    private HashMap<String,Integer> inventory;

    public ProductInventory_4(){
        inventory = new HashMap<>();
    }

    public void addProduct(String name, int quant){
        if(inventory.containsKey(name)){
            System.out.println("Product Alreay exist");
        }else{
            inventory.put(name, quant);
            System.out.println("Product added");
        }
    }

    public void update(String name, int quant){

        if(inventory.containsKey(name)){
            inventory.put(name,quant);
            System.out.println("Quantity of the product updated");
        }else{
            System.out.println("Product not Found");
        }
    }

    public void remove(String name){
        if(inventory.remove(name)!=null){
            System.out.println(name+" Product has been removed ");
        }else{
            System.out.println("Product not found");
        }
    }

    public void checkstock(String name){
        if(inventory.containsKey(name)){
            int quantity = inventory.get(name);
            if(quantity>0){
                System.out.println("This product is in stock with "+quantity+" Quantity");
            }else{
                System.out.println("Product is out of stock");
            }
        }else{
            System.out.println("product not found");
        }
    }

    public void displayInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
        } else {
            System.out.println("\nCurrent Inventory:");
            for (String product : inventory.keySet()) {
                System.out.println(product + " - Quantity: " + inventory.get(product));
            }
        }
}

public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProductInventory_4 pi = new ProductInventory_4();
        int choice;

        do {
            System.out.println("1. Add product");
            System.out.println("2. Update product quantity");
            System.out.println("3. Remove product");
            System.out.println("4. Check stock");
            System.out.println("5. Display all products");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter product name: ");
                    String addName = sc.nextLine();
                    System.out.print("Enter quantity: ");
                    int addQty = sc.nextInt();
                    pi.addProduct(addName, addQty);
                    break;

                case 2:
                    System.out.print("Enter product name: ");
                    String updateName = sc.nextLine();
                    System.out.print("Enter new quantity: ");
                    int updateQty = sc.nextInt();
                    pi.update(updateName, updateQty);
                    break;

                case 3:
                    System.out.print("Enter product name to remove: ");
                    String removeName = sc.nextLine();
                    pi.remove(removeName);
                    break;

                case 4:
                    System.out.print("Enter product name to check: ");
                    String checkName = sc.nextLine();
                    pi.checkstock(checkName);
                    break;

                case 5:
                    pi.displayInventory();
                    break;

                case 6:
                    System.out.println("Exiting... Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 6);

        sc.close();
    }
}


