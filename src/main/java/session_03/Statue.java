package session_03;

import java.util.Scanner;

public class Statue extends Item {
    private int weight;
    private String color;

    public Statue() { super(); }
    public Statue(String id, int value, String creator, int weight, String color) {
        super(id, value, creator);

    }

    public int getWeight() { return weight; }
    public String getColor() { return color; }

    public void setWeight(int weight) {
        if(weight < 0) {
            throw new IllegalArgumentException("Weight cannot be negative");
        }
        this.weight = weight;
    }

    public void setColor(String color) {
        if(color == null || color.trim().isEmpty()) {
            throw new IllegalArgumentException("Color cannot be null or empty");
        }
        this.color = color;
    }

    @Override
    public void input() {
        super.input();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter weight: ");
        int weight = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter color: ");
        String color = sc.nextLine().trim();

        setWeight(weight);
        setColor(color);
    }

    @Override
    public String toString() {
        return "Statue={" + "Weight: " + weight + ", Color: " + color + '}';
    }
}
