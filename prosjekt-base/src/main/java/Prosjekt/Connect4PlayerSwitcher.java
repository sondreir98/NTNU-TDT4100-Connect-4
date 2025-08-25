package Prosjekt;

public class Connect4PlayerSwitcher {

    private char currentPlayer = 'X'; // Player X starts the game

    public Connect4PlayerSwitcher() {
    }

    public void switchPlayer() {
        if (currentPlayer == 'X') {
            currentPlayer = 'O';
        }

        else {
            currentPlayer = 'X';
        }
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(char currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
}
