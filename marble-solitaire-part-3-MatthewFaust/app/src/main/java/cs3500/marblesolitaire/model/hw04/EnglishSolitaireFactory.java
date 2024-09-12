package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

/**
 * Factory subclass for English Model
 */
public class EnglishSolitaireFactory implements MarbleSolitaireAbstractFactory{
 private   int armThickness;
    private  int sRow;
    private int sCol;
    private MarbleSolitaireModel model;
    private MarbleSolitaireView view;

    /**
     * Factory English contructor, creates the model as well
     * @param armThickness takes in game length
     * @param sRow the row for the hole
     * @param sCol the colum for the hole
     */
    public EnglishSolitaireFactory(int armThickness, int sRow, int sCol) {
        this.armThickness = armThickness;
        this.sRow = sRow;
        this.sCol = sCol;
        this.model = new EnglishSolitaireModel(armThickness, sRow, sCol);


    }

    /**
     * Factory method to create an english Model
     * @return an English Model with the feilds as paramters
     */
    @Override
    public EnglishSolitaireModel createModel() {
        return new EnglishSolitaireModel(this.armThickness, this.sRow, this.sCol);
    }

    /**
     * Factory method to create a view
     * @return an English view with the model
     */
    @Override
    public MarbleSolitaireView createView() {
        return new MarbleSolitaireTextView(this.createModel());
    }

    /**
     * Factory method to create a view with an Appendable
     * @param ap appendle objuct to be outputted
     * @return a Marble solitare text view
     */
    @Override
    public MarbleSolitaireView createView(Appendable ap) {
        return new MarbleSolitaireTextView(this.createModel(), ap);    }


}
