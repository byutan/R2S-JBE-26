package session_03;

import java.util.Scanner;

public class Item {
    protected String id;
    protected int value;
    protected String creator;

    public Item() {}
    public Item(String id, int value, String creator) {
        setId(id);
        setValue(value);
        setCreator(creator);
    }

    public String getId() { return id; }
    public int getValue() { return value; }
    public String getCreator() { return creator; }

    public void setId(String id) {
        if(id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("id cannot be null or empty");
        }
        this.id = id;
    }

    public void setValue(int value) {
        if(value < 0) {
            throw new IllegalArgumentException("value cannot be negative");
        }
        this.value = value;
    }

    public void setCreator(String creator) {
        if(creator == null || creator.trim().isEmpty()) {
            throw new IllegalArgumentException("creator cannot be null or empty");
        }
        this.creator = creator;
    }

    public void input() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter ID: ");
        id = sc.nextLine().trim();
        setId(id);

        System.out.print("Enter Value: ");
        value = sc.nextInt();
        sc.nextLine();
        setValue(value);

        System.out.print("Enter Creator: ");
        creator = sc.nextLine().trim();
        setCreator(creator);
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Value: " + value + ", Creator: " + creator;
    }
}
