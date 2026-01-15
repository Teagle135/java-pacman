/*Header
Name: Tony Ren
Date: 2023-12-15
Course Code: ICS3U1-08 Mr.Fernandes
Title: Icons.java
Description: This class setup all the icons for board to use
Major Skills: objects and methods, arrays and import.
*/

//import 
import java.awt.Image;

import javax.swing.ImageIcon;

//Template class for icon
public class Icons {

	// Fields
	public static final ImageIcon WALL = new ImageIcon("images//images//Wall.bmp");
	public static final ImageIcon FOOD = new ImageIcon("images//images//Food.bmp");
	public static final ImageIcon BLANK = new ImageIcon("images//images//Black.bmp");
	public static final ImageIcon DOOR = new ImageIcon("images//images//Black.bmp");
	public static final ImageIcon GATE = new ImageIcon("images//images//Door.png");
	public static final ImageIcon SKULL = new ImageIcon("images//images//Pacmandeath.gif");
	public static final ImageIcon SCORE = new ImageIcon("images//images//Score.png");
	public static final ImageIcon STRAWBERRY = new ImageIcon(
			new ImageIcon("images//images//Strawberry.png").getImage().getScaledInstance(22, 22, Image.SCALE_DEFAULT));
	public static final ImageIcon HEART = new ImageIcon(
			new ImageIcon("images//images//BONUSHEART.png").getImage().getScaledInstance(22, 22, Image.SCALE_DEFAULT));

	// Heart Image
	public static final ImageIcon heart = new ImageIcon(
			new ImageIcon("images//images//heart.png").getImage().getScaledInstance(60, 30, Image.SCALE_DEFAULT));
	// PacMan image
	public static final ImageIcon[] PACMAN = { new ImageIcon("images//images//PacMan0.gif"),
			new ImageIcon("images//images//PacMan1.gif"), new ImageIcon("images//images//PacMan2.gif"),
			new ImageIcon("images//images//PacMan3.gif"),

	};

	// Ghost Image
	public static final ImageIcon[] GHOST = {
			new ImageIcon(new ImageIcon("images//images//GreenGhost.gif").getImage().getScaledInstance(22, 22,
					Image.SCALE_DEFAULT)),
			new ImageIcon(new ImageIcon("images//images//PinkGhost.gif").getImage().getScaledInstance(22, 22,
					Image.SCALE_DEFAULT)),
			new ImageIcon(new ImageIcon("images//images//RedGhost.gif").getImage().getScaledInstance(22, 22,
					Image.SCALE_DEFAULT)) };

}