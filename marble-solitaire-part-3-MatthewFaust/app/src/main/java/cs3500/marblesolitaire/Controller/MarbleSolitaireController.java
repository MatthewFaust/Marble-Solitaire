package cs3500.marblesolitaire.Controller;

public interface MarbleSolitaireController {

  /**
   * play new game
   * @throws IllegalStateException if the controller is unable to successfully read input or transmit output
   */
  void playGame() throws IllegalStateException;
}
