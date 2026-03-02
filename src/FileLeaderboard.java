// Loads and sorts leaderboard entries from disk.

//Import 
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

//Class
public class FileLeaderboard {

	// Constructor Method
	public FileLeaderboard(ArrayList<Player> player) {

		// Clear the arraylist
		player.clear();

		// Receiving Data From File
		File leaderboardFile = new File("Leaderboard.csv");
		if (!leaderboardFile.exists()) {
			return;
		}
		try (BufferedReader reader = new BufferedReader(new FileReader(leaderboardFile))) {
			String line;

			// Read each entry line-by-line so either Windows or Unix line endings work.
			while ((line = reader.readLine()) != null) {
				line = line.trim();

				if (line.isEmpty()) {
					continue;
				}

				String[] entry = line.split(",", 2);
				if (entry.length < 2) {
					continue;
				}

				String name = entry[0].trim();
				String scoreText = entry[1].trim();

				try {
					int score = Integer.parseInt(scoreText);
					player.add(new Player(name, score));
				} catch (NumberFormatException e) {
					System.out.println("Skipping invalid leaderboard entry: " + line);
				}
			}

			// Sort the arraylist
			sortArray(player);
		} catch (FileNotFoundException e) {
			System.out.println("File not Found");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// This method sort the array
	private void sortArray(ArrayList<Player> player) {
		player.sort((player1, player2) -> Integer.compare(player2.getScore(), player1.getScore()));
	}

}
