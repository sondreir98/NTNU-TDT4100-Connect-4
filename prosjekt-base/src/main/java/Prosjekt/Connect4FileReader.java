package Prosjekt;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Connect4FileReader {

    private String winnerFilePath = "C:\\Users\\sondr\\OneDrive\\Dokumenter\\#NTNU\\TDT4100-Prosjekt\\Connect4Games\\Winners.txt"; // File
                                                                                                                                   // path
                                                                                                                                   // to
                                                                                                                                   // store
                                                                                                                                   // winner
                                                                                                                                   // information
    private int redCount;
    private int yellowCount;
    private Connect4Model connect4Game;

    public Connect4FileReader(Connect4Model connect4Game) {
        redCount = 0;
        yellowCount = 0;
        this.connect4Game = connect4Game;
    }

    public void saveWinnerToFile() {
        // Legge til en reset file knapp??
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(winnerFilePath, true))) {
            String winner = "";
            if (connect4Game.isGameEnded()) {
                if (connect4Game.getCurrentPlayer() == 'O') {
                    winner = "Red";
                } else if (connect4Game.getCurrentPlayer() == 'X') {
                    winner = "Yellow";
                }
                writer.write(winner);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readWinnerFromFile() throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File(winnerFilePath))) {
            {
                while (scanner.hasNextLine()) {
                    String lineFromFile = scanner.nextLine().trim();
                    if (lineFromFile.equals("Red")) {
                        this.redCount++;
                    } else {
                        this.yellowCount++;
                    }
                }
            }
        }
    }

    public void resetFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(winnerFilePath, false))) { // False to indicate
                                                                                                  // that the file is
                                                                                                  // being overwritten
            writer.write(""); // Write an empty string to clear the file contents
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getRedCount() {
        return redCount;
    }

    public int getYellowCount() {
        return yellowCount;
    }

}
