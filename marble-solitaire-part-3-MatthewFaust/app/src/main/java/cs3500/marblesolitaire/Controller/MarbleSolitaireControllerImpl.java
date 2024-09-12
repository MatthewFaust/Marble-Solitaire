package cs3500.marblesolitaire.Controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import cs3500.marblesolitaire.model.hw04.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

/**
 * Controller class of MVC for marbleSolitaire
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {
  private MarbleSolitaireModel model;
  private MarbleSolitaireView view;
  private Readable read;
  private boolean quit;


  /**
   * Constructor for Controller
   * @param model model of the game
   * @param view the view of the baord
   * @param read the inputted moves
   * @throws IllegalArgumentException if any input is null
   */
  public MarbleSolitaireControllerImpl(MarbleSolitaireModel model, MarbleSolitaireView view, Readable read) throws IllegalArgumentException {
    if (read == null || model == null || view == null) {
      throw new IllegalArgumentException("The model view and read cannot be null.");
    }
    
    this.model = model;
    this.view = view;
    this.read = read;
    this.quit = false;
  }

  /**
   * Method to check if the user has decided to quit the game during any input
   * just an abstraction
   * @param str the inputted string
   * @return True if the game is quit
   */
  public boolean quitGame(String str) {
    if (str.equals("q") || str.equals("Q")) {
      try {
        this.view.renderMessage("Game quit!\n");
        this.view.renderMessage("State of game when quit:\n");
        this.view.renderBoard();
        this.view.renderMessage("Score: " + model.getScore());
      } catch (IOException e) {
        throw new IllegalStateException(e);
      }
      return true;
    } else {
      return false;
    }
  }

  /**
   * herlper method to render the board with a given message in the play game method
   * @param msg is the message passed in, usually conating a remark and score
   *
   */
  public void renderHelper(String msg){
    try {
      this.view.renderMessage(msg);
      this.view.renderBoard();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }



  /**
   * Helper method to handle user input and validation
   * @param scanner the Scanner object for reading input
   * @param messages array of error messages for invalid input
   * @return array of valid input integers
   */
  private int[] getUserInput(Scanner scanner, String[] messages) {
    int[] input = new int[4];
    String[] moves = scanner.nextLine().split("\\s+"); // Split input by any whitespace

    if (this.quitGame(moves[0])) {
      quit = true;
      return input; // Return array of zeroes if quitting
    }

    while (moves.length != 4) {
      System.out.println("Incorrect number of digits. Please enter 4 integers and try again.");
      moves = scanner.nextLine().split("\\s+");
      if (this.quitGame(moves[0])) {
        quit = true;
        return input; // Return array of zeroes if quitting
      }
    }

    for (int i = 0; i < input.length; i++) {
      boolean valid = false;
      while (!valid) {
        try {
          input[i] = Integer.parseInt(moves[i]);
          if (input[i] < 0) {
            System.out.println("Please enter a positive integer for " + messages[i]);
          } else {
            valid = true;
          }
        } catch (NumberFormatException n) {
          System.out.println(messages[i]);
        }
        if (valid) break;
        moves[i] = scanner.nextLine();
        if (this.quitGame(moves[i])) {
          quit = true;
          return input; // Return array of zeroes if quitting
        }
      }
    }
    return input;
  }

  /**
   * Main method in controller that connects model and view to run the game based on inputs
   * @throws IllegalStateException if the board cannot communicate with the view
   */
  @Override
  public void playGame() throws IllegalStateException {
    Scanner scanner = new Scanner(this.read);

    this.renderHelper("Current score: " + model.getScore() + "\n");

    String[] messages = {
            "Please enter a valid integer for From Row:",
            "Please enter a valid integer for From Col:",
            "Please enter a valid integer for To Row:",
            "Please enter a valid integer for To Col:"
    };

    while (!model.isGameOver() && !quit) {
      int[] input = getUserInput(scanner, messages);

      if (quit) return;

      this.model.move(input[0], input[1], input[2], input[3]);
      this.renderHelper("Current score: " + model.getScore() + "\n");
    }

    if (model.isGameOver()) {
      this.renderHelper("Game Over! Score: " + model.getScore());
    }
  }
}
