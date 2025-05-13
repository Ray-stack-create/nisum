package SPRINT_1_DAY_1;

class Cart {
    String itemName;
    int itemVal;
    int itemId;

    public Cart(String itemName, int itemVal, int itemId) {
        if(itemId<=0||itemName==null||itemName.isEmpty()||itemVal<=0){
            System.out.println("Invalid Input");
        }
        this.itemName = itemName;
        this.itemVal = itemVal;
        this.itemId = itemId;
    }
}


public class eCart_4{
    public static void main(String[] args) {
        Cart[] items = {
            new Cart("Pen", 35, 232),
            new Cart("Notebook", 74, 653),
            new Cart("Pencil", 87, 476)
        };

        int itemsCount = items.length;
        int  orderTotal = 0;
        for (Cart item : items) {
            orderTotal += item.itemVal;
        }

        System.out.println("Items Count: " + itemsCount);
        System.out.println("Order Total: ₹" + orderTotal);
    }
}