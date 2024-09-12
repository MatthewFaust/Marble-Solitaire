package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.view.MarbleSolitaireView;

import java.util.ArrayList;

/**
 * Interface for the abstarct factory design pattern
 */
public interface MarbleSolitaireAbstractFactory {

    /**
     * Factory method to create a  Model
     * @return an  Model with the feilds as paramters
     */
    MarbleSolitaireModel createModel();

    /**
     * Factory method to create a view
     * @return a Model view with the inputted model
     */
    MarbleSolitaireView createView();


    /**
     * Factory method to create a view with an Appendable
     * @param ap appendle objuct to be outputted
     * @return a Solitaire Marble solitare text view
     */

    MarbleSolitaireView createView(Appendable ap);

    /**
     * Static method to make a factory
     * @param name the inputted model
     * @return the factory with defult inputs
     */

    static MarbleSolitaireAbstractFactory getFactory(String name) {
        switch (name.toLowerCase()) {
            case "english":
                return new EnglishSolitaireFactory(3, 3, 3);
            case "european":
                return new EuropeanSolitaireFactory(3, 3, 3);
            case "triangular":
                return new TriangleSolitaireFactory(5, 0, 0);
            default :
                throw new IllegalArgumentException("Invalid game type " + name);
        }
    }

    /**
     * Static method to create a factory
     * @param name name of the model
     * @param length dimension/armThickness
     * @param row the row of the hole
     * @param col the col of the hole
     * @return the model with the inputted fields
     */

    static MarbleSolitaireAbstractFactory getAllFactory(String name, int length, int row, int col) {
         switch (name.toLowerCase()) {
            case "english":
                return new EnglishSolitaireFactory(length, row, col);
            case "european":
                return new EuropeanSolitaireFactory(length, row, col);
            case "triangular":
                return new TriangleSolitaireFactory(length, row, col);
             default:
                 throw new IllegalArgumentException("Invalid game type " + name);
        }
    }

    /**
     * Static method to create a factory
     * @param name name of the model
     * @param row the row of the hole
     * @param col the col of the hole
     * @return the model with the inputted fields
     */
    static MarbleSolitaireAbstractFactory getStartFactory(String name, int row, int col) {
         switch (name.toLowerCase()) {
            case "english":
                return new EnglishSolitaireFactory(3, row, col);
            case "european":
                return new EuropeanSolitaireFactory(3, row, col);
            case "triangular":
                return new TriangleSolitaireFactory(5, row, col);
             default: throw new IllegalArgumentException("Invalid game type " + name);
        }
    }


    /**
     * Static method to create a factory
     * @param name name of the model
     * @param length dimension/armThickness
     * @return the model with the inputted feilds
     */

    static MarbleSolitaireAbstractFactory getSizeFactory(String name, int length) {
         switch (name.toLowerCase()) {
            case "english":
                return new EnglishSolitaireFactory(length, 3, 3);
            case "european":
                return new EuropeanSolitaireFactory(length, 3, 3);
            case "triangular":
                return new TriangleSolitaireFactory(length, 0, 0);
             default:
                 throw new IllegalArgumentException("Invalid game type  " + name);
        }
    }
}
