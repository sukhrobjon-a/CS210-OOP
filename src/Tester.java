import java.util.Scanner;
public class Tester {
    public static void main(String[] args){

//        //Problem 1
//        LibraryBook book1 = new LibraryBook();
//        book1.setBookTitle("The Little Prince");
//        book1.setAuthor("Antoine de Saint-Exupéry");
//        book1.setPages(2200);
//
//        LibraryBook book2 = new LibraryBook();
//        book2.setBookTitle("Days Gone By");
//        book2.setAuthor("Abdulla Qadiri");
//        book2.setPages(-19);
//
//        System.out.println(book1.getBookTitle() +", "+ book1.getAuthor() +", "+ book1.getPages());
//        System.out.print(book2.getBookTitle() +", "+ book2.getAuthor() +", " + book2.getPages());


//        //Problem 2
//        Scanner input = new Scanner(System.in);
//        Sensor[] sensors = new Sensor[5];
//
//        double total = 0.0;
//
//        for (int i = 0; i < sensors.length; i++) {
//
//            System.out.println("Sensor " + (i + 1));
//
//            System.out.print("Enter location: ");
//            String location = input.nextLine();
//
//            System.out.print("Enter temperature (-50 to 50): ");
//            double reading = input.nextDouble();
//            input.nextLine();
//
//            sensors[i] = new Sensor(location, reading);
//
//            total += sensors[i].getReading();
//
//            System.out.println();
//        }
//
//        double average = total / sensors.length;
//        System.out.println("Average temperature reading: " + average);
//
//        input.close();


//        //Problem 3
//        StepCounter.resetGlobalSteps();
//
//        StepCounter userA = new StepCounter();
//        StepCounter userB = new StepCounter();
//
//        userA.walk(1000);
//        userA.walk(2000);
//
//        userB.walk(500);
//        userB.walk(1500);
//
//        System.out.println("User A individual steps: " + userA.getIndividualSteps());
//        System.out.println("User B individual steps: " + userB.getIndividualSteps());
//
//        System.out.println("Total steps: " + StepCounter.getTotalStepsAlUsers());



//        //Problem 4
//        StopWatch stopwatch = new StopWatch();
//
//        stopwatch.start();
//        for (int i = 0; i < 1000000; i++) {
//            MathTool.factorialRecursive(20);
//        }
//        stopwatch.stop();
//        long recursiveTime = stopwatch.getElapsedTime();
//
//
//        stopwatch.start();
//        for (int i = 0; i < 1000000; i++) {
//            MathTool.factorialIterative(20);
//        }
//        stopwatch.stop();
//        long iterativeTime = stopwatch.getElapsedTime();
//
//        System.out.println("Recursive method time: " + recursiveTime + " ms");
//        System.out.println("Iterative method time: " + iterativeTime + " ms");
    }
}
