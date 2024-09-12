package cs3500.marblesolitaire.model.hw04;

import java.util.ArrayList;

public abstract class MarbleSollitaireModelAbstract implements MarbleSolitaireModel {

    protected int armThickness;
    protected int sRow;
    protected int sCol;
    protected ArrayList<ArrayList<SlotState>> board = new ArrayList();
    protected int sidelength;

    /**
     * Constructor for game with default paramaters
     */
    public MarbleSollitaireModelAbstract() {
        this.armThickness = 3;
        sidelength = this.getBoardSize();
        this.sRow = (int) Math.ceil(sidelength / 2);
        this.sCol = (int) Math.ceil(sidelength / 2);
        this.board = new ArrayList<>();

        this.exceptionChecker(armThickness, sRow, sCol);
        this.modelMaker(armThickness, sRow, sCol);
    }

    /**
     * Constructor for initialization of the board with specific empty slot
     * @param sRow Inputted row for empty slot
     * @param sCol Inputted col for empty slot
     */
    public MarbleSollitaireModelAbstract(int sRow, int sCol) {
        this.armThickness = 3;
        this.sRow = sRow;
        this.sCol = sCol;
        this.board = new ArrayList<>();

        this.exceptionChecker(armThickness, sRow, sCol);
        this.modelMaker(armThickness, sRow, sCol);
    }

    /**
     * Constructor for initialization of the game with set armthickness
     * @param armThickness the width of the marble part of the board
     */
    public MarbleSollitaireModelAbstract(int armThickness) {
        this.armThickness = armThickness;
        sidelength = this.getBoardSize();
        this.sRow = (int) Math.ceil(sidelength / 2);
        this.sCol = (int) Math.ceil(sidelength / 2);
        this.board = new ArrayList<>();

        this.exceptionChecker(armThickness, sRow, sCol);
        this.modelMaker(armThickness, sRow, sCol);
    }

    /**
     * Constructor with set parameters for the initialization of the board
     * @param armThickness width of the marble part of the board
     * @param sRow the row for the empty slot
     * @param sCol the col for the empty slot
     */
    public MarbleSollitaireModelAbstract(int armThickness, int sRow, int sCol) {
        this.armThickness = armThickness;
        this.sRow = sRow;
        this.sCol = sCol;
        this.board = new ArrayList<>();

        this.exceptionChecker(armThickness, sRow, sCol);
        this.modelMaker(armThickness, sRow, sCol);
    }


    /**
     * Method to create the model for each type of board
     * @param armThickness the thickness of the top row of marbles for english and european
     *                     but its the bottom side for triangle
     * @param sRow the location on the x axis of the empty starting spot
     * @param sCol the location on the y axis of the empty starting spot
     */
    protected abstract void modelMaker(int armThickness, int sRow, int sCol);

    /**
     * cCalled in the constructor, this method is used to check if a
     * board is being correctly initialzed. If not it will throw an exception
     * @param armThickness size of the side
     * @param sRow location of the starting empty marble x axis
     * @param sCol location of the starting empty marble y axis
     */
    protected abstract void exceptionChecker(int armThickness, int sRow, int sCol);

    /**
     * Basic design principle of ood. returns true if the model is a triangle otherwise returns false
     * @return returns true if the model is a triangle otherwise returns false
     */
    protected abstract boolean isTriangle();



    /**
     * Move a single marble from a given position to another given position.
     * A move is valid only if the from and to positions are valid. Specific
     * implementations may place additional constraints on the validity of a move.
     *
     * @param fromRow the row number of the position to be moved from
     *                (starts at 0)
     * @param fromCol the column number of the position to be moved from
     *                (starts at 0)
     * @param toRow   the row number of the position to be moved to
     *                (starts at 0)
     * @param toCol   the column number of the position to be moved to
     *                (starts at 0)
     * @throws IllegalArgumentException if the move is not possible
     */

    @Override
    public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
        int x = fromCol - toCol;
        int y = fromRow - toRow;
        boolean diagnol = false;
        if(x == y && this.isTriangle()) {
            diagnol = true;
        }

        if (board.get(fromRow).get(fromCol) == SlotState.Marble) {
            if(board.get(toRow).get(toCol) == SlotState.Empty) {
                if((x == 0 || y == 0 || diagnol) && (Math.abs(x) == 2 || Math.abs(y) == 2)) {
                    if(board.get((fromRow + toRow) / 2).get((fromCol + toCol) / 2) == SlotState.Marble) {
                        board.get(fromRow).set(fromCol, SlotState.Empty);
                        board.get(toRow).set(toCol, SlotState.Marble);
                        board.get((fromRow + toRow) / 2).set((fromCol + toCol) / 2,SlotState.Empty);
                    } else {
                        throw new IllegalArgumentException("Invalid Move, marble must be in between from and to!");
                    }

                } else {
                    throw new IllegalArgumentException("Invalid Move, The marbles are not two spaces apart horizontally or vertically");
                }

            } else {
                throw new IllegalArgumentException("Invalid Move, The place you are trying to put your marble is not legal");
            }
        } else {
            throw new IllegalArgumentException("Invalid Move, from has no marble");
        }
    }
    /**
     * Determine and return if the game is over or not. A game is over if no
     * more moves can be made.
     *
     * @return true if the game is over, false otherwise
     */

    @Override
    public boolean isGameOver() {

        for (int i = 0; i < this.getBoardSize(); i++) {
            for (int j = 0; j < this.getBoardSize(); j++) {
                if (board.get(i).get(j) == SlotState.Marble) {
                    if (canMove(i, j, i - 2, j) ||
                            canMove(i, j, i + 2, j) ||
                            canMove(i, j, i, j - 2) ||
                            canMove(i, j, i, j + 2) ||
                            canMove(i , j, i - 2, j - 2) ||
                            canMove(i, j, i + 2, j + 2)) {
                        return false;
                    }
                }
            }
        }
        return true;

    }


    /**
     *helper for game over to determine if any remaining marbles have a valid move left.
     *
     @param fromRow the row number of the position to be moved from
      *                (starts at 0)
      * @param fromCol the column number of the position to be moved from
     *                (starts at 0)
     * @param toRow   the row number of the position to be moved to
     *                (starts at 0)
     * @param toCol   the column number of the position to be moved to
     *                (starts at 0)
     * @return a boolean if it is a valid move
     */
    public boolean canMove(int fromRow, int fromCol, int toRow, int toCol) {
        if (toRow < 0 || toCol < 0 || toRow >= board.size() || toCol >= board.size()) {
            return false;
        }
        int x = fromCol - toCol;
        int y = fromRow - toRow;
        boolean diagnol = false;
        if(x == y && this.isTriangle()) {
            diagnol = true;
        }

        if (board.get(fromRow).get(fromCol) == SlotState.Marble) {
            if(board.get(toRow).get(toCol) == SlotState.Empty) {
                if((x == 0 || y == 0 || diagnol) && (Math.abs(x) == 2 || Math.abs(y) == 2)) {
                    if(board.get((fromRow + toRow) / 2).get((fromCol + toCol) / 2) == SlotState.Marble) {
                        return true;
                    }
                }
            }
        }
        return false;

    }

    /**
     * Return the size of this board. The size is roughly the longest dimension of a board
     *
     * @return the size as an integer
     */
    @Override
    public int getBoardSize() {
        return armThickness * 3 - 2;
    }

    /**
     * Get the state of the slot at a given position on the board.
     *
     * @param row the row of the position sought, starting at 0
     * @param col the column of the position sought, starting at 0
     * @return the state of the slot at the given row and column
     * @throws IllegalArgumentException if the row or the column are beyond
     *         the dimensions of the board
     */
    @Override
    public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
        if ((row >= this.getBoardSize() || col >= this.getBoardSize()) || (row < 0 || col < 0)) {
            throw new IllegalArgumentException("Invalid row or column");
        }
        return (SlotState) board.get(row).get(col);
    }

    /**
     * Return the number of marbles currently on the board.
     *
     * @return the number of marbles currently on the board
     */

    @Override
    public int getScore() {
        int score = 0;
        for (int row = 0; row < this.getBoardSize(); row++) {
            for (int col = 0; col < this.getBoardSize(); col++) {
                if (board.get(row).get(col) == SlotState.Marble) {
                    score++;
                }
            }
        }
        return score;
    }

}
