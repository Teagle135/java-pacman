// Builds the title screen and menu actions.

//import
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

//This is the titleframe class
@SuppressWarnings("serial")
public class PacManTitleFrame extends JFrame implements ActionListener {

	// Create gui elements
	private JLabel title = new JLabel(new ImageIcon(new ImageIcon("images//images//PacmanTitle.png").getImage()
			.getScaledInstance(600, 150, Image.SCALE_SMOOTH)));
	private JLabel PacmanGhost = new JLabel(new ImageIcon(new ImageIcon("images//images//PacmanGhost.gif").getImage()
			.getScaledInstance(504, 78, Image.SCALE_DEFAULT)));
	private JPanel buttonPanel = new JPanel();
	private JButton start = new JButton("Start Game");
	private JButton leaderboard = new JButton("Leaderboard");
	private JButton help = new JButton("Help");

	// Create the player array
	ArrayList<Player> player = new ArrayList<Player>();

	// Font
	Font pixelFontBold;
	Font pixelFontNorm;

	// Create the leaderboard string
	public static String leaderStr = "";

	//constructor method
	public PacManTitleFrame() {

		//play the background music
		try
	    {
	        Clip clip = AudioSystem.getClip();
	        clip.open(AudioSystem.getAudioInputStream(new File("sounds//sounds//Pac-ManFever.wav")));
	        clip.start();
	    }
	    catch (Exception exc)
	    {
	        exc.printStackTrace(System.out);
	    }
		
		//Read the file
		new FileLeaderboard(player);
		
		// setup the fonts
		try {

			// Load a custom font

			pixelFontBold = Font.createFont(Font.TRUETYPE_FONT, new File("pixelFont.ttf"));

			pixelFontBold = pixelFontBold.deriveFont(Font.BOLD, 75);

			pixelFontNorm = Font.createFont(Font.TRUETYPE_FONT, new File("pixelFont.ttf"));

			pixelFontNorm = pixelFontNorm.deriveFont(Font.BOLD, 28);

		} catch (Exception e) {
			e.printStackTrace();

		}
		// Setup the Frame
		setSize(650, 750);
		setTitle("Pac-Man Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		getContentPane().setBackground(Color.BLACK);

		// setup the title label
		title.setBounds(20, 50, 600, 200);

		// setup the pacman chase ghost gif
		PacmanGhost.setBounds(20, 210, 600, 150);

		// setup the button panel
		buttonPanel.setBounds(120, 350, 400, 300);
		buttonPanel.setBackground(Color.black);
		buttonPanel.setLayout(null);

		// setup the buttons
		start.setBounds(5, 5, 390, 90);
		start.setFont(pixelFontNorm);
		start.setForeground(Color.white);
		start.setBackground(Color.BLACK);
		start.setBorder(BorderFactory.createLineBorder(Color.white, 5));
		start.addActionListener(this);
		leaderboard.setBounds(5, 100, 390, 90);
		leaderboard.setFont(pixelFontNorm);
		leaderboard.setForeground(Color.white);
		leaderboard.setBackground(Color.BLACK);
		leaderboard.setBorder(BorderFactory.createLineBorder(Color.white, 5));
		leaderboard.addActionListener(this);
		help.setBounds(5, 195, 390, 90);
		help.setFont(pixelFontNorm);
		help.setForeground(Color.white);
		help.setBackground(Color.BLACK);
		help.setBorder(BorderFactory.createLineBorder(Color.white, 5));
		help.addActionListener(this);

		// add the elements
		buttonPanel.add(start);
		buttonPanel.add(leaderboard);
		buttonPanel.add(help);
		add(PacmanGhost);
		add(title);
		add(buttonPanel);

		// set visible
		setVisible(true);
	}

	// setup action for the button
	@Override
	public void actionPerformed(ActionEvent e) {

		// start the game when its clicked
		if (e.getSource() == start) {
			setVisible(false);
			new PacManGUI(player);
		}

		// Display the leaderboard if it's clicked
		if (e.getSource() == leaderboard) {
			theLeaderboard();
		}

		// display the instruction of pacman if it is clicked
		if (e.getSource() == help) {

			// display the messagebox of instruction
			JOptionPane.showMessageDialog(this,
					"<html>Objective: Eat all the pellets without being "
					+ "caught by the ghost<br>Eating strawberries or power "
					+ "up pellets may give you bonus abilities. <br>You have "
					+ "three lives before a lost. Good Luck!<html>");
		}
	}

	// set up the leaderboard
	private void theLeaderboard() {

		// reset the leaderstr
		leaderStr = "";

		// Display the players
		for (int index = 0; index < player.size(); index++) {
			leaderStr += (index + 1) + ". " + player.get(index).toString() + "\n";
		}
		// display the messgagebox
		JOptionPane.showMessageDialog(this, leaderStr);

	}
}
