import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw04.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import org.junit.jupiter.api.Test;


import cs3500.marblesolitaire.model.hw04.EnglishSolitaireModel;

import static org.junit.jupiter.api.Assertions.*;

public class ModelTests {


    EnglishSolitaireModel model0 = new EnglishSolitaireModel();
    TriangleSolitaireModel tmodel = new TriangleSolitaireModel();
    EnglishSolitaireModel model1 = new EnglishSolitaireModel();
    EnglishSolitaireModel model2 = new EnglishSolitaireModel(3, 3);
    EnglishSolitaireModel model3 = new EnglishSolitaireModel(5);
    EnglishSolitaireModel model4 = new EnglishSolitaireModel(5, 6, 6);



    /**
     * test for invalid parameters in English constructor
     */
    @Test
    public void testInvalidConstructorsEnglish() {
        assertThrows(IllegalArgumentException.class, () -> new EnglishSolitaireModel(1, 1));
        assertThrows(IllegalArgumentException.class, () -> new EnglishSolitaireModel(2));
        assertThrows(IllegalArgumentException.class, () -> new EnglishSolitaireModel(0, 4, 4));
    }

    /**
     * test for invalid parameters in european constructor
     */
    @Test
    public void testInvalidConstructorsEuropean() {
        assertThrows(IllegalArgumentException.class, () -> new EuropeanSolitaireModel(0, 1));
        assertThrows(IllegalArgumentException.class, () -> new EuropeanSolitaireModel(4));
        assertThrows(IllegalArgumentException.class, () -> new EuropeanSolitaireModel(0, 4, 4));
    }

    /**
     * test for invalid parameters in Triangle constructor
     */
    @Test
    public void testInvalidConstructorsTriangle() {
        assertThrows(IllegalArgumentException.class, () -> new TriangleSolitaireModel(7, 0, 2));
        assertThrows(IllegalArgumentException.class, () -> new TriangleSolitaireModel(0));
        assertThrows(IllegalArgumentException.class, () -> new TriangleSolitaireModel(0, 3, 4));
    }

    /**
     * test for default constructor
     */

    @Test
    public void testDefaultConstructor() {
        assertEquals(MarbleSolitaireModelState.SlotState.Empty, model1.getSlotAt(3, 3));
        assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(0, 3));
        assertEquals(MarbleSolitaireModelState.SlotState.Invalid, model1.getSlotAt(0, 0));
    }

    /**
     * test for contructor with empty position
     */
    @Test
    public void testConstructorWithEmptyPosition() {
        assertEquals(MarbleSolitaireModelState.SlotState.Empty, model2.getSlotAt(3, 3));
        assertEquals(MarbleSolitaireModelState.SlotState.Marble, model2.getSlotAt(0, 3));
        assertEquals(MarbleSolitaireModelState.SlotState.Invalid, model2.getSlotAt(0, 0));
    }

    /**
     * test for constructor with arm thickness
     */
    @Test
    public void testConstructorWithArmThickness() {
        assertEquals(MarbleSolitaireModelState.SlotState.Empty, model3.getSlotAt(6, 6));
        assertEquals(MarbleSolitaireModelState.SlotState.Marble, model3.getSlotAt(0, 7));
        assertEquals(MarbleSolitaireModelState.SlotState.Invalid, model3.getSlotAt(0, 0));
    }

    /**
     * test for constructor with all paramaters
     */
    @Test
    public void testConstructorWithAllParams() {
        assertEquals(MarbleSolitaireModelState.SlotState.Empty, model4.getSlotAt(6, 6));
        assertEquals(MarbleSolitaireModelState.SlotState.Marble, model4.getSlotAt(0, 7));
        assertEquals(MarbleSolitaireModelState.SlotState.Invalid, model4.getSlotAt(0, 0));
    }

    /**
     * test for move
     */
    @Test
    public void testMove() {
        tmodel.move(2, 2, 0, 0);
        assertEquals(MarbleSolitaireModelState.SlotState.Empty, tmodel.getSlotAt(1, 1));
        assertEquals(MarbleSolitaireModelState.SlotState.Empty, tmodel.getSlotAt(2, 2));
        assertEquals(MarbleSolitaireModelState.SlotState.Marble, tmodel.getSlotAt(0, 0));
    }

    /**
     * test for invalid moves
     */
    @Test
    public void testInvalidMoves() {
        assertThrows(IllegalArgumentException.class, () -> model2.move(0, 0, 3, 3));
        assertThrows(IllegalArgumentException.class, () -> model2.move(2, 0, 3, 1));
        assertThrows(IllegalArgumentException.class, () -> model2.move(3, 2, 3, 3));
        assertThrows(IllegalArgumentException.class, () -> model2.move(2, 0, 2, 2));
    }

    /**
     * test for is game over
     */
    @Test
    public void testIsGameOver() {
        assertFalse(model0.isGameOver());
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
        model0.move(2, 1, 2, 3);
        model0.move(2, 4, 2, 2);
        model0.move(0, 4, 2, 4);
        model0.move(3, 4, 1, 4);
        model0.move(4, 5, 4, 3);
        model0.move(5, 3, 3, 3);
        model0.move(3, 2, 3, 4);
        model0.move(3, 5, 3, 3);
        model0.move(0, 2, 0, 4);
        model0.move(0, 4, 2, 4);
        model0.move(4, 6, 2, 6);
        model0.move(6, 4, 4, 4);
        model0.move(5, 2, 3, 2);
        model0.move(3, 2, 3, 4);
        model0.move(1, 2, 3, 2);
        model0.move(6, 2, 6, 4);
        model0.move(3, 4, 1, 4);

        assertTrue(model0.isGameOver());
    }

    /**
     * test for get board size
     */
    @Test
    public void testGetBoardSize() {
        assertEquals(7, model1.getBoardSize());
        assertEquals(7, model2.getBoardSize());
        assertEquals(13, model3.getBoardSize());
        assertEquals(13, model4.getBoardSize());

    }

    /**
     * test for get slot at
     */
    @Test
    public void testGetSlotAt() {
        assertEquals(MarbleSolitaireModelState.SlotState.Empty, model1.getSlotAt(3, 3));
        assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(0, 3));
        assertEquals(MarbleSolitaireModelState.SlotState.Invalid, model1.getSlotAt(0, 0));
    }

    /**
     * test for get score
     */
    @Test
    public void testGetScore() {
        assertEquals(104, model4.getScore());

        model1.move(1, 3, 3, 3);
        assertEquals(31, model1.getScore());
    }
}