package session04;

public class Clothing extends Product {
    private  String size;

    public Clothing(int id, String name, float price, String size) {
        super(id, name, price);
        setSize(size);
    }

    public String getSize() { return size; }
    public void setSize(String size) {
        if(size == null || size.trim().isEmpty()) {
            throw new IllegalArgumentException("Size cannot be null or empty");
        }
        if(size.equalsIgnoreCase("S") || size.equalsIgnoreCase("M") || size.equalsIgnoreCase("L")) {
            this.size = size;
        } else {
            throw new IllegalArgumentException("Size must be either S or M or L");
        }
    }

    @Override
    public String toString() {
        return "ID: " + getId() + ", Brand: " + getSize() + ", Name: " + getName() + ", Price: " + getPrice();
    }
}
