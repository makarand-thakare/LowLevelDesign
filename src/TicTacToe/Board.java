package TicTacToe;

public class Board {
    int boardSize;
    PlayingPiece[][] tttBoard;

    public Board(int boardSize) {
        this.boardSize = boardSize;
        tttBoard = new PlayingPiece[boardSize][boardSize];
    }

    public void drawBoard() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                String pieceValue = "";
                if (tttBoard[i][j] != null) {

                    pieceValue = tttBoard[i][j].pieceType.toString();
                }
                System.out.print(" " + pieceValue + " ");
                if (j + 1 != boardSize) {
                    System.out.print("|");
                }
            }
            if (i + 1 != boardSize) {
                System.out.println("\n---------");
            }
        }
        System.out.println();

    }

    public boolean addMove(String[] inputArray, Player currentPlayer) {
        int row = Integer.parseInt(inputArray[0]);
        int col = Integer.parseInt(inputArray[1]);
        if (tttBoard[row][col] != null) {
            System.out.println("That square is already taken, please try again.");
            return false;
        }

        tttBoard[row][col] = currentPlayer.playingPiece;
        return true;
    }

    public GameStatus getGameStatus() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (tttBoard[i][j] == null) {
                    return GameStatus.ONGOING;
                }
            }
        }
        return GameStatus.COMPLETED;
    }

    // todo to do it in O(n)
    public PlayingPiece checkWinner() {

        PlayingPiece currentPiece = null;

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (currentPiece == null) {
                    currentPiece = tttBoard[i][j];
                    if (currentPiece == null) {
                        break;
                    }
                } else {

                    if (tttBoard[i][j] == null ||
                            (tttBoard[i][j] != null && currentPiece.pieceType != tttBoard[i][j].pieceType)) {
                        currentPiece = null;
                        break;
                    }
                }
            }
            if (currentPiece != null) {
                return currentPiece;
            }
        }

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (currentPiece == null) {
                    currentPiece = tttBoard[j][i];
                    if (currentPiece == null) {
                        break;
                    }
                } else {
                    if (tttBoard[j][i] == null ||
                            (tttBoard[j][i] != null && currentPiece.pieceType != tttBoard[j][i].pieceType)) {
                        currentPiece = null;
                        break;
                    }
                }
            }
            if (currentPiece != null) {
                return currentPiece;
            }
        }


        for (int i = 0; i < boardSize; i++) {
            if (currentPiece == null) {
                currentPiece = tttBoard[i][i];
                if (currentPiece == null) {
                    break;
                }
            } else {
                if (tttBoard[i][i] == null ||
                        (tttBoard[i][i] != null && currentPiece.pieceType != tttBoard[i][i].pieceType)) {
                    currentPiece = null;
                    break;
                }
            }
        }
        if (currentPiece != null) {
            return currentPiece;
        }


        for (int i = 0; i < boardSize; i++) {
            int col = boardSize-1-i;
            if (currentPiece == null) {
                currentPiece = tttBoard[i][col];
                if (currentPiece == null) {
                    break;
                }
            } else {
                if (tttBoard[i][col] == null ||
                        (tttBoard[i][col] != null && currentPiece.pieceType != tttBoard[i][col].pieceType)) {
                    currentPiece = null;
                    break;
                }
            }
        }

        return currentPiece;
    }

}
