package session_04;

public class Electronics extends Product {
    private String brand;

    public Electronics(int id, String name, float price, String brand) {
        super(id, name, price);
        setBrand(brand);
    }

    public String getBrand() { return brand; }
    public void setBrand(String brand) {
        if(brand == null || brand.trim().isEmpty()) {
            throw new IllegalArgumentException("brand cannot be null or empty");
        }
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "ID: " + getId() + ", Brand: " + getBrand() + ", Name: " + getName() + ", Price: " + getPrice();
    }
}
