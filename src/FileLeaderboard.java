
/*Header
Name: Tony Ren
Date: 2023-12-15
Course Code: ICS3U1-08 Mr.Fernandes
Title: FileLeaderBoard.java
Description: The file reads the leaderboard file and sort it in descending order of score.
Major Skills: import, scanner, bufferedreader, arrays, arrayslist and arrays, while and for loop, if and else statement.
*/

//Import 
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

//Class
public class FileLeaderboard {

	// Constructor Method
	public FileLeaderboard(ArrayList<Player> player) {

		// Clear the arraylist
		player.clear();

		// Variables
		Scanner inputFile;

		// Receiving Data From File
		try {
			// Receive the file
			inputFile = new Scanner(new File("Leaderboard.csv"));

			// Separate the file by , and each line
			inputFile.useDelimiter(",|\r\n");

			// Count the number of lines in the file
			BufferedReader reader = new BufferedReader(new FileReader("Leaderboard.csv"));
			int lines = 0;
			try {
				while (reader.readLine() != null)
					lines++;
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			// Create a for loop to store each player data
			for (int count = 0; count < lines; count++) {

				// Store each player data
				String name = inputFile.next();
				int Score = inputFile.nextInt();

				// Store data as a player object
				Player aplayer = new Player(name, Score);
				player.add(aplayer);

			}

			// Create a temp player array
			@SuppressWarnings("unchecked")
			ArrayList<Player> player2 = (ArrayList<Player>) player.clone();

			// Sort the arraylist
			sortArray(player, player2);

			// Close Scanner after done
			inputFile.close();

		} catch (FileNotFoundException e) {
			System.out.println("File not Found");
		}
	}

	// This method sort the array
	private void sortArray(ArrayList<Player> player, ArrayList<Player> player2) {

		// Create a new int array to sort their score
		Integer[] scoreArray = new Integer[player.size()];

		// Create a for loop to read all player
		for (int index = 0; index < player.size(); index++) {

			// add their score to the score array
			scoreArray[index] = player.get(index).getScore();
		}

		// sort the integer array
		Arrays.sort(scoreArray, Collections.reverseOrder());

		// clear the first array
		player.clear();

		// insert them in the temp player arrays
		for (int index = 0; index < scoreArray.length; index++) {

			// Find matching score in each player
			for (int index2 = 0; index2 < scoreArray.length; index2++) {
				if (player2.get(index2).getScore() == scoreArray[index])
					player.add(player2.get(index2));
			}
		}

	}

}
