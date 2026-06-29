package session_03;

import java.util.ArrayList;

public class ItemList {
    private ArrayList<Item> list = new ArrayList<Item>();
    private int numOfItem;
    private final int MAX = 100;

    public ItemList() {}

    public boolean addItem(Item item) {
        if(item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }

        if(numOfItem >= MAX) {
            System.out.println("Maximum allowed is " + MAX);
            return false;
        }
        if(numOfItem > 0) {
            for(int i = 0; i < numOfItem; i++) {
                Item shopItem = list.get(i);
                if(shopItem.getId().equals(item.getId())) {
                    System.out.println("Item with id " + item.getId() + " already exists");
                    return false;
                }
            }
        }
        list.add(item);
        numOfItem++;
        System.out.println("Added Item: " + item.toString());
        return true;
    }

    public void displayAll() {
        if(numOfItem > 0) {
            for(Item item : list) {
                System.out.println(item.toString());
            }
        } else {
            System.out.println("No items in this list");
        }
    }

    public Item findItem(String creator) {
        if(creator == null || creator.isEmpty()) {
            throw new IllegalArgumentException("Creator cannot be null or empty");
        }
        for(Item item : list) {
            if(creator.equalsIgnoreCase(item.getCreator())) {
                return item;
            }
        }
        System.out.println("No items with the creator: " + creator);
        return null;
    }

    public void displayItemsByType(String type) {
        if(type == null || type.isEmpty()) {
            throw new IllegalArgumentException("Type cannot be null or empty");
        }
        for(Item item : list) {
            if(item.toString().contains(type)) {
                System.out.println(item.toString());
            }
        }
        System.out.println("No items with the creator: " + type);
    }
}
