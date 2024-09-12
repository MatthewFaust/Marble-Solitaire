package cs3500.marblesolitaire.model.hw04;

import java.util.ArrayList;

/**
 * Class for the initialization of the game
 */
public class EnglishSolitaireModel extends MarbleSollitaireModelAbstract {


  /**
   * Constructor for game with default paramaters
   */
  public EnglishSolitaireModel(){
    super();
  }




  /**
   * Constructor for initialization of the board with specific empty slot
   * @param sRow Inputted row for empty slot
   * @param sCol Inputted col for empty slot
   */
  public EnglishSolitaireModel(int sRow, int sCol) {
    super(sRow, sCol);
  }

  /**
   * Constructor for initialization of the game with set armthickness
   * @param armThickness the width of the marble part of the board
   */
  public EnglishSolitaireModel(int armThickness) {
    super(armThickness);
  }

  /**
   * Constructor with set parameters for the initialization of the board
   * @param armThickness width of the marble part of the board
   * @param sRow the row for the empty slot
   * @param sCol the col for the empty slot
   */
  public EnglishSolitaireModel(int armThickness, int sRow, int sCol) {
    super(armThickness, sRow, sCol);
  }



  /**
   * cCalled in the constructor, this method is used to check if a
   * board is being correctly initialzed. If not it will throw an exception
   * @param armThickness size of the side
   * @param sRow location of the starting empty marble x axis
   * @param sCol location of the starting empty marble y axis
   */
@Override
  public void exceptionChecker(int armThickness, int sRow, int sCol) {

    if ((sRow < armThickness -1|| sRow > armThickness * 2 - 1 ) && (sCol <= armThickness || sCol > armThickness * 2 - 1)) {
      throw new IllegalArgumentException("Invalid empty cell position (" + sRow + ", " + sCol + ")");
    } else if  ((armThickness % 2 == 0) || (armThickness <= 1)) {
      throw new IllegalArgumentException("armThickness is an invalid number");
    }
  }

  /**
   * Method to create the model for each type of board
   * @param armThickness the thickness of the top row of marbles
   * @param sRow the location on the x axis of the empty starting spot
   * @param sCol the location on the y axis of the empty starting spot
   */
  public void modelMaker(int armThickness, int sRow, int sCol) {
    int sidelength = this.getBoardSize();
    for (int i = 0; i <= sidelength; i++) {
      ArrayList rows = new ArrayList();
      for (int j = 0; j <= sidelength; j++) {
        if (i == sRow && j == sCol) {
          rows.add(SlotState.Empty);
        } else if ((i < armThickness - 1 || i >= armThickness * 2 - 1) &&
                (j < armThickness - 1 || j >= armThickness * 2 - 1)) {
          rows.add(SlotState.Invalid);
        } else {
          rows.add(SlotState.Marble);
        }
      }
      this.board.add(rows);
    }
  }


  /**
   * Basic design principle of ood. returns true if the model is a triangle otherwise returns false
   * @return returns false
   */
  public boolean isTriangle() {
    return false;
  }
}


