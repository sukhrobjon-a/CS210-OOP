import java.util.Scanner;
import java.lang.Math;
public class Week3_Lab1 {
    public static void main(String[] args) {
        //Problem1();
        //Problem2();
        //Problem3();
        //Problem4();
        //Problem5();
        //Problem6();
        //Problem8();
    }

    public static void Problem1() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the string to reverse: ");
        String n = in.nextLine();
        n = reverse(n);
        System.out.println(n);
        in.close();
    }

    public static String reverse(String s) {
        String n1 = "";
        for (int i = s.length() - 1; i >= 0; i--) {
            n1 += s.charAt(i);
        }
        return n1;
    }


    public static void Problem2() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = in.nextLine();
        System.out.print("Enter a character: ");
        char ch = in.next().charAt(0);
        int result = count(input, ch);
        System.out.println("Number of occurrences: " + result);
        in.close();
    }

    public static int count(String str, char a) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == a) {
                count++;
            }
        }
        return count;
    }


    public static void Problem3() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = in.nextLine();
        countUL(input);
        in.close();
    }

    public static void countUL(String s) {
        int U = 0;
        int L = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= 'a' && s.charAt(i) <= 'z') {
                L += 1;
            } else if (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z') {
                U += 1;
            }
        }
        System.out.println("The upprecase letters: " + U);
        System.out.println("The lowercase letters: " + L);
    }

    public static void Problem4() {
        Scanner in = new Scanner(System.in);
        double[] arr = new double[10];
        for (int i = 1; i <= 10; i++) {
            System.out.println("Enter the element " + i);
            arr[i - 1] = in.nextDouble();
        }
        int index = indexOfSmallestElement(arr);
        System.out.println("The index of the smallest number is " + index);
    }

    public static int indexOfSmallestElement(double[] array) {
        int s = 1;
        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[s - 1]) {
                s = i + 1;
            }
        }
        return s;
    }

    public static void Problem5() {
        Scanner input = new java.util.Scanner(System.in);

        System.out.print("Enter a string: ");
        String userInput = input.nextLine();

        System.out.println("Sorted string: " + sort(userInput));
    }

    public static String sort(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < chars.length; j++) {
                if (chars[j] < chars[minIndex]) {
                    minIndex = j;
                }
            }

            char temp = chars[i];
            chars[i] = chars[minIndex];
            chars[minIndex] = temp;
        }

        return new String(chars);
    }


    public static void Problem6(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the number of elements: ");
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i =0; i<n; i++){
            System.out.print("Enter element " + (i+1) + ": ");
            arr[i] = in.nextInt();
        }
        if (isConsecutiveFour(arr)){
            System.out.println("The array has four consecutive numbers");
        } else{
            System.out.println("The array has no four consecutive numbers");
        }

    }

    public static boolean isConsecutiveFour(int[] values) {
        for (int i = 0; i <= values.length - 4; i++) {
            if (values[i] == values[i + 1] &&
                    values[i] == values[i + 2] &&
                    values[i] == values[i + 3]) {
                return true;
            }
        }
        return false;
    }


    public static void Problem8(){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String text = input.nextLine();
        System.out.println("Vowels: " + countVowels(text));
        System.out.println("Consonants: " + countConsonants(text));
        input.close();
    }

    public static int countVowels(String s) {
        int count = 0;
        s = s.toLowerCase();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
                if (ch == 'a' || ch == 'e' || ch == 'i' ||
                        ch == 'o' || ch == 'u') {
                    count++;
                }
            }
        return count;
    }

    public static int countConsonants(String s) {
        int count = 0;
        s = s.toLowerCase();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isLetter(ch)) {
                if (!(ch == 'a' || ch == 'e' || ch == 'i' ||
                        ch == 'o' || ch == 'u')) {
                    count++;
                }
            }
        }
        return count;
    }

}
