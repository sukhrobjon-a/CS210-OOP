public abstract class Shape {
    protected String color;

    public Shape(String color) {
        this.color = color;
    }

    public abstract double getArea();
    public abstract double getPerimeter();

    public void displayInfo() {
        System.out.println(getClass().getSimpleName() +
                " | Color: " + color +
                " | Area: " + getArea() +
                " | Perimeter: " + getPerimeter());
    }
}