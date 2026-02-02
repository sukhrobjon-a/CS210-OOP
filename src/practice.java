import java.util.Scanner;
import java.lang.Math;
public class practice {
    public static void main(String[] args) {

//        //Problem 3
//        Scanner in = new Scanner(System.in);
//        int grade = in.nextInt();
//        String result;
//
//        if (grade >= 90 && grade <= 100) {
//            result = "A";
//        } else if (grade >= 80 && grade <= 89) {
//            result = "B";
//        } else if (grade >= 70 && grade <= 79) {
//            result = "C";
//        } else if (grade >= 60 && grade <= 69) {
//            result = "D";
//        } else if (grade >= 0 && grade <= 59) {
//            result = "F";
//        } else {
//            result = "Wrong input";
//        }
//
//        System.out.println(result);
//        in.close();


//        //Problem 4
//        Scanner in = new Scanner(System.in);
//        System.out.println("Please enter your weight");
//        double w = in.nextDouble();
//        System.out.println("Please enter your height");
//        double h = in.nextDouble();
//        double result = (w)/(h*h);
//        System.out.printf("Your BMI is: %.2f%n", result);
//
//        if (result < 18.5) {
//            System.out.println("You are underweight");
//        } else if (result < 24.9) {
//            System.out.println("You are Normal");
//        } else if (result < 29.9) {
//            System.out.println("You are overweight");
//        } else {
//            System.out.println("You are obese");
//        }
//
//        in.close();



//        //Problem 5
//        Scanner in = new Scanner(System.in);
//        System.out.println("Please enter N");
//        int n = in.nextInt();
//        int sum = 0;
//        for (int i = 1; i<=n; i++){
//            sum += i*i;
//        }
//        System.out.println(sum);



//        //Problem 6
//        Scanner in = new Scanner(System.in);
//        System.out.println("Please enter N");
//        int n = in.nextInt();
//        int prod = 1;
//        for (int i = 1; i<=n; i++){
//            prod *= i;
//        }
//        System.out.println(prod);
//        in.close();





//        //Probelem 7
//        Scanner in = new Scanner(System.in);
//        System.out.println("Please enter N");
//        int n = in.nextInt();
//        int count = 0;
//        if (n == 0) {
//            count = 1;
//        } else {
//            n = Math.abs(n);
//            while (n > 0) {
//                n /= 10;
//                count++;
//            }
//        }
//
//        System.out.println(count);
//        in.close();



//        //Problem 8
//        Scanner in = new Scanner(System.in);
//        System.out.println("Please enter N");
//        int n = in.nextInt();
//        int reverse = 0;
//        if (n == 0) {
//            reverse = 0;
//        } else {
//            n = Math.abs(n);
//            while (n > 0) {
//                reverse = reverse*10 + n % 10;
//                n /= 10;
//            }
//        }
//
//        System.out.println(reverse);
//        in.close();



//        //Problem 9
//        Scanner in = new Scanner(System.in);
//        System.out.println("Please enter N");
//        int i = in.nextInt();
//        double sum = 0;
//
//        for (int k = 0; k <= i; k++) {
//            sum += Math.pow(-1, k) / (2 * k + 1);
//        }
//
//        double pi = 4 * sum;
//
//        System.out.println(pi);
//        in.close();


//        //Problem 10
//        int h_count = 0, t_count = 0;
//        for (int i = 0; i<100; i++){
//            if (Math.random()>0.5){
//                h_count += 1;
//            } else {
//                t_count += 1;
//            }
//        }
//        System.out.println("Head counts: " + h_count + "\nTail counts: " + t_count);




//        //Problem 11
//        int secret = (int)(Math.random()*50)+1;
//        System.out.println(secret);
//        int count = 0;
//        Scanner in = new Scanner(System.in);
//        while (count < 5){
//            System.out.println("Guess between 1 and 50: ");
//            int i = in.nextInt();
//            if (i == secret){
//                System.out.println("Correct!");
//                break;
//            } else if(i > secret){
//                System.out.println("Too big");
//                count+=1;
//            } else{
//                System.out.println("Too Small");
//                count+=1;
//            }
//        }
//
//        if (count == 5){
//            System.out.println("You lost");
//        } else {
//            System.out.println("You won!");
//        }



        //Problem 12
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the year to check: ");
        int year = in.nextInt();

        if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
            System.out.println(year + " is a leap year");
        } else {
            System.out.println(year + " is not a leap year");
        }

    }
}

