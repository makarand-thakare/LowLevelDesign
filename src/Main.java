import TicTacToe.TicTacToeGameController;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // Add your move in 3x3 matrix. E.g. 1,2
        TicTacToeGameController ticTacToeGameController =
                new TicTacToeGameController("Player1", "Player2");
        ticTacToeGameController.startGame();

    }
}
