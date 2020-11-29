/*************************************************************************
 * Name: Nicholas Mel
 * Description: The position class pairs a row number and a column number,
 * and also keeps track which direction it is going next (face value).
 *************************************************************************/

public class Position {
    private final int row;
    private final int col;
    private int face; //which direction it is going next

    //Initializes the position object using three parameters
    public Position(int row, int col, int face) {
        this.row = row;
        this.col = col;
        this.face = face;
    }

    //Accessor method for the row number
    public int getRow() {
        return row;
    }

    //Accessor method for the column number
    public int getColumn() {
        return col;
    }

    //Mutator method for the face (direction)
    public void setFace(int face) {
        this.face = face;
    }

    //Accessor method for the face
    public int getFace() {
        return face;
    }

    //toString() returns a string containing
    //the current row, col, and face
    public String toString() {
        String result = "From row " + row + ", col " + col + ", go to ";
        switch (face) {
            case 0:
                result += "Down";
                break;
            case 1:
                result += "DownLeft";
                break;
            case 2:
                result += "Left";
                break;
            case 3:
                result += "UpLeft";
                break;
            case 4:
                result += "Up";
                break;
            case 5:
                result += "UpRight";
                break;
            case 6:
                result += "Right";
                break;
            case 7:
                result += "DownRight";
                break;
            default:
                result += "Error incorrect direction";
                break;
        } //end of switch

        result += "\n";
        return result;

    } //end of toString method

} //end of Position class