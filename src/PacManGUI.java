/*Header
Name: Tony Ren
Date: 2023-12-15
Course Code: ICS3U1-08 Mr.Fernandes
Title: PacManGUI.java
Description: This class display the game frame of the pacman game.
Major Skills: Methods and Objects, if - else statement, for loop, while loop, swing gui element, arrays, arraylist, list.
*/

//Import
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

import javax.swing.*;

//PacManGUI Class - Creates the PacManGUI window
@SuppressWarnings("serial")
public class PacManGUI extends JFrame {

	// Change the board each day
	private Board board = new Board();

	// Font
	Font pixelFontBold;
	Font pixelFontNorm;
	Font pixelFont2;

	// GUI element
	public static JLabel scoreText = new JLabel("SCORE:0");
	public static JLabel hiScoreText = new JLabel("YOUR BEST:0");
	public static JLabel[] heartArray = new JLabel[3];
	public static JPanel heartPanel = new JPanel();
	public static JLabel gameBest = new JLabel("GEAM BEST");

	// Create a label to tell user to click once before starting
	public static JLabel warn = new JLabel("Click Any Key To Start");

	// Constructor Method
	public PacManGUI(ArrayList<Player> player) {

		// setup the fonts
		try {

			// Load a custom font

			pixelFontBold = Font.createFont(Font.TRUETYPE_FONT, new File("pixelFont.ttf"));

			pixelFontBold = pixelFontBold.deriveFont(Font.BOLD, 25);

			pixelFontNorm = Font.createFont(Font.TRUETYPE_FONT, new File("pixelFont.ttf"));

			pixelFontNorm = pixelFontNorm.deriveFont(Font.BOLD, 23);
			
			pixelFont2 = Font.createFont(Font.TRUETYPE_FONT, new File("pixelFont.ttf"));

			pixelFont2 = pixelFontBold.deriveFont(Font.BOLD, 16);

		} catch (Exception e) {

			e.printStackTrace();

		}
		// Setup the Frame
		setSize(650, 750);
		setTitle("Tony Ren - PacMan Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		getContentPane().setBackground(Color.BLACK);

		// Score Track
		JPanel bottom = new JPanel();
		bottom.setBackground(Color.BLACK);
		bottom.setBounds(18, 650, 600, 100);
		bottom.setLayout(null);
		scoreText.setFont(pixelFontNorm);
		scoreText.setForeground(Color.white);
		scoreText.setBounds(10, 0, 300, 50);
		hiScoreText.setFont(pixelFontNorm);
		hiScoreText.setForeground(Color.white);
		hiScoreText.setBounds(270, 0, 400, 50);

		// set up the warn label
		warn.setFont(pixelFontBold);
		warn.setBounds(56, 295, 600, 100);
		warn.setBackground(Color.black);
		warn.setForeground(Color.white);

		// setup the board
		board.setSize(600, 600);
		board.setBounds(18, 50, 600, 600);
		board.setOpaque(false);

		// setup the heart
		for (int count = 0; count < heartArray.length; count++) {
			heartArray[count] = new JLabel(Icons.heart);
			heartArray[count].setBounds((count) * 60 + 10, 0, 60, 50);
			heartPanel.add(heartArray[count]);

		}
		// setup the all time high score
		gameBest.setBounds(300, 0, 400, 50);
		gameBest.setForeground(Color.white);
		gameBest.setFont(pixelFont2);
		gameBest.setText("<html>HIGHEST SCORE:" + "<br>"+player.get(0).getName() + " " + player.get(0).getScore()+"<html>");
		
		// setup the heartpanel
		heartPanel.setBackground(Color.BLACK);
		heartPanel.setBounds(0, 0, 750, 50);
		heartPanel.setLayout(null);
		heartPanel.setOpaque(false);

		// Add element to the frame
		heartPanel.add(gameBest);
		add(heartPanel);
		add(warn);
		add(board);
		bottom.add(scoreText);
		bottom.add(hiScoreText);
		add(bottom);

		// Make the board listen to action of keys
		addKeyListener(board);

		// set the frame unresizeable
		setResizable(false);

		// Set the frame as visible
		setVisible(true);

	}

}
