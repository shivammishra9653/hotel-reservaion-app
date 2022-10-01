package ExperimentWithJava.LearnScanner;

import java.util.Scanner;

public class UserInputTester {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in);) {


            try {
                System.out.println("Enter a String");
                String userInput = sc.nextLine();
                System.out.println("User Input: " + userInput);
            } catch (Exception ex) {
                System.out.println(ex.getLocalizedMessage());
            } finally {
//            close the scanner resource so we avoid memory leaks:
                sc.close();
            }
        }

    }
}
