package session_03;

import java.util.Scanner;

public class Painting extends Item {
    private int height;
    private int width;
    private boolean isWaterColor;
    private boolean isFramed;

    public Painting() { super(); }
    public Painting(String id, int value, String creator, int width, int height) {
        super(id, value, creator);
    }

    public int getHeight() { return this.height; }
    public int getWidth() { return this.width; }
    public boolean isWaterColor() { return this.isWaterColor; }
    public boolean isFramed() { return this.isFramed; }

    public void setHeight(int height) {
        if(height < 0) {
            throw new IllegalArgumentException("Height cannot be negative");
        }
        this.height = height;
    }

    public void setWidth(int width) {
        if(width < 0) {
            throw new IllegalArgumentException("Width cannot be negative");
        }
        this.width = width;
    }

    public void setWaterColor(boolean isWaterColor) {
        this.isWaterColor = isWaterColor;
    }

    public void setFramed(boolean isFramed) {
        this.isFramed = isFramed;
    }

    @Override
    public void input() {
        super.input();
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter height: ");
        int height = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter width: ");
        int width = sc.nextInt();
        sc.nextLine();

        setHeight(height);
        setWidth(width);
    }

    @Override
    public String toString() {
        return "Painting={" + "Height: " + height + ", Width: " + width + '}';
    }
}
