package Prosjekt;

public class Connect4Model implements Connect4Interface {

    private int numRows; // Number of rows in the game board
    private int numCols; // Number of columns in the game board
    private char[][] gameBoard; // 2D array representing the game board
    private boolean gameEnded; // Flag to indicate if the game has ended
    private boolean draw; // Flag to indicate if the game ended in a draw
    private Connect4PlayerSwitcher playerSwitcher; // Class to handle player logic
    private int column; // Current column
    private int row; // Current row

    // Constructor
    public Connect4Model() {
        this.numRows = 6;
        this.numCols = 7;
        this.gameBoard = new char[numCols][numRows];
        this.gameEnded = false;
        this.draw = false;
        playerSwitcher = new Connect4PlayerSwitcher();
        resetGameBoard();

    }

    public void resetGameBoard() {
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                gameBoard[col][row] = ' ';
            }
        }

        char currentPlayer = 'X'; // Player X starts the game
        playerSwitcher.setCurrentPlayer(currentPlayer);
        this.gameEnded = false;
        this.draw = false;

    }

    public boolean makeMove(int col, int row) {
        if (gameEnded || row < 0 || row >= numRows || col < 0 || col >= numCols || gameBoard[col][row] != ' ') {
            return false;
        }

        // Recursive loop to make sure gravity is accounted for
        if (row != numRows - 1) {
            if (gameBoard[col][row + 1] == ' ') {
                return makeMove(col, row + 1);
            }
        }

        gameBoard[col][row] = playerSwitcher.getCurrentPlayer(); // Set the current player's symbol in the selected cell
        setColumn(col); // Set column for controller to use
        setRow(row); // Set row for controller to use

        if (checkWin(col, row)) {
            gameEnded = true;
        } else if (isGameDraw(gameBoard)) {
            gameEnded = true;
            draw = true;
        }
        playerSwitcher.switchPlayer();
        // Check in terminal if game runs correctly
        for (int i = 0; i < numRows; i++) {
            // Iterate over the columns
            for (int j = 0; j < numCols; j++) {
                System.out.print(gameBoard[j][i] + " "); // Print the value at the current position, followed by a space
            }
            System.out.println(); // Print a new line after each row
        }
        return true;
    }

    public boolean checkWin(int col, int row) {
        char player = playerSwitcher.getCurrentPlayer();
        int count = 0;

        // Check for vertical win
        for (int i = 0; i < numRows; i++) {
            if (gameBoard[col][i] == player) {
                count++;
            } else {
                count = 0; // Reset count if current player is not found consecutively
            }

            if (count == 4) {
                return true;
            }
        }

        // Check for horizontal win
        count = 0; // Reset count before checking horizontal wins
        for (int i = 0; i < numCols; i++) {
            if (gameBoard[i][row] == player) {
                count++;

            } else {
                count = 0; // Reset count if current player is not found consecutively
            }

            if (count == 4) {
                return true;
            }
        }

        // Check for diagonal win (top-left to bottom-right)
        int startRow = row - Math.min(row, col);
        int startCol = col - Math.min(row, col);
        count = 0;
        while (startRow < numRows && startCol < numCols) {
            if (gameBoard[startCol][startRow] == player) {
                count++;
            } else {
                count = 0;
            }

            if (count == 4) {
                return true; // Diagonal win
            }

            startRow++;
            startCol++;
        }

        // Check for diagonal win (top-right to bottom-left)
        count = 0;
        startRow = row - Math.min(row, numCols - 1 - col);
        startCol = col + Math.min(row, numCols - 1 - col);
        while (startRow < numRows && startCol >= 0) {
            if (gameBoard[startCol][startRow] == player) {
                count++;
            } else {
                count = 0;
            }

            if (count == 4) {
                return true; // Diagonal win
            }

            startRow++;
            startCol--;
        }

        return false; // No win
    }

    // Method to check if the game ended in a draw
    public boolean isGameDraw(char[][] connect4Game) {
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                if (gameBoard[col][row] == ' ') {
                    return false; // Game is not draw as long as there's an empty cell
                }
            }
        }
        return true; // Game is draw
    }

    public void switchPlayer() {
        playerSwitcher.switchPlayer();
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumCols() {
        return numCols;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public char[][] getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(char[][] gameBoard) {
        this.gameBoard = gameBoard;
    }

    public char getCurrentPlayer() {
        return playerSwitcher.getCurrentPlayer();
    }

    public boolean isGameEnded() {
        return gameEnded;
    }

    public boolean isDraw() {
        return draw;
    }

    public void setGameEnded() {
        this.gameEnded = true;
    }

}