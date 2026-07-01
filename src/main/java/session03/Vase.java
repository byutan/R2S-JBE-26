package session03;

import java.util.Scanner;

public class Vase extends Item {
    private int height;
    private String material;

    public Vase() { super(); }
    public Vase(String id, int value, String creator, int height, String material) {
        super(id, value, creator);

        setHeight(height);
        setMaterial(material);
    }

    public int getHeight() { return height; }
    public String getMaterial() { return material; }

    public void setHeight(int height) {
        if(height < 0) {
            throw new IllegalArgumentException("Height cannot be negative");
        }
        this.height = height;
    }

    public void setMaterial(String material) {
        if(material == null || material.trim().isEmpty()) {
            throw new IllegalArgumentException("Material cannot be null or empty");
        }
        this.material = material;
    }

    public void input() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Height: ");
        int height = sc.nextInt();
        sc.nextLine();

        System.out.print("Material: ");
        String material = sc.nextLine().trim();

        setHeight(height);
        setMaterial(material);
    }
    @Override
    public String toString() {
        return "Vase={" + "Height: " + height + ", Material: " + material + '}';
    }
}
