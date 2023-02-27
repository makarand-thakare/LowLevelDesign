package TicTacToe;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicTacToeGameController {

    List<Player> playerList = new ArrayList<>();
    Board board;
    Player currentPlayer;
    private String player1Name;
    private String player2Name;
    private Player lostPlayer;

    public TicTacToeGameController(String player1Name ,String player2Name ) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;

        initialiseGame();
    }

    public void startGame() {
        board.drawBoard();
        while (true) {
            currentPlayer = playerList.get(0);
            playerPlayMessage(currentPlayer);

            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            String[] inputArray = input.split(",");

            boolean moveAdded = board.addMove(inputArray, currentPlayer);
            if (!moveAdded) {
                continue;
            }
            board.drawBoard();
            Player player = getWinner(board.checkWinner());
            if (player == null) {
                GameStatus gameStatus = board.getGameStatus();
                if (gameStatus == GameStatus.COMPLETED) {
                    System.out.println("Game is tied!");
                    break;
                }
                // do nothing for ongoing
            } else {
                System.out.println(player.name + " won the game :D");
                System.out.println("!!! Congratulations " + player.name + " !!!");
                System.out.println();
                System.out.println("Hey " +lostPlayer.name + ", Don't be sad. \nThere is always next game, right? Keep Going :)");
                break;
            }

            Player removedPlayer = playerList.remove(0);
            playerList.add(removedPlayer);
        }
    }

    private Player getWinner(PlayingPiece playingPiece) {
        if (playingPiece == null) {
            return null;
        }
        lostPlayer = playerList.get(1);
        for (int i = 0; i < playerList.size(); i++) {

            if (playerList.get(i).playingPiece.pieceType.toString() == playingPiece.pieceType.toString()) {
                return playerList.get(i);
            }
            lostPlayer = playerList.get(0);

        }
        return null;
    }

    private void playerPlayMessage(Player currentPlayer) {
        System.out.println(currentPlayer.name + ", please add your move: ");
    }


    private void initialiseGame() {
        playerList.add(new Player(player1Name, new PieceO()));
        playerList.add(new Player(player2Name, new PieceX()));

        board = new Board(3);
    }
}
