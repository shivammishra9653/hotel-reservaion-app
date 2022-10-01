package ExperimentWithJava.LearnScanner;

import java.util.Scanner;

public class SnackSurvey2 {
    public static void main(String[] args) {
        boolean keepRunning = true;
        try(Scanner sc = new Scanner(System.in)){
            while(keepRunning){
                try{
                    System.out.println("Snacks for break room?");
                    System.out.println("1. chips");
                    System.out.println("2. candy");
                    System.out.println("3. fruit");
                    System.out.println("please enter your selection?");
                    int select = Integer.parseInt(sc.nextLine());
                    switch (select){
                        case 1:
                            System.out.println("chips");
                            keepRunning = false;
                            break;
                        case 2:
                            System.out.println("candy");
                            keepRunning = false;
                            break;
                        case 3:
                            System.out.println("fruit");
                            keepRunning = false;
                            break;
                        default:
                            System.out.println("enter a number between 1 and 3?");
                    }
                }catch(Exception ex){
                    System.out.println("\nError, Invalid Input");
                }
            }
        }
    }
}
