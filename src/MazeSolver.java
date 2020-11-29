/*****************************************************************************
    Name: Nicholas Mel
    Description: The MazeSolver contains two 2-dimetional character arrays,
    one for the original and another to display the It also contains a stack
    to keep track of a solution path found. Its findSolution method solves the
    maze problem and put the solution in the stack.
*******************************************************************************/

import java.util.Stack;

public class MazeSolver {
    private char[][] originalMaze;
    private char[][] maze;
    private final int mazeSize;
    private final Stack<Position> stackSoln;

    //Constructor to initialize the mazeSize,
    //initializes two 2-dimensional character arrays.
    public MazeSolver(String[] mazeInfo) {
        mazeSize = 10;
        setupMaze(mazeInfo);
        stackSoln = new Stack<Position>();
    }

    //the setupMaze method initializes
    //two character arrays, using the input array of strings.
    public void setupMaze(String[] mazeInfo) {
        maze = new char[mazeSize][mazeSize];
        originalMaze = new char[mazeSize][mazeSize];
        for (int i = 0; i < mazeSize; i++) {
            for (int j = 0; j < mazeSize; j++) {
                originalMaze[i][j] = mazeInfo[i].charAt(j);
                maze[i][j] = originalMaze[i][j];
            }
        }
    }

    //The displayPath methods returns a string describing
    //how to go from the starting position to the goal position
    public String displayPath() {
        String result = "";

        while (!stackSoln.isEmpty()) {
            result = stackSoln.pop() + result;
        }
        return "\nSolution Path:\n" + result + "\n\n";
    }

    //the displaySoln method returns a string containing
    //a solution of the maze
    public String displaySoln() {
        String result = "\nThe maze content:\n";

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                result += maze[i][j];
            }
            result += "\n";
        }

        return result + "\n";
    }

    //The findSolution will return true if a solution is found,
    //false otherwise. Please see the pseudo-code of the assignment 11 statement
    public boolean findSolution() {
        boolean finish = false; //finish should become true when a solution is found or it is determined that there is no solution
        boolean success = false;  //success should become true when a solution is found

        //The following can be used to compute each of 8 directions
        //one can move from their current position (row,column).
        int[][] offset = {
                {1, 0},   //Down
                {1, -1},  //DownLeft
                {0, -1},  //Left
                {-1, -1}, //UpLeft
                {-1, 0},  //Up
                {-1, 1},  //UpRight
                {0, 1},   //Right
                {1, 1}    //DownRight
        };

        //Push information onto the stack indicating the first choice
        //is the position of row 0 and column 9. The last value is face, put -1 as default
        Position nextPosition = new Position(0, 9, -1);
        stackSoln.push(nextPosition);

        while (finish == false && stackSoln.isEmpty() == false) {
            //check the last position
            int currentRow = stackSoln.peek().getRow();
            int currentCol = stackSoln.peek().getColumn();

            System.out.println("Trying the position of row "
                    + currentRow
                    + " and column "
                    + currentCol);

            /************************************************************************
             Check the most recent position from the stack, and check which of eight adjacent positions to move from the recent position,
             in the order of 'down', 'down left', 'left', 'up left', 'up', 'up right', 'right', and 'down right'.
             This part requires another nested loop to repeat at most 8 times to check eight adjacent positions.
             If such adjacent position is not outside of the maze range,
             row and column being greater than or equals to 0 or less than or equals to 9, and being able to move into
             (it needs to contain 'x' or '<' the goal position),
             then we found a position to move to.
             As soon as we find a position to move to, save its direction to 'face' variable and get out of the loop
             ('face' can contain 0,1,2,3,4,5,6, and 7 representing the direction of
             'down', 'down left', 'left', 'up left', 'up', 'up right', 'right', and 'down right' respectively).


             row and column of adjacent positions can be written as:

             currentRow+offset[k][0]
             and
             currentCol+offset[k][1]

             where k ranges from 0 to 7.
             *************************************************************************/
            int k = 0;
            boolean found = false;

            for (k = 0; k < 8 && finish; k++) {
                currentRow += offset[k][0];
                currentCol += offset[k][1];

                if (currentRow < 9 && currentCol < 9 && currentRow > 0 && currentCol > 0) {
                    found = true;
                    break;
                }
            }
            /************************************************************************
             If the found adjacent position to move to is the goal position (contains '<'),
             then we found a solution path, and set 'success' to true and 'finish' to true.
             Also set the face of the current position to 'face' value obtained by the above loop.
             ************************************************************************/
            if (maze[currentRow][currentCol] == '<') {
                stackSoln.peek().setFace(k);
                success = true;
                finish = true;
            }
            /************************************************************************
             If the found adjacent position to move to is not the goal position,
             then push the object of such adjacent position onto the stack, and set the adjacent position to 'x'.
             Also set the face of the current position to 'face' value obtained by the above loop.
             ************************************************************************/
            else if (maze[currentRow][currentCol] == '.') {
                nextPosition = new Position(currentRow, currentCol, -1);
                stackSoln.peek().setFace(k);
                stackSoln.push(nextPosition);
                maze[currentRow][currentCol] = 'x';
            }
            /************************************************************************
             If we cannot find any adjacent position to move to, then set the current position to 'O', if it was 'x'.
             Then pop the solution stack, which means not including this position in the solution path.
             ************************************************************************/
            else {
                if (maze[currentRow][currentCol] == 'x') {
                    maze[currentRow][currentCol] = '0';
                }
                stackSoln.pop();
            }
            /************************************************************************
             If the stack is empty at this time, then there is no other place to back track, thus the maze does not have a solution.
             Set 'success' to false and 'finish' to true.
             ************************************************************************/
            if (stackSoln.isEmpty() == true) {
                success = false;
                finish = true;
            }
        } //end of while loop
        return success;
    }
}
