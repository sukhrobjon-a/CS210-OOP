public class ShapeDemo {
    public static Shape findLargest(Shape[] shapes) {
        Shape largest = shapes[0];

        for (Shape s : shapes) {
            if (s.getArea() > largest.getArea()) {
                largest = s;
            }
        }
        return largest;
    }

    public static void main(String[] args) {

        Shape[] shapes = {
                new Circle("Red", 3),
                new Rectangle("Blue", 4, 5),
                new Triangle("Green", 3, 4, 5),
                new Circle("Yellow", 5),
                new Rectangle("Black", 2, 6),
                new Triangle("White", 5, 5, 6)
        };

        double totalArea = 0;

        for (Shape s : shapes) {
            s.displayInfo();
            totalArea += s.getArea();
        }

        Shape largest = findLargest(shapes);

        System.out.println("\nLargest area: " + largest.getArea());
        System.out.println("Total area: " + totalArea);
    }
}