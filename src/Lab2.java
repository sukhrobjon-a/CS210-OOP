import java.util.Scanner;
import java.lang.Math;
public class Lab2 {
    public static void main(String[] args) {

//        //Problem 1
//        Scanner in = new Scanner(System.in);
//        System.out.print("Enter how many cappuccinos: ");
//        int c = in.nextInt();
//        System.out.print("Enter how many muffins: ");
//        int m = in.nextInt();
//        System.out.printf("Grand Total: $ %.2f", ((4.50*c+3.00*m)*1.08+5.00));
//        in.close();



//        //Problem 2
//        Scanner in = new Scanner(System.in);
//        System.out.print("Enter the distance: ");
//        int distance = in.nextInt();
//        System.out.print("Enter the cost of fuel: ");
//        double l = (double)distance/100 * 8.5;
//        double cost = in.nextDouble()*l;
//        System.out.println("Distance: " +  distance + " km");
//        System.out.println("Fuel needed: "+ l + " Litres");
//        System.out.printf("Total cost: %.2f UZS", cost);
//        in.close();



//        //Problem 3
//        Scanner in = new Scanner(System.in);
//        System.out.print("Enter the total number of seconds: ");
//        int s = in.nextInt();
//        int h = s/3600;
//        int m = s/60 - h*60;
//        s = s - h*3600 - m*60;
//        System.out.println(h + " hours, " + m + " minutes, " + s + " seconds");
//        in.close();



//        //Problem 4
//        Scanner in = new Scanner(System.in);
//        System.out.println("Enter your age: ");
//        int age = in.nextInt();
//        if (age<=12){
//            System.out.println("Ticket Price: $7");
//        } else if (age < 18){
//            System.out.println("Ticket Price: $10");
//        } else if (age < 65) {
//            System.out.println("Ticket Price: $15");
//        } else {
//            System.out.println("Ticket Price: $10");
//        }
//        in.close();



//        //Problem 5
//        Scanner in = new Scanner(System.in);
//        System.out.println("Enter the year to check: ");
//        int year = in.nextInt();
//        if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
//            System.out.println(year + " is a leap year");
//        } else {
//            System.out.println(year + " is not a leap year");
//        }
//        in.close();



//        //Problem 6
//        Scanner in = new Scanner(System.in);
//        System.out.println("Enter the weight: ");
//        double w = in.nextDouble();
//        if (w<2){
//            System.out.println("Shipping cost: $5.00");
//        } else if (w<=10){
//            System.out.println("Shipping cost: $10.00");
//        } else{
//            System.out.printf("Shipping cost: $ %.2f", 10.00+(w-10)*2.00);
//        }
//        in.close();



//        //Problem 7
//        int pin = (int)(Math.random()*9000) + 1000;
//        System.out.println("*The correct pin: "+ pin);
//        int count = 0;
//        Scanner in = new Scanner(System.in);
//        System.out.println("Enter the pin: ");
//        int pass = in.nextInt();
//
//        while (pass != pin && count < 2){
//            System.out.println("Try again: ");
//            pass = in.nextInt();
//            count += 1;
//        }
//
//        if (pass==pin){
//            System.out.println("Correct Password!");
//        } else {
//            System.out.println("Card is blocked");
//        }
//
//        in.close();



//        //Problem 8
//        Scanner in = new Scanner(System.in);
//        System.out.println("Enter the population");
//        int p = in.nextInt();
//        System.out.println("Enter growth rate: ");
//        double r = in.nextDouble();
//        int temp = p;
//        int c = 0;
//
//        while (temp < 2*p){
//            temp *= (1+r/100);
//            c+=1;
//            System.out.println("Year " + c + ": "+ temp);
//        }
//
//        System.out.println("It will take " + c + " years to double.");
//        in.close();



//        //Problem 9
//        System.out.println("Enter the number n: ");
//        Scanner in = new Scanner(System.in);
//        int n = in.nextInt();
//
//        for (int i = 1; i<=n; i++){
//            if (i%3==0 && i%5==0){
//                System.out.print("FizzBuzz");
//            } else if (i%3==0){
//                System.out.print("Fizz");
//            } else if (i%5==0){
//                System.out.print("Buzz");
//            } else{
//                System.out.print(i);
//            }
//
//            if (i<n){
//                System.out.print(", ");
//            }
//        }
//        in.close();



//        //Problem 10
//        Scanner in = new Scanner(System.in);
//        System.out.print("Enter the deposit amoount: ");
//        double d = in.nextDouble();
//        System.out.print("Enter the interest rate: ");
//        double r = in.nextDouble();
//        System.out.print("Enter the number of years: ");
//        int y = in.nextInt();
//
//        int c = 0;
//        while (c<y){
//            c+=1;
//            d *= 1+r/100;
//            System.out.printf("Year %d: $%.2f%n", c, d);
//        }

    }
}