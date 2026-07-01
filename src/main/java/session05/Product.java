package session05;

public class Product {
    private int productID;
    private String name;
    private double price;
    private int quantityInStock;

    public Product(int productID, String name, double price, int quantityInStock) {
        setProductID(productID);
        setName(name);
        setPrice(price);
        setQuantityInStock(quantityInStock);
    }

    public int getProductID() {
        return productID;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public void setName(String name) {
        if(name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }

    public void setPrice(double price) {
        if(price < 0) {
            throw new IllegalArgumentException(" Price and quantity must be nonnegative.");
        }
        this.price = price;
    }

    public void setQuantityInStock(int quantityInStock) {
        if(quantityInStock < 0) {
            throw new IllegalArgumentException(" Price and quantity must be nonnegative.");
        }
        this.quantityInStock = quantityInStock;
    }

    public String displayProductInfo() {
        return String.format("ID: %-6d | Name: %-20s | Price: %-10f | Stock: %-6d", productID, name,  price, quantityInStock);
    }
}
