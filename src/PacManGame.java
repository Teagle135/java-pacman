/* =========================================================
 * Name: Teagle135
 * Date: December 15, 2023
 * Title: Pac-Man Game (Java)
 *
 * Description:
 *   A fully playable Pac-Man–style game developed in Java using
 *   Swing GUI. The game includes animated characters, sound effects,
 *   power-ups, bonus items (such as fruits), multiple lives, and a
 *   persistent leaderboard that stores player scores using file I/O.
 *
 * Major Skills & Concepts:
 *   - Object-Oriented Programming (methods, objects, classes)
 *   - Control Structures (if-else, for loops, while loops)
 *   - Java Swing GUI components
 *   - Arrays, ArrayList, and List collections
 *   - File I/O (FileInputStream, BufferedReader, Scanner)
 *   - Game timer and real-time game logic
 *
 * Implemented Features:
 *
 *   Basic Features:
 *   1. Score tracking with a dedicated score panel
 *   2. Restricted ghost house entry using a gate system
 *   3. Logic to release ghosts from the house and prevent re-entry
 *   4. Interactive title screen with UI elements
 *   5. Life system (3 lives before game over)
 *   6. Bonus items (cherries and other fruits)
 *   7. Power-ups (speed boost, slow down, teleport, bonus life)
 *   8. Sound effects and background music
 *
 *   Advanced Features:
 *   1. High-score saving with player names using file storage
 *   2. Persistent leaderboard displaying past player scores
 *
 * Areas for Improvement:
 *   1. Pac-Man death animation may not always complete smoothly
 *
 * =========================================================
 */


//PacManGame - this class runs the entire game
public class PacManGame {

	// Main method
	public static void main(String[] args) {

		// Call the title frame
		new PacManTitleFrame();

	}

}
