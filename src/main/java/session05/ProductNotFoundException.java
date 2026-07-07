package session05;

public class ProductNotFoundException extends Exception {
    public ProductNotFoundException() {}

    public ProductNotFoundException(String message) {
        super(message);
    }
}
