import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw04.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;
import org.junit.jupiter.api.Test;
import java.io.StringReader;
import cs3500.marblesolitaire.Controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;

import static org.junit.jupiter.api.Assertions.*;


public class EurControllerTests {

    Readable invalidIntegerThenQuit = new StringReader("4 6 w 6\n6\nq");
    Readable tooShortThenQuit = new StringReader("1 3 3\n1 3 3 3\nq");
    Readable tooLongThenQuit = new StringReader("1 3 3 3 3\n4 6 6 6\nq");


    Readable quit = new StringReader("q");
    Appendable ap = new StringBuilder();


    EuropeanSolitaireModel model = new EuropeanSolitaireModel();
    EuropeanSolitaireModel bigModel = new EuropeanSolitaireModel(5);
    EuropeanSolitaireModel bigModel2 = new EuropeanSolitaireModel(5, 6, 5);
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model, ap);
    MarbleSolitaireTextView view2 = new MarbleSolitaireTextView(bigModel, ap);
    MarbleSolitaireTextView view3 = new MarbleSolitaireTextView(bigModel2, ap);


    /**
     * test for the controller constructor
     */
    @Test
    public void testEurConstructor() {
        try {

            new MarbleSolitaireControllerImpl(null, view, tooLongThenQuit);
            fail("Constructor should have thrown an exception with a null argument.");
        } catch (IllegalArgumentException e) {
            assertEquals("The model view and read cannot be null.", e.getMessage());
        }
    }

    /**
     * test for a euro model that has an input that is too short then quits
     */
    @Test
    public void testTooShortInputEuro() {
        MarbleSolitaireControllerImpl controller1 = new MarbleSolitaireControllerImpl(model, view, tooShortThenQuit);
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
     * test for a euro model that has an armThickness and invalid integer
     */
    @Test
    public void testTooShortInput() {
        MarbleSolitaireControllerImpl controller1 = new MarbleSolitaireControllerImpl(bigModel, view2, invalidIntegerThenQuit);
        controller1.playGame();
        String Output =
                "Current score: 128\n" +
                        "        O O O O O\n" +
                        "      O O O O O O O\n" +
                        "    O O O O O O O O O\n" +
                        "  O O O O O O O O O O O\n" +
                        "O O O O O O O O O O O O O\n" +
                        "O O O O O O O O O O O O O\n" +
                        "O O O O O O _ O O O O O O\n" +
                        "O O O O O O O O O O O O O\n" +
                        "O O O O O O O O O O O O O\n" +
                        "  O O O O O O O O O O O\n" +
                        "    O O O O O O O O O\n" +
                        "      O O O O O O O\n" +
                        "        O O O O O" +

                        "Current score: 127\n" +
                        "        O O O O O\n" +
                        "      O O O O O O O\n" +
                        "    O O O O O O O O O\n" +
                        "  O O O O O O O O O O O\n" +
                        "O O O O O O _ O O O O O O\n" +
                        "O O O O O O _ O O O O O O\n" +
                        "O O O O O O O O O O O O O\n" +
                        "O O O O O O O O O O O O O\n" +
                        "O O O O O O O O O O O O O\n" +
                        "  O O O O O O O O O O O\n" +
                        "    O O O O O O O O O\n" +
                        "      O O O O O O O\n" +
                        "        O O O O O" +

                        "Game quit!\n" + "State of game when quit:\n" +
                        "        O O O O O\n" +
                        "      O O O O O O O\n" +
                        "    O O O O O O O O O\n" +
                        "  O O O O O O O O O O O\n" +
                        "O O O O O O _ O O O O O O\n" +
                        "O O O O O O _ O O O O O O\n" +
                        "O O O O O O O O O O O O O\n" +
                        "O O O O O O O O O O O O O\n" +
                        "O O O O O O O O O O O O O\n" +
                        "  O O O O O O O O O O O\n" +
                        "    O O O O O O O O O\n" +
                        "      O O O O O O O\n" +
                        "        O O O O O" +
                        "Score: 127";

        assertEquals(Output, ap.toString());
    }

    /**
     * test for a euro model that has an armThickness and empty cell location inputted
     */
    @Test
    public void testCutOutHole() {
        MarbleSolitaireControllerImpl controller1 = new MarbleSolitaireControllerImpl(bigModel2, view3, quit);
        controller1.playGame();
        String Output =
                "Current score: 128\n" +
                        "        O O O O O\n" +
                        "      O O O O O O O\n" +
                        "    O O O O O O O O O\n" +
                        "  O O O O O O O O O O O\n" +
                        "O O O O O O O O O O O O O\n" +
                        "O O O O O O O O O O O O O\n" +
                        "O O O O O _ O O O O O O O\n" +
                        "O O O O O O O O O O O O O\n" +
                        "O O O O O O O O O O O O O\n" +
                        "  O O O O O O O O O O O\n" +
                        "    O O O O O O O O O\n" +
                        "      O O O O O O O\n" +
                        "        O O O O O" +

                        "Game quit!\n" + "State of game when quit:\n" +
                        "        O O O O O\n" +
                        "      O O O O O O O\n" +
                        "    O O O O O O O O O\n" +
                        "  O O O O O O O O O O O\n" +
                        "O O O O O O O O O O O O O\n" +
                        "O O O O O O O O O O O O O\n" +
                        "O O O O O _ O O O O O O O\n" +
                        "O O O O O O O O O O O O O\n" +
                        "O O O O O O O O O O O O O\n" +
                        "  O O O O O O O O O O O\n" +
                        "    O O O O O O O O O\n" +
                        "      O O O O O O O\n" +
                        "        O O O O O" +
                        "Score: 128";

        assertEquals(Output, ap.toString());
    }
}
