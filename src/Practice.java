import java.util.Scanner;
import java.lang.Math;
public class Practice {
    public void main(String[] args){

//        System.out.printf("%d%n", 42);
//        System.out.printf("%.4f", 5.900014567654, "Hello %s%n", "Java");
//        System.out.print("\n");
//        System.out.printf("Hi, %s%n", "Bob");

//        System.out.print("Enter your name: \n");
//        Scanner input = new Scanner(System.in);
//        String name = input.nextLine();
//        System.out.print("Enter your age: \n");
//        Integer age = input.nextInt();
//        input.nextLine();
//        System.out.print("Enter your dream job: \n");
//        String job = input.nextLine();
//        input.close();
//
//        System.out.printf("Name: %s%n", name);
//        System.out.printf("Age %d%n", age);
//        System.out.printf("Drean job: %s", job);

//        System.out.printf("Hi, %50s%n", "Sukhrobjon");
//        System.out.printf("Age: %50d%n", 18);
//        System.out.printf("%,d%n", 1234565432);

//        System.out.print("-----------------------------\n");
//        System.out.printf("   Java's Primitive Types\n");
//        System.out.print("-----------------------------\n");
//        System.out.printf("| %10s | %-10s | %2s%n","Category", "Name  ", "Bits");
//        System.out.print("-----------------------------\n");
//        System.out.printf("| %-10s | %-10s | %04d%n", "Floating", "double", 64);
//        System.out.printf("| %-10s | %-10s | %04d%n", "Floating", "float", 32);
//        System.out.printf("| %-10s | %-10s | %04d%n", "Integral", "long", 64);
//        System.out.printf("| %-10s | %-10s | %04d%n", "Integral", "int", 32);
//        System.out.printf("| %-10s | %-10s | %04d%n", "Integral", "char", 16);
//        System.out.printf("| %-10s | %-10s | %04d%n", "Integral", "short", 16);
//        System.out.printf("| %-10s | %-10s | %04d%n", "Integral", "byte", 8);
//        System.out.printf("| %-10s | %-10s | %04d%n", "Boolean", "boolen", 1);
//        System.out.printf("| %-10s | %-10s | %04d%n", "Boolean", "boolen", 1);




//        //Problem 1
//        System.out.println("Suxrobjon Abdubannonov");
//        System.out.println("94 Mirzo Ulugbek");
//        System.out.println("Tashkent, 100000");
//        System.out.println("Uzbekistan");





//        //Problem 2
//        Scanner in = new Scanner(System.in);
//        System.out.println("Enter your name: ");
//        String name = in.nextLine();
//        System.out.printf("Hey, %s%n", name);
//        in.close();





//        //Problem 3
//        double width = 4.5;
//        double height = 7.9;
//        double area = width * height;
//        double perimeter = 2 * (width + height);
//
//        System.out.printf("area = %.1f%n", area);
//        System.out.printf("perimeter = %.1f%n", perimeter);





//        //Problem 4
//        double pi = 3.1415926535;
//        Scanner input = new Scanner(System.in);
//        System.out.println("Enter the radius and lenght of a cylinder: ");
//        double r = input.nextDouble();
//        input.nextLine();
//        double l= input.nextDouble();
//        double area1 = r*r*pi;
//        double volume1 = area1*l;
//        System.out.printf("area (1d.p) = %.1f%n", area1);
//        System.out.printf("volume (1d.p) = %.1f%n", volume1);
//        input.close();





        //Problem 5
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the height and lenght of a equilateral triangle: ");
        double h = input.nextDouble();
        input.nextLine();
        double l= input.nextDouble();
        double area2 = l*l*Math.sqrt(3.0)/4;
        double volume2 = area2*l;
        System.out.printf("area (1d.p) = %.1f%n", area2);
        System.out.printf("volume of triangular prism (1d.p) = %.1f%n", volume2);
        input.close();
    }

}
