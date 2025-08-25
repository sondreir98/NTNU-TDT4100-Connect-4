package exampleproject;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Prosjekt.Connect4FileReader;
import Prosjekt.Connect4Model;

public class Connect4ModelTest {

    private Connect4Model connect4Model;
    private static final String filePath = "C:\\Users\\sondr\\OneDrive\\Dokumenter\\#NTNU\\TDT4100-Prosjekt\\Connect4Games\\Winners.txt";
    private Connect4FileReader connect4FileReader;

    @BeforeEach
    public void setup() {
        connect4Model = new Connect4Model();
        connect4FileReader = new Connect4FileReader(connect4Model);
    }

    @Test
    public void testResetGameBoard() {
        // Set up initial game board
        char[][] expectedGameBoard = new char[7][6];
        for (int col = 0; col < 7; col++) {
            for (int row = 0; row < 6; row++) {
                expectedGameBoard[col][row] = ' ';
            }
        }

        // Make some moves
        connect4Model.makeMove(0, 0);
        connect4Model.makeMove(1, 1);
        connect4Model.makeMove(2, 2);

        // Reset game board
        connect4Model.resetGameBoard();

        // Verify game board is reset
        assertArrayEquals(expectedGameBoard, connect4Model.getGameBoard());
        assertFalse(connect4Model.isGameEnded());
        assertFalse(connect4Model.isDraw());
    }

    @Test
    public void testInvalidMove() {
        assertFalse(connect4Model.makeMove(-1, 0));
        assertFalse(connect4Model.makeMove(0, -1));
        assertFalse(connect4Model.makeMove(7, 0));
        assertFalse(connect4Model.makeMove(0, 6));
    }

    @Test
    public void testCheckWin_HorizontalWin_ReturnsTrue() {
        // Set up horizontal win for Player 1
        connect4Model.makeMove(0, 0); // Player 1 (X) moves in column 0
        connect4Model.makeMove(0, 1); // Player 2 (O) moves in column 0
        connect4Model.makeMove(1, 0); // Player 1 (X) moves in column 1
        connect4Model.makeMove(1, 1); // Player 2 (O) moves in column 1
        connect4Model.makeMove(2, 0); // Player 1 (X) moves in column 2
        connect4Model.makeMove(2, 1); // Player 2 (O) moves in column 2
        connect4Model.makeMove(3, 0); // Player 1 (X) moves in column 3

        assertTrue(connect4Model.isGameEnded()); // Check for win in column 3, row 0
    }

    @Test
    public void testSaveWinnerToFile() throws IOException {
        // Delete the test winner file if it exists
        File winnerFile = new File(filePath);
        if (winnerFile.exists()) {
            winnerFile.delete();
        }

        // Set up game state
        connect4Model.setGameEnded(); // Set game ended to true

        // Call saveWinnerToFile
        connect4FileReader.saveWinnerToFile();

        // Check if the winner file is created
        assertTrue(winnerFile.exists());

        // Read the winner file to check its contents
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String winner = reader.readLine();
        reader.close();

        // Assert that the winner in the file matches the expected value
        assertEquals("Yellow", winner);
    }

    @Test
    public void testReadWinnerFromFile() throws IOException {
        // Set up test winner file
        File winnerFile = new File(filePath);
        if (!winnerFile.exists()) {
            winnerFile.createNewFile();
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        writer.write("Red");
        writer.newLine();
        writer.write("Yellow");
        writer.newLine();
        writer.write("Red");
        writer.close();

        // Call readWinnerFromFile
        connect4FileReader.readWinnerFromFile();

        // Check the red and yellow count values
        assertEquals(2, connect4FileReader.getRedCount());
        assertEquals(1, connect4FileReader.getYellowCount());
    }

    @Test
    public void testIsGameDraw() {
        // Test when top row is not full:
        char[][] gameBoard = {
                { ' ', 'X', 'O', 'X', 'X', 'X', },
                { 'X', 'X', 'O', 'X', 'O', 'X', },
                { 'X', 'O', 'X', 'O', 'O', 'O', },
                { 'O', 'X', 'X', 'O', 'X', 'X', },
                { 'X', 'O', 'O', 'O', 'X', 'O', },
                { 'O', 'X', 'O', 'X', 'O', 'X', },
                { 'O', 'X', 'O', 'X', 'O', 'X', }
        };
        connect4Model.setGameBoard(gameBoard);
        assertFalse(connect4Model.isGameDraw(gameBoard));

        // Fill up the game board to simulate a draw
        char[][] fullGameBoard = {
                { 'X', 'X', 'O', 'X', 'O', 'X', },
                { 'X', 'X', 'O', 'X', 'O', 'X', },
                { 'X', 'O', 'X', 'O', 'O', 'O', },
                { 'O', 'X', 'X', 'O', 'X', 'X', },
                { 'X', 'O', 'O', 'O', 'X', 'O', },
                { 'O', 'X', 'O', 'X', 'O', 'X', },
                { 'O', 'X', 'O', 'X', 'O', 'X', }
        };
        connect4Model.setGameBoard(fullGameBoard);

        // Test when game board is full
        assertTrue(connect4Model.isGameDraw(fullGameBoard));
    }
}
