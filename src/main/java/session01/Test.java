package session01;

public class Test {
    public static void main(String[] args) {
        Circle circle = new Circle();
        circle.radius = 12;
        System.out.println(String.format("Area: %.2f", circle.getArea()));
    }
}
