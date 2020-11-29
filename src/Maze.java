/*********************************************************************
 * Name: Nicholas Mel
 * Description: Displays a menu of choices to a user
 * and performs the chosen task. It will keep asking a user to
 * enter the next choice until the choice of 'Q' (Quit) is entered.
*********************************************************************/

import java.io.*;

public class Maze {
    public static void main(String[] args) throws IOException {
        char input1;
        String line;
        int mazeSize = 10;
        String[] mazeInfo = new String[mazeSize];

        printMenu();

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader stdin = new BufferedReader(isr);

        do {
            System.out.println("What action would you like to perform?");
            line = stdin.readLine();
            input1 = line.charAt(0);
            input1 = Character.toUpperCase(input1);

            if (line.length() == 1) {
                // matches one of the case statements
                switch (input1) {
                    case 'E':   //Enter Problem parameter
                        System.out.print("Please enter the maze information\n");

                        for (int i = 0; i < mazeSize; i++) {
                            mazeInfo[i] = stdin.readLine().trim();
                        }

                        //Create a solver for this problem.
                        MazeSolver solver = new MazeSolver(mazeInfo);
                        boolean found = solver.findSolution();

                        if (found) {
                            System.out.print("\nsolution found\n");
                            System.out.print(solver.displayPath());
                        } else {
                            System.out.print("\nsolution not found\n");
                        }
                        System.out.print(solver.displaySoln());
                        break;
                    case 'Q':   //Quit
                        break;
                    case '?':   //Display Menu
                        printMenu();
                        break;
                    default:
                        System.out.print("Unknown action\n");
                        break;
                }
            } else {
                System.out.print("Unknown action\n");
            }
        } while (input1 != 'Q' || line.length() != 1);
    }

    /**
     * The method printMenu displays the menu to a user
     **/
    public static void printMenu() {
        System.out.print("Choice\t\tAction\n" +
                "------\t\t------\n" +
                "E\t\tEnter Maze Information\n" +
                "Q\t\tQuit\n" +
                "?\t\tDisplay Help\n\n");
    }
}