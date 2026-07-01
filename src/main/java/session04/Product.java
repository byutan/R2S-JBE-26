package session04;

public abstract class Product {
    protected int id;
    protected String name;
    protected float price;

    public Product(int id, String name, float price) {
        setId(id);
        setName(name);
        setPrice(price);
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public float getPrice() { return price; }

    public void setId(int id) {
        if(id < 0) { throw new IllegalArgumentException("ID must be non-negative"); }
        this.id = id;
    }

    public void setName(String name) {
        if(name == null || name.trim().isEmpty()) { throw new IllegalArgumentException("Name must not be empty"); }
        this.name = name;
    }

    public void setPrice(float price) {
        if(price < 0) { throw new IllegalArgumentException("Price must be non-negative"); }
        this.price = price;
    }

    public abstract String toString();
}
