import java.util.Scanner;

public class Main {

    /*
     * Lets the user pick between the two (possibly three, we'll see) algorithms encompassing this portion of the assignment.
     */
    public static void main(String[] args) {
        int alg = 0;

        // Asks user for input corresponding to one of the three problems.
        while (alg == 0) {
            System.out.print("Welcome to Assignment 4.\nEnter 1 for Problem 1, enter 2 for Problem 2, " +
                    "or enter 3 for all problems to be run: ");
            Scanner userIn = new Scanner(System.in);
            String input = userIn.nextLine();
            if (input.equals("1") || input.equals("2") || input.equals("3")) {
                alg = Integer.parseInt(input);
            } else {
                System.out.println("Error: Valid input not given.");
            }
        }
        System.out.println();

        switch (alg) {
            case 1:
                Problem1.P1();
                break;
            case 2:
                Problem2.P2();
                break;
            default:
                Problem1.P1();
                System.out.println();
                Problem2.P2();
                System.out.println();
                break;
        }
    }
}