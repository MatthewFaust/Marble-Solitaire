import org.junit.jupiter.api.Test;

import cs3500.marblesolitaire.model.hw04.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw04.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;
import java.io.StringReader;
import static org.junit.jupiter.api.Assertions.*;
import cs3500.marblesolitaire.Controller.MarbleSolitaireControllerImpl;

public class TriControllerTests {



    Readable invalidIntegerThenQuitTri = new StringReader("2 0 w 0\n0\nq");
    Readable makeGameEnd = new StringReader("2 2 0 0\n2 0 2 2\n0 0 2 0");

    Readable quit = new StringReader("q");
    Appendable ap = new StringBuilder();

    TriangleSolitaireModel triangle = new TriangleSolitaireModel();
    TriangleSolitaireModel triangle2 = new TriangleSolitaireModel(7);
    TriangleSolitaireModel triangle3 = new TriangleSolitaireModel(7, 2, 2);
    TriangleSolitaireModel triangle4 = new TriangleSolitaireModel(3);

    TriangleSolitaireTextView viewT = new TriangleSolitaireTextView(triangle, ap);
    TriangleSolitaireTextView viewT2 = new TriangleSolitaireTextView(triangle2, ap);
    TriangleSolitaireTextView viewT3 = new TriangleSolitaireTextView(triangle3, ap);
    TriangleSolitaireTextView viewT4 = new TriangleSolitaireTextView(triangle4, ap);


    /**
     * test to see if the game runs and if quit works
     */
    @Test
    public void testTrianglePlayGame() {
        MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(triangle, viewT, invalidIntegerThenQuitTri);
        controller.playGame();

        String expected =
                "Current score: 14\n" +
                        "    _\n" +
                        "   O O\n" +
                        "  O O O\n" +
                        " O O O O\n" +
                        "O O O O O" + "Current score: 13\n" +
                        "    O\n" +
                        "   _ O\n" +
                        "  _ O O\n" +
                        " O O O O\n" +
                        "O O O O O" + "Game quit!\n" + "State of game when quit:\n"
                        + "    O\n" +
                        "   _ O\n" +
                        "  _ O O\n" +
                        " O O O O\n" +
                        "O O O O O" +"Score: 13";


        assertEquals(expected, ap.toString());


    }

    /**
     * test for a triangle that has a dimension inputted
     */
    @Test
    public void testBigTriangle() {
        MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(triangle2, viewT2, quit);
        controller.playGame();

        String expected =
                "Current score: 27\n" +
                        "      _\n" +
                        "     O O\n" +
                        "    O O O\n" +
                        "   O O O O\n" +
                        "  O O O O O\n" +
                        " O O O O O O\n" +
                        "O O O O O O O" + "Game quit!\n" + "State of game when quit:\n"
                +"      _\n" +
                        "     O O\n" +
                        "    O O O\n" +
                        "   O O O O\n" +
                        "  O O O O O\n" +
                        " O O O O O O\n" +
                        "O O O O O O OScore: 27";


        assertEquals(expected, ap.toString());


    }

    /**
     * test for a triangle that has a dimension and empty cell inputted
     */
    @Test
    public void testTriangleMovedEmpty() {
        MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(triangle3, viewT3, quit);
        controller.playGame();

        String expected =
                "Current score: 27\n" +
                        "      O\n" +
                        "     O O\n" +
                        "    O O _\n" +
                        "   O O O O\n" +
                        "  O O O O O\n" +
                        " O O O O O O\n" +
                        "O O O O O O O" + "Game quit!\n" + "State of game when quit:\n"
                        +"      O\n" +
                        "     O O\n" +
                        "    O O _\n" +
                        "   O O O O\n" +
                        "  O O O O O\n" +
                        " O O O O O O\n" +
                        "O O O O O O OScore: 27";


        assertEquals(expected, ap.toString());
    }

    /**
     * Test for a triangle that beats the game
     * the game should end with the inputs from makeGameEnd
     */
    @Test
    public void testTriangleGameEnd() {
        MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(triangle4, viewT4, makeGameEnd);
        controller.playGame();

        String expected =
                "Current score: 5\n" +
                        "  _\n" +
                        " O O\n" +
                        "O O OCurrent score: 4\n" +
                        "  O\n" +
                        " O _\n" +
                        "O O _" + "Current score: 3\n" +
                        "  O\n" +
                        " O _\n" +
                        "_ _ O" + "Current score: 2\n" +
                        "  _\n" +
                        " _ _\n" +
                        "O _ OGame Over! Score: 2" +
                        "  _\n" +
                        " _ _\n" +
                        "O _ O" ;


        assertEquals(expected, ap.toString());
    }




}
