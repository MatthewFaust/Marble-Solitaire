import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw04.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;
import org.junit.jupiter.api.Test;

import java.io.StringReader;

import cs3500.marblesolitaire.Controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw04.EnglishSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;

import static org.junit.jupiter.api.Assertions.*;

/**
 * tests the methods in MarbleSolitaireControllerImpl with various games and inputs
 */
public class ControllerTests {

  Readable invalidIntegerThenQuit = new StringReader("1 3 w 3\n3\nq");
  Readable negativeMoveThenQuit = new StringReader("1 3 -3 3\n3\nq");
  Readable tooShortThenQuit = new StringReader("1 3 3\n1 3 3 3\nq");
  Readable tooLongThenQuit = new StringReader("1 3 3 3 3\n4 6 6 6\nq");
  Readable tooShortInvalidIntegerNegativeIntegerThenQuit = new StringReader("1 3 3\n1 3 3 w\n-4\n3\nq");

  Readable invalidIntegerThenQuitTri = new StringReader("2 0 w 0\n0\nq");



  Readable quit = new StringReader("q");
  Appendable ap = new StringBuilder();




  EnglishSolitaireModel model = new EnglishSolitaireModel();
  EuropeanSolitaireModel Euromodel = new EuropeanSolitaireModel();
  EuropeanSolitaireModel bigEuromodel = new EuropeanSolitaireModel();
  TriangleSolitaireModel triangle = new TriangleSolitaireModel();
  EnglishSolitaireModel model2 = new EnglishSolitaireModel(5);
  MarbleSolitaireTextView view = new MarbleSolitaireTextView(model, ap);
  MarbleSolitaireTextView view2 = new MarbleSolitaireTextView(model2, ap);
  MarbleSolitaireTextView Euroview = new MarbleSolitaireTextView(Euromodel, ap);
  MarbleSolitaireTextView BigEuroview = new MarbleSolitaireTextView(bigEuromodel, ap);
  TriangleSolitaireTextView viewT = new TriangleSolitaireTextView(triangle, ap);


  /**
   * testing the methodRenderHelper with a simple message
   */
  @Test
  public void testRenderHelper() {
    MarbleSolitaireControllerImpl controller1 = new MarbleSolitaireControllerImpl(model, view, quit);
    controller1.renderHelper("Score 400");
    String Output =
            "Score 400" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O" ;

    assertEquals(Output, ap.toString());

  }

  /**
   * Test the controller constructer for any null fields
   */
  @Test
  public void testConstructor() {
    try {

      new MarbleSolitaireControllerImpl(null, view, tooLongThenQuit);
      fail("Constructor should have thrown an exception with a null argument.");
    } catch (IllegalArgumentException e) {
      assertEquals("The model view and read cannot be null.", e.getMessage());
    }

  }

  /**
   * Test the method quit game with various inputs
   */
  @Test
  public void testQuiGame() {
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, quit);
    assertTrue(controller.quitGame("q"));
    assertFalse(controller.quitGame("1"));
    assertFalse(controller.quitGame("w"));


  }



  /**
   * Test the method playGame, with the user quiting on the first move
   */
  @Test
  public void testCanQuitGame() {
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, quit);
    controller.playGame();

    String Output =
            "Current score: 32\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O" +
                     "Game quit!\n" + "State of game when quit:\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O" + "Score: 32";

    assertEquals(Output, ap.toString());
  }

  /**
   * test the method playGame with the user quiting after inputing an invalid mpve set
   */
  @Test
  public void testInvalidInteger() {
    MarbleSolitaireControllerImpl controller1 = new MarbleSolitaireControllerImpl(model, view, invalidIntegerThenQuit);
    controller1.playGame();
    String Output =
            "Current score: 32\n" +
            "    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O" +
                    "Current score: 31\n" +
                    "    O O O\n" +
                    "    O _ O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O" +

                   "Game quit!\n" + "State of game when quit:\n" +
                    "    O O O\n" +
                    "    O _ O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O" +
                    "Score: 31";

    assertEquals(Output, ap.toString());
  }

  /**
   *  test the method playGame with the user quitting after inputting a negative number
   */
  @Test
  public void testNegativeInteger() {
    MarbleSolitaireControllerImpl controller1 = new MarbleSolitaireControllerImpl(model, view, negativeMoveThenQuit);
    controller1.playGame();
    String Output =
            "Current score: 32\n" +
            "    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O" +
                    "Current score: 31\n" +
                    "    O O O\n" +
                    "    O _ O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O" +

                    "Game quit!\n" + "State of game when quit:\n" +
                    "    O O O\n" +
                    "    O _ O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O" +
                    "Score: 31";

    assertEquals(Output, ap.toString());
  }

  /**
   * test the method playGame with the user inputting to short of an option with a different board
   */
  @Test
  public void testTooShortInput() {
    MarbleSolitaireControllerImpl controller1 = new MarbleSolitaireControllerImpl(model, view, tooShortThenQuit);
    controller1.playGame();
    String Output =
            "Current score: 32\n" +
            "    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O" +
                    "Current score: 31\n" +
                    "    O O O\n" +
                    "    O _ O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O" +

                    "Game quit!\n" + "State of game when quit:\n" +
                    "    O O O\n" +
                    "    O _ O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O" +
                    "Score: 31";

    assertEquals(Output, ap.toString());
  }

  @Test
  public void testTooShortInputEuro() {
    MarbleSolitaireControllerImpl controller1 = new MarbleSolitaireControllerImpl(Euromodel, Euroview, tooShortThenQuit);
    controller1.playGame();
    String Output =
            "Current score: 36\n" +
                    "    O O O\n" +
                    "  O O O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "  O O O O O\n" +
                    "    O O O" +
                    "Current score: 35\n" +
                    "    O O O\n" +
                    "  O O _ O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "O O O O O O O\n" +
                    "  O O O O O\n" +
                    "    O O O" +

                    "Game quit!\n" + "State of game when quit:\n" +
                    "    O O O\n" +
                    "  O O _ O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "O O O O O O O\n" +
                    "  O O O O O\n" +
                    "    O O O" +
                    "Score: 35";

    assertEquals(Output, ap.toString());
  }

  /**
   * test the method playGame with the user inputting too long of an option, with a bigger board
   */
  @Test
  public void testTooLongInput() {
    MarbleSolitaireControllerImpl controller1 = new MarbleSolitaireControllerImpl(model2, view2, tooLongThenQuit);
    controller1.playGame();
    String Output =
            "Current score: 104\n" +
                    "        O O O O O\n" +
                    "        O O O O O\n" +
                    "        O O O O O\n" +
                    "        O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "O O O O O O _ O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "        O O O O O\n" +
                    "        O O O O O\n" +
                    "        O O O O O\n" +
                    "        O O O O O" +
                    "Current score: 103\n" +
                    "        O O O O O\n" +
                    "        O O O O O\n" +
                    "        O O O O O\n" +
                    "        O O O O O\n" +
                    "O O O O O O _ O O O O O O\n" +
                    "O O O O O O _ O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "        O O O O O\n" +
                    "        O O O O O\n" +
                    "        O O O O O\n" +
                    "        O O O O O" +
                    "Game quit!\n" + "State of game when quit:\n" +
                    "        O O O O O\n" +
                    "        O O O O O\n" +
                    "        O O O O O\n" +
                    "        O O O O O\n" +
                    "O O O O O O _ O O O O O O\n" +
                    "O O O O O O _ O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "        O O O O O\n" +
                    "        O O O O O\n" +
                    "        O O O O O\n" +
                    "        O O O O O" +"Score: 103";

    assertEquals(Output, ap.toString());
  }

  /**
   * test the method playGame with the user inputting every bad input then quiting
   */
  @Test
  public void testManyBadInputs() {
    MarbleSolitaireControllerImpl controller1 = new MarbleSolitaireControllerImpl(model, view, tooShortInvalidIntegerNegativeIntegerThenQuit);
    controller1.playGame();
    String Output =
            "Current score: 32\n" +

                    "    O O O\n" +
                    "    O O O\n" +
                   "O O O O O O O\n" +
                   "O O O _ O O O\n" +
                   "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O" +
                    "Current score: 31\n" +

                    "    O O O\n" +
                    "    O _ O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O" +
            "Game quit!\n" + "State of game when quit:\n" +
                    "    O O O\n" +
                    "    O _ O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O" +
            "Score: 31";

    assertEquals(Output, ap.toString());

  }


}


