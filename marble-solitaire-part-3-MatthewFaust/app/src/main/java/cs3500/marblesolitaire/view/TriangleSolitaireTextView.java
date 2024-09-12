package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw04.MarbleSolitaireModelState;

import java.io.IOException;

public class TriangleSolitaireTextView implements MarbleSolitaireView{
    private MarbleSolitaireModelState BoardState;
    private Appendable ap;

    /**
     * Constructor for the view
     *
     * @param BoardState takes in a game model
     */
    public TriangleSolitaireTextView(MarbleSolitaireModelState BoardState) {
        if (BoardState == null) {
            throw new IllegalArgumentException("MarbleSolitaireModelState cannot be null!");
        }
        this.BoardState = BoardState;
        this.ap = System.out;

    }

    /**
     * Constructor for the view
     *
     * @param BoardState takes in a game model
     * @param ap takes in an appendable
     */

    public TriangleSolitaireTextView(MarbleSolitaireModelState BoardState, Appendable ap) {
        if (BoardState == null || ap == null) {
            throw new IllegalArgumentException("Boardstate and ap cannot be null!");
        }
        this.BoardState = BoardState;
        this.ap = ap;
    }

    /**
     * Return a string that represents the current state of the board. The
     * string should have one line per row of the game board. Each slot on the
     * game board is a single character (O, _ or space for a marble, empty and
     * invalid position respectively). Slots in a row should be separated by a
     * space. Each row has no space before the first slot and after the last slot.
     *
     * @return the game state as a string
     */

    public String toString() {
        StringBuilder board = new StringBuilder();
        for (int i = 0; i < BoardState.getBoardSize(); i++) {
            for (int j = 0; j < BoardState.getBoardSize(); j++) {
                for(int k = 0; k < (BoardState.getBoardSize() - i) -1; k++) {
                    if(j == 0) {
                        board.append(" ");
                    }
                }
                if (BoardState.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Marble) {
                    board.append("O");
                } else if (BoardState.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Empty) {
                    board.append("_");
                }
                if (j < (BoardState.getBoardSize()) - (BoardState.getBoardSize() -i)) {
                    board.append(" ");
                }
            }
            if (i < BoardState.getBoardSize() - 1) {
                board.append("\n");
            }
        }
        return board.toString();
    }

    /**
     * Transmits the state of the marble solitaire board to a specified destination
     * @throws IOException for any transmission fails
     */
    @Override
    public void renderBoard() throws IOException {
        try {
            ap.append(this.toString());
        } catch (IOException e) {
            throw new IOException("The transmission has failed", e);
        }
    }

    /**
     *this method can be used to show an arbitrary message, allowing
     * this view to show messages determined by whoever uses it.
     * @param message the message to be transmitted
     * @throws IOException for any transmission fails
     */
    @Override
    public void renderMessage(String message) throws IOException {
        try {
            ap.append(message);
        } catch (IOException e) {
            throw new IOException("The transmission has failed", e);
        }
    }
}
