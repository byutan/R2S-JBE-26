package session_03;

import java.util.Scanner;

public class AntiqueShop {
    static void notifyAddItem(boolean isAdded) {
        if(isAdded) {
            System.out.println("Item added successfully");
        } else {
            System.out.println("Item not added successfully");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ItemList itemList = new ItemList();

        while(true) {
            System.out.println("----WELCOME TO ANIQUE SHOP MANAGEMENT----");
            System.out.println("1. Add an item");
            System.out.println("2. Display all items");
            System.out.println("3. Find an item by creator");
            System.out.println("4. Display item by type");
            System.out.println("5. Quit");

            String choice =  sc.nextLine().trim();
            switch (choice) {
                case "1":
                    System.out.println("1. Statue");
                    System.out.println("2. Painting");
                    System.out.println("3. Vase");
                    System.out.println("4. Exit");

                    String option = sc.nextLine().trim();

                    Item newItem;
                    boolean isAdded;

                    switch (option) {
                        case "1":
                            newItem = new Statue(); //upcasting
                            newItem.input();
                            isAdded = itemList.addItem(newItem);
                            notifyAddItem(isAdded);
                            break;

                        case "2":
                            newItem = new Painting(); //upcasting
                            newItem.input();
                            isAdded = itemList.addItem(newItem);
                            notifyAddItem(isAdded);
                            break;

                        case "3":
                            newItem = new Vase(); //upcasting
                            newItem.input();
                            isAdded = itemList.addItem(newItem);
                            notifyAddItem(isAdded);
                            break;

                        case "4":
                            break;

                        default:
                            System.out.println("Invalid option");
                            break;
                    }
                    break;

                case "2":
                    itemList.displayAll();
                    break;

                case "3":
                    System.out.print("Enter item creator: ");
                    String creator = sc.nextLine().trim();

                    Item item = itemList.findItem(creator);
                    if(item != null) {
                        System.out.println(item.toString());
                    }
                    break;

                case "4":
                    System.out.print("Enter item type: ");
                    String type = sc.nextLine().trim();

                    itemList.displayItemsByType(type);
                    break;

                case "5":
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
