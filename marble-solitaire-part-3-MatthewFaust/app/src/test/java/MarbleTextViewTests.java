import cs3500.marblesolitaire.model.hw04.*;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;
import org.junit.jupiter.api.Test;

import cs3500.marblesolitaire.view.MarbleSolitaireTextView;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class MarbleTextViewTests {
  EnglishSolitaireModel model0 = new EnglishSolitaireModel();
  MarbleSolitaireModel euroModel = new EuropeanSolitaireModel();

  /**
   * test constructor for the view
   * <p>
   * Throws Exceptions to give a null model
   */

  @Test
  public void testConstructor() {
    try {
      new MarbleSolitaireTextView(null);
      fail("Constructor should have thrown an exception with a null argument.");
    } catch (IllegalArgumentException e) {
      assertEquals("MarbleSolitaireModelState cannot be null!", e.getMessage());
    }
  }


  /**
   * test to string method with the default board
   */
  @Test
  public void testToStringTriangle() {
    TriangleSolitaireModel tModel = new TriangleSolitaireModel();
    TriangleSolitaireTextView view = new TriangleSolitaireTextView(tModel);


    String expected =
                    "    _\n" +
                    "   O O\n" +
                    "  O O O\n" +
                    " O O O O\n" +
                    "O O O O O";


    assertEquals(expected, view.toString());


  }

  /**
   * test to string method with a larger triangle with a mopved starting empty
   */
  @Test
  public void testToStringBigTriangle() {
    TriangleSolitaireModel tModel = new TriangleSolitaireModel(7, 2, 2);
    TriangleSolitaireTextView view = new TriangleSolitaireTextView(tModel);


    String expected =
                    "      O\n" +
                    "     O O\n" +
                    "    O O _\n" +
                    "   O O O O\n" +
                    "  O O O O O\n" +
                    " O O O O O O\n" +
                    "O O O O O O O";


    assertEquals(expected, view.toString());


  }

  /**
   * test to string method with a base euro model
   */
  @Test
  public void testToStringEuro() {
    EuropeanSolitaireModel eModel = new EuropeanSolitaireModel();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(eModel);

    String expected =
                    "    O O O\n" +
                    "  O O O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "  O O O O O\n" +
                    "    O O O";


    assertEquals(expected, view.toString());
  }

  /**
   * test to string method with a euro mode that has armThickness inputted
   */

  @Test
  public void testToStringBigEuro() {
    EuropeanSolitaireModel eModel = new EuropeanSolitaireModel(5, 3, 3);
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(eModel);

    String expected =         "        O O O O O\n" +
            "      O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "  O O _ O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "      O O O O O O O\n" +
            "        O O O O O";

    assertEquals(expected, view.toString());



  }

  /**
   * test to string with a mutated board
   */
  @Test
  public void testToStringEnglish() {


    // Additional moves to test game over scenario
    model0.move(1, 3, 3, 3);
    model0.move(2, 1, 2, 3);
    model0.move(2, 4, 2, 2);
    model0.move(2, 6, 2, 4);
    model0.move(4, 3, 2, 3);
    model0.move(2, 3, 2, 1);
    model0.move(2, 0, 2, 2);
    model0.move(4, 0, 2, 0);
    model0.move(4, 1, 2, 1);

    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model0);


    String expected =
                    "    O O O\n" +
                    "    O _ O\n" +
                    "O O O _ O _ _\n" +
                    "_ _ O _ O O O\n" +
                    "_ _ O _ O O O\n" +
                    "    O O O\n" +
                    "    O O O";

    assertEquals(expected, view.toString());

  }


  /**
   * test to string on an english model with inputted armThickness and empty cell
   */
  @Test
  public void testToStringBigEnglishMovedEmpty() {
    EnglishSolitaireModel model1 = new EnglishSolitaireModel(5, 6, 2);
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model1);


    String expected =
            "        O O O O O\n" +
                    "        O O O O O\n" +
                    "        O O O O O\n" +
                    "        O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "O O _ O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "        O O O O O\n" +
                    "        O O O O O\n" +
                    "        O O O O O\n" +
                    "        O O O O O";

    assertEquals(expected, view.toString());

  }



}



