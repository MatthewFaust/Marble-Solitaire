package cs3500.marblesolitaire;

import cs3500.marblesolitaire.Controller.MarbleSolitaireController;
import cs3500.marblesolitaire.Controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw04.*;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

import java.io.InputStreamReader;

/**
 * class tp run the game
 * based off inputs it creates a model and view which it gives as paramaters for the controller.
 * Then the controller runs the game and uses System.in to take in arguments and System.out to produce the results
 */
public final class MarbleSolitaire {
    public static void main(String[] args) {


        /**
         * initializing variables
         */
        String modelName = "";
        int sRow = -1;
        int sCol = -1;
        int size = -1;
        MarbleSolitaireModel model;
        MarbleSolitaireTextView view;
        TriangleSolitaireTextView tview;
        MarbleSolitaireController controller = null;


        /**
         * for loop to check what type of board will be created along with parameters size, sRow, and sCol
         */
        for (int i = 0; i < args.length; i++) {

            switch (args[i]) {
                case "european":
                    modelName = "european";
                    break;
                case "english":
                    modelName = "english";
                    break;
                case "triangular":
                    modelName = "triangular";
                    break;
                case "-hole":
                    sRow = Integer.parseInt(args[i + 1]);
                    sCol = Integer.parseInt(args[i + 2]);
                    break;
                case "-size":
                    size = Integer.parseInt(args[i + 1]);
                    break;
            }
        }


        if (size == -1) {
            if (modelName.equals("triangular")) {
                size = 5;
            } else {
                size = 3;
            }
        }

        if (sRow == -1 && sCol == -1) {
            if (modelName.equals("triangular")) {
                sRow = 0;
                sCol = 0;
            } else {
                int xy = (size * 3 - 2) / 2;
                sRow = xy;
                sCol = xy;
            }

        }



        Readable input = new InputStreamReader(System.in);

        switch (modelName) {
            case "triangular":
                model = new TriangleSolitaireModel(size, sRow, sCol);
                tview = new TriangleSolitaireTextView(model, System.out);
                controller = new MarbleSolitaireControllerImpl(model, tview, input);
                break;
            case "english":
                model = new EnglishSolitaireModel(size, sRow, sCol);
                view = new MarbleSolitaireTextView(model, System.out);
                controller = new MarbleSolitaireControllerImpl(model, view, input);
                break;
            case "european":
                model = new EuropeanSolitaireModel(size, sRow, sCol);
                view = new MarbleSolitaireTextView(model, System.out);
                controller = new MarbleSolitaireControllerImpl(model, view, input);
                break;
        }
        if (controller != null) {
            controller.playGame();
        }
    }
}
