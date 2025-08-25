package Prosjekt;

public interface Connect4Interface {

    public void resetGameBoard();

    public boolean makeMove(int row, int col);

    public boolean checkWin(int row, int col);

    public boolean isGameDraw(char[][] gameBoard);

    public void switchPlayer();

    public int getNumRows();

    public int getNumCols();

    public char[][] getGameBoard();

    public char getCurrentPlayer();

    public boolean isGameEnded();

    public boolean isDraw();
}
