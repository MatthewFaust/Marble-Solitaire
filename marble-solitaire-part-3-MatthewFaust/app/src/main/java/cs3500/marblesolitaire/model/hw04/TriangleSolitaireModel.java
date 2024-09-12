package cs3500.marblesolitaire.model.hw04;

import java.util.ArrayList;

public class TriangleSolitaireModel extends MarbleSollitaireModelAbstract {


    /**
     * Constructor for game with default paramaters
     */
    public TriangleSolitaireModel() {
        this(5, 0, 0);
    }


    /**
     * Constructor for initialization of the game with set armthickness
     *
     * @param dimensions the width of the marble part of the board
     */
    public TriangleSolitaireModel(int dimensions) {
        this(dimensions, 0, 0);
    }

    /**
     * Constructor for initialization of the board with specific empty slot
     *
     * @param sRow Inputted row for empty slot
     * @param sCol Inputted col for empty slot
     */
    public TriangleSolitaireModel(int sRow, int sCol) {
        this(5, sRow, sCol);
    }

    /**
     * Constructor with set parameters for the initialization of the board
     *
     * @param dimensions width of the marble part of the board
     * @param sRow       the row for the empty slot
     * @param sCol       the col for the empty slot
     */
    public TriangleSolitaireModel(int dimensions, int sRow, int sCol) {
        super(dimensions, sRow, sCol);
    }


    /**
     * cCalled in the constructor, this method is used to check if a
     * board is being correctly initialzed. If not it will throw an exception
     *
     * @param armThickness size of the side
     * @param sRow         location of the starting empty marble x axis
     * @param sCol         location of the starting empty marble y axis
     */
    @Override
    protected void exceptionChecker(int armThickness, int sRow, int sCol) {
        if (sCol > sRow) {
            throw new IllegalArgumentException("Invalid empty cell position (" + sRow + ", " + sCol + ")");
        } else if (armThickness <= 1) {
            throw new IllegalArgumentException("dimension is an invalid number");
        }
    }


    /**
     * Method to create the model for each type of board
     *
     * @param dimensions the thickness of the  bottom side for triangle
     * @param sRow       the location on the x axis of the empty starting spot
     * @param sCol       the location on the y axis of the empty starting spot
     */
    @Override
    public void modelMaker(int dimensions, int sRow, int sCol) {
        for (int i = 0; i < dimensions; i++) {
            ArrayList rows = new ArrayList();
            for (int j = 0; j < dimensions; j++) {
                if (i == sRow && j == sCol) {
                    rows.add(SlotState.Empty);
                } else if (i >= j) {
                    rows.add(SlotState.Marble);
                } else {
                    rows.add(SlotState.Invalid);
                }
            }
            board.add(rows);
        }
    }

    /**
     * Return the size of this board. The size is roughly the longest dimension of a board
     *
     * @return the size as an integer
     */
    @Override
    public int getBoardSize() {
        return armThickness;
    }


    /**
     * Basic design principle of ood. returns true if the model is a triangle otherwise returns false
     *
     * @return returns true
     */
    public boolean isTriangle() {
        return true;
    }
}
