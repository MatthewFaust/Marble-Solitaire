package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

/**
 * Factory subclass for Triangle Model
 */
public class TriangleSolitaireFactory implements MarbleSolitaireAbstractFactory{
    private    int dimensions;
    private  int sRow;
    private  int sCol;
    private   MarbleSolitaireModel model;

    /**
     * Factory triangle contructor, creates the model as well
     * @param dimensions takes in game length
     * @param sRow the row for the hole
     * @param sCol the colum for the hole
     */

        public TriangleSolitaireFactory(int dimensions, int sRow, int sCol) {
            this.dimensions = dimensions;
            this.sRow = sRow;
            this.sCol = sCol;
            this.model = new TriangleSolitaireModel(this.dimensions, this.sRow, this.sCol);
        }

    /**
     * Factory method to create a Triangle Model
     * @return an Triangle Model with the feilds as paramters
     */

        @Override
        public TriangleSolitaireModel createModel() {
            return new TriangleSolitaireModel(this.dimensions, this.sRow, this.sCol);
    }

    /**
     * Factory method to create a view
     * @return a Triangle view with the model
     */

    @Override
    public TriangleSolitaireTextView createView() {
        return new TriangleSolitaireTextView(this.createModel());
    }



    /**
     * Factory method to create a view with an Appendable
     * @param ap appendle objuct to be outputted
     * @return a Triangle Marble solitare text view
     */


    @Override
    public TriangleSolitaireTextView createView(Appendable ap) {
         return new TriangleSolitaireTextView(this.createModel(), ap);
    }


}
