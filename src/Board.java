// Handles board state, movement, collisions, and score updates.

//Import 
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TimerTask;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

//BoardDay1 - Creates the board for day 1
@SuppressWarnings("serial")
public class Board extends JPanel implements KeyListener, ActionListener {

	// Create timer
	private javax.swing.Timer gameTimer = new javax.swing.Timer(200, this);
	private java.util.Timer timer = new java.util.Timer();

	// Create the board using a 2D array
	private Cell[][] mazeArray = new Cell[25][27];

	// Pacman variable
	private Mover pacMan;

	// Ghost
	private Mover[] ghostArray = new Mover[3];

	// number of pellets
	private int pellets = 0;

	// User's score
	private int score = 0;
	private int hiscore = 0;

	// User's live
	private int lives = 2;

	// Create field for pacman and ghost location
	int pRow;
	int pCol;
	int gRow1;
	int gCol1;
	int gRow2;
	int gCol2;
	int gRow3;
	int gCol3;

	// Create a variable for user to save their name
	String username = "";

	// create a variable to see if timer finish delay
	private boolean delay = false;
	private boolean delay2 = false;
	private boolean delay3 = false;

	// create a variable to determine if user ate the bonus item
	private boolean bonus = false;

	// Constructor Method
	public Board() {

		// Setup the board
		setLayout(new GridLayout(25, 27));
		setBackground(Color.black);
		setBorder(BorderFactory.createLineBorder(Color.white, 10));
		loadBoard();

		// Display the input message box
		username = JOptionPane.showInputDialog("Please enter the name you want to be recorded as:");
	}

	// This method load the board
	private void loadBoard() {

		// reset the variable
		delay = false;
		delay2 = false;
		delay3 = false;

		// reset the text
		PacManGUI.scoreText.setText("SCORE:0");

		// Variable
		int row = 0;
		Scanner inputFile;

		// Try if system can read the file
		try {

			// read the file
			inputFile = new Scanner(new File("maze.txt"));

			// keep on reading until reaches the end
			while (inputFile.hasNext()) {

				// Read by line
				char[] lineArray = inputFile.nextLine().toCharArray();
				// Read every column in a row
				for (int column = 0; column < lineArray.length; column++) {

					// setup each column
					mazeArray[row][column] = new Cell(lineArray[column]);

					// keep track of number of pellets
					if (lineArray[column] == 'F') {
						pellets++;
					}

					// keep track of number of strawberry
					if (lineArray[column] == 'S') {
						pellets += 5;
					}
					// keep track of pacman's location
					else if (lineArray[column] == 'P') {
						this.pRow = row;
						this.pCol = column;
						pacMan = new Mover(row, column);
						pacMan.setIcon(Icons.PACMAN[0]);
						pacMan.setDirection(1);
					}

					// set up the ghost
					else if (lineArray[column] == '0' || lineArray[column] == '1' || lineArray[column] == '2') {
						int gNum = Character.getNumericValue(mazeArray[row][column].getItem());

						// save ghost's spawn coordinate
						if (lineArray[column] == '0') {
							this.gRow1 = row;
							this.gCol1 = column;
						}
						// save ghost's spawn coordinate
						else if (lineArray[column] == '1') {
							this.gRow2 = row;
							this.gCol2 = column;
						}
						// save ghost's spawn coordinate
						else if (lineArray[column] == '2') {
							this.gRow3 = row;
							this.gCol3 = column;
						}
						ghostArray[gNum] = new Mover(row, column);
						ghostArray[gNum].setIcon(Icons.GHOST[gNum]);
					}

					// add the maze
					add(mazeArray[row][column]);
				}

				// Increment the row value
				row++;

			}

			// Close the scanner
			inputFile.close();

			// If File not found
		} catch (FileNotFoundException error) {

			// Display File not found if it's not found
			System.out.println("File Not Found");
		}
	}

	@Override
	// This method do action
	public void actionPerformed(ActionEvent event) {

		// Setup the action
		if (event.getSource() == gameTimer) {

			// move pacman and ghost
			performMove(pacMan);
			moveGhosts();
		}

	}

	// this method moves the ghost
	private void moveGhosts() {

		// move each ghost
		for (Mover ghost : ghostArray) {

			// direction
			int dir = 0;
			int dCol = 0;
			int dRow = 0;

			// Make Ghost get out of the spawn first
			if (ghost.getRow() >= 10 && ghost.getRow() < 14 && ghost.getColumn() > 10 && ghost.getColumn() < 16) {
				if (ghost.getColumn() < 13) {
					dCol = 1;
					dir = 2;
				} else if (ghost.getColumn() > 13) {
					dCol = -1;
					dir = 0;
				} else if (ghost.getRow() >= 10) {
					dRow = -1;
					dir = 1;
				}
			} else {

				// generate random direction
				do {
					dir = (int) (Math.random() * 4);
					if (dir == 0)
						dCol = -1;
					else if (dir == 1)
						dRow = -1;
					else if (dir == 2)
						dCol = 1;
					else if (dir == 3)
						dRow = 1;

					// Make sure ghost don't overlap and run into walls
				} while ((Math.abs(ghost.getDirection() - dir) == 2)
						&& mazeArray[ghost.getRow() + dRow][ghost.getColumn() + dCol].getIcon() != Icons.WALL
						&& mazeArray[ghost.getRow() + dRow][ghost.getColumn() + dCol].getIcon() != Icons.GHOST[0]
						&& mazeArray[ghost.getRow() + dRow][ghost.getColumn() + dCol].getIcon() != Icons.GHOST[1]
						&& mazeArray[ghost.getRow() + dRow][ghost.getColumn() + dCol].getIcon() != Icons.GHOST[2]);

			}
			// set direction for ghost
			if (mazeArray[ghost.getRow() + dRow][ghost.getColumn() + dCol].getIcon() != Icons.WALL) {
				ghost.setDirection(dir);
			}
			// Make sure pacman is not dead when ghost are moving
			if (!pacMan.isDead()) {
				performMove(ghost);
			}

		}

	}

	// This method isn't used in the program
	@Override
	public void keyTyped(KeyEvent e) {
		// not used

	}

	@Override
	// This method tells the computer what to do when a key is pressed
	public void keyPressed(KeyEvent key) {

		// Start the game if it's not running and Pac-Man is not dead
		if (!gameTimer.isRunning() && !pacMan.isDead()) {
			gameTimer.start();
			try {
				Clip clip = AudioSystem.getClip();
				clip.open(AudioSystem.getAudioInputStream(new File("sounds//sounds//pacmanintro.wav")));
				clip.start();
			} catch (Exception exc) {
				exc.printStackTrace(System.out);
			}
			PacManGUI.warn.setText(null);
		}
		// Continue handling key presses only if Pac-Man is not dead and the score is
		// not equal to the total number of pellets
		else if (!pacMan.isDead() && score != pellets) {

			// Determine which key is pressed
			int direction = key.getKeyCode() - 37;

			// Initialize the variables for row and column changes
			int dRow = 0;
			int dCol = 0;

			// Tell the computer what to do depending on the key pressed
			if (direction == 0)
				dCol = -1;
			else if (direction == 1)
				dRow = -1;
			else if (direction == 2)
				dCol = 1;
			else if (direction == 3)
				dRow = 1;

			// Make sure Pac-Man doesn't go through a wall or gate
			if (mazeArray[pacMan.getRow() + dRow][pacMan.getColumn() + dCol].getIcon() != Icons.WALL
					&& mazeArray[pacMan.getRow() + dRow][pacMan.getColumn() + dCol].getIcon() != Icons.GATE) {
				pacMan.setIcon(Icons.PACMAN[direction]);
				pacMan.setDirection(direction);
			}
		}
	}

	// This method determine if pacman die from ghost
	private boolean collided() {

		// Check if pacman intersect with ghost
		for (Mover ghost : ghostArray) {
			if (ghost.getRow() == pacMan.getRow() && ghost.getColumn() == pacMan.getColumn()) {
				return true;
			}

		}
		return false;
	}

	// reset the game
	private void resetGame() {
		// Reset variables
		pacMan.setDead(false);
		lives = 2;
		score = 0;
		delay = false;
		delay2 = false;
		delay3 = false;
		bonus = false;

		for (int count = 0; count < PacManGUI.heartArray.length; count++) {
			PacManGUI.heartArray[count].setIcon(Icons.heart);
			PacManGUI.heartPanel.add(PacManGUI.heartArray[count]);
		}
		// Clear the board
		for (int row = 0; row < mazeArray.length; row++) {
			for (int column = 0; column < mazeArray[row].length; column++) {
				remove(mazeArray[row][column]);
				mazeArray[row][column] = null;
			}
		}

		// Load the board again
		loadBoard();

		// Start the game
		gameTimer.start();
		pacMan.setDirection(1);
	}

	// This method determine what pacman do when key is released
	private void death() {

		// display the skull when pacman dies
		mazeArray[pacMan.getRow()][pacMan.getColumn()].setIcon(Icons.SKULL);

		// set pacman as dead
		pacMan.setDead(true);

		// play the death sound
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(new File("sounds//sounds//killed.wav")));
			clip.start();
		} catch (Exception exc) {
			exc.printStackTrace(System.out);
		}

		// Change the number of lives
		PacManGUI.heartArray[lives].setIcon(null);

		// give user time to react
		timer.schedule(new delayTimer(), 1500);

		// Give user time to react for their second life
		if (lives == 1)
			timer.schedule(new delayTimer2(), 1500);

		// Check if user ate the bonus life
		if (bonus == true) {
			timer.schedule(new delayTimer3(), 1500);
		}
		// Check if all of the timer is finished
		if ((delay == true && lives == 2 && delay2 == false && bonus == false)
				|| (delay2 == true && delay == true && lives == 0 && bonus == false)
				|| (delay2 == true && delay == true && lives == 1 && bonus == false)
				|| (delay == true && delay2 == true && delay3 == true && bonus == true)
				|| (delay == true && delay3 == true && bonus == true && lives == 2 && delay2 == false)) {

			// check if user have anymore life
			if (lives <= 0) {
				// Set Pac-Man as dead
				pacMan.setDead(true);

				// Show a "GAME OVER!" message dialog
				int option = JOptionPane.showConfirmDialog(this, "GAME OVER! Would you like to play again" + "?",
						"GAME OVER", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, pacMan.getIcon());

				// reset the game if user say yes
				if (option == 0)
					resetGame();
				else {

					// close the window and update the leaderboard if user say no
					modifyLeaderboard();
					Window window = SwingUtilities.getWindowAncestor(this);
					window.dispose();
					System.exit(0);
				}

				// check if user have any more lives left
			} else if (lives > 0) {

				// reset the board
				resetBoard();

				// reset delay variable
				delay = false;
				delay2 = false;

				// set pacman back alive
				pacMan.setDead(false);

			}
		}
	}

	// the method reset the board for user to play in their next life
	private void resetBoard() {

		// setup ghost location
		for (Mover ghost : ghostArray) {
			mazeArray[ghost.getRow()][ghost.getColumn()].setIcon(Icons.BLANK);
		}
		ghostArray[0].setColumn(gCol1);
		ghostArray[0].setRow(gRow1);
		ghostArray[1].setColumn(gCol2);
		ghostArray[1].setRow(gRow2);
		ghostArray[2].setColumn(gCol3);
		ghostArray[2].setRow(gRow3);
		mazeArray[gRow1][gCol1].setIcon(Icons.GHOST[0]);
		mazeArray[gRow2][gCol2].setIcon(Icons.GHOST[1]);
		mazeArray[gRow3][gCol3].setIcon(Icons.GHOST[2]);

		// set the icon as blank when Pacman dies
		mazeArray[pacMan.getRow()][pacMan.getColumn()].setIcon(Icons.BLANK);

		// setup pacman location
		pacMan.setColumn(pCol);
		pacMan.setRow(pRow);
		pacMan.setDirection(1);
		mazeArray[pRow][pCol].setIcon(Icons.PACMAN[1]);

		// Change variable value
		lives--;
	}

	// Display the death animation of pacman
	class delayTimer extends TimerTask {

		// set the timer task
		public void run() {

			// set delay as true
			delay = true;

		}
	}

	// Display the death animation of pacman for second life
	class delayTimer2 extends TimerTask {

		// set the timer task
		public void run() {

			// set delay2 as true
			delay2 = true;
		}
	}

	// Display the death animation of pacman for bonus life
	class delayTimer3 extends TimerTask {

		// set the timer task
		public void run() {

			// set delay2 as true
			delay3 = true;
		}
	}

	// Make character move
	private void performMove(Mover mover) {

		// Variable
		Cell currentCell = mazeArray[mover.getRow()][mover.getColumn()];
		Cell nextCell = mazeArray[mover.getNextRow()][mover.getNextColumn()];

		// Change location of pacman when it enters the door
		if (mover.getColumn() == 1) {
			mover.setColumn(25);
			mazeArray[12][1].setIcon(Icons.DOOR);
		} else if (mover.getColumn() == 25) {
			mover.setColumn(1);
			mazeArray[12][15].setIcon(Icons.DOOR);
		}

		// Check if pacman is running to wall
		if (nextCell.getIcon() != Icons.WALL) {

			// Check if other stand on food
			if (mover != pacMan && currentCell.getItem() == 'F')
				currentCell.setIcon(Icons.FOOD);

			// Prevent other mover from eating the strawberry
			else if (mover != pacMan && currentCell.getItem() == 'S')
				currentCell.setIcon(Icons.STRAWBERRY);

			// Change the icon back to gate
			else if (mover != pacMan && currentCell.getItem() == 'G')
				currentCell.setIcon(Icons.GATE);

			// make sure ghost can't eat heart
			else if (mover != pacMan && currentCell.getItem() == 'H')
				currentCell.setIcon(Icons.HEART);

			// change the icon if pacman is on the food
			else {

				currentCell.setIcon(Icons.BLANK);
			}
			// move the mover and make sure ghost don't overlap each others
			if (nextCell.getItem() != 'G' || mover.getdRow() == -1) {
				if (nextCell.getIcon() != Icons.GHOST[0] && nextCell.getIcon() != Icons.GHOST[1]
						&& nextCell.getIcon() != Icons.GHOST[2] && mover != pacMan)
					mover.move();
				else if (mover == pacMan)
					mover.move();
			}
			currentCell = mazeArray[mover.getRow()][mover.getColumn()];

			// Change Icon
			currentCell.setIcon(mover.getIcon());

			// Check for death
			if (collided()) {
				death();
			} else {

				// Increment the score if pacman ate the food
				if (mover == pacMan && nextCell.getItem() == 'F') {

					// Increment the score
					score++;

					currentCell.setItem('E');
					if (score > hiscore) {
						hiscore = score;
						PacManGUI.hiScoreText.setText("YOUR BEST:" + hiscore);
					}
					PacManGUI.scoreText.setText("SCORE:" + score);

				}
				// Increment score for strawberry
				if (mover == pacMan && nextCell.getItem() == 'S') {

					// play the sound when pacman eat fruit
					try {
						Clip clip = AudioSystem.getClip();
						clip.open(AudioSystem.getAudioInputStream(new File("sounds//sounds//fruiteat.wav")));
						clip.start();
					} catch (Exception exc) {
						exc.printStackTrace(System.out);
					}

					// Increment the score
					score += 5;
					currentCell.setItem('E');
					if (score > hiscore) {
						hiscore = score;
						PacManGUI.hiScoreText.setText("YOUR BEST:" + hiscore);
					}
					PacManGUI.scoreText.setText("SCORE:" + score);

				}

				// Give user a bonus life only if they lost a life
				if (mover == pacMan && nextCell.getItem() == 'H') {

					// play sound if user eat bonus life
					try {
						Clip clip = AudioSystem.getClip();
						clip.open(AudioSystem.getAudioInputStream(new File("sounds//sounds//GHOSTEATEN.wav")));
						clip.start();
					} catch (Exception exc) {
						exc.printStackTrace(System.out);
					}

					// Check if user lost life
					if (lives < 2) {

						// increment life and change bonus value to true
						lives++;
						bonus = true;
						PacManGUI.heartArray[lives].setIcon(Icons.heart);
					}
				}

				// Display message when game is over
				if (score == pellets) {
					gameTimer.stop();
					int option = JOptionPane.showConfirmDialog(this,
							"You cleared the board! Would you like to play again?", "Congratulation!",
							JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, pacMan.getIcon());
					
					//reset game if user say yes
					if (option == 0)
						resetGame();
					
					//do following if user say no
					else {
						
						//update leaderboard
						modifyLeaderboard();

						// Close the current window
						Window window = SwingUtilities.getWindowAncestor(this);
						window.setVisible(false);
						window.dispose();

						// go back to the title frame
						new PacManTitleFrame();
					}

				}
			}

		}

	}

	// This method modifies the leaderboard file by adding a new entry with the
	// username and hiscore.
	// It reads the existing lines from the "Leaderboard.csv" file, appends a new
	// entry, and writes the updated lines back.

	private void modifyLeaderboard() {
		// Create a list to store the modified lines of the leaderboard file
		List<String> newLines = new ArrayList<>();
		String trimmedUsername = username == null ? "" : username.trim();
		String recordedName = trimmedUsername.isEmpty() ? "Player" : trimmedUsername;
		java.nio.file.Path leaderboardPath = Paths.get("Leaderboard.csv");

		try {
			// Read all existing lines from the leaderboard file when it already exists.
			if (Files.exists(leaderboardPath)) {
				for (String line : Files.readAllLines(leaderboardPath, StandardCharsets.UTF_8)) {
					newLines.add(line);
				}
			}
		} catch (IOException e) {
			// Handle and log any IOException that may occur during file reading
			e.printStackTrace();
		}

		// Add a new entry with username and hiscore to the list
		newLines.add(recordedName + "," + hiscore);

		try {
			// Write the modified lines back to the leaderboard file
			Files.write(leaderboardPath, newLines, StandardCharsets.UTF_8);
		} catch (IOException e) {
			// Handle and log any IOException that may occur during file writing
			e.printStackTrace();
		}
	}

	@Override
	
	//this method is not used
	public void keyReleased(KeyEvent e) {

	}
}
