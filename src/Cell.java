// Maps maze characters to display icons.

//import
import javax.swing.*;

//Template Class
@SuppressWarnings("serial")
public class Cell extends JLabel {

	// Fields
	private char item;

	// Constructor Method
	public Cell(char item) {
		super();
		this.item = item;

		// Set the value depending the letter
		setCodeIcon();
	}

	// The setCodeIcon method
	private void setCodeIcon() {

		// Setup the Item
		if (item == 'P')
			setIcon(Icons.PACMAN[0]);
		else if (item == '0')
			setIcon(Icons.GHOST[0]);
		else if (item == '1')
			setIcon(Icons.GHOST[1]);
		else if (item == '2')
			setIcon(Icons.GHOST[2]);
		else if (item == 'W')
			setIcon(Icons.WALL);
		else if (item == 'F')
			setIcon(Icons.FOOD);
		else if (item == 'D')
			setIcon(Icons.DOOR);
		else if (item == 'G')
			setIcon(Icons.GATE);
		else if (item == 'S')
			setIcon(Icons.STRAWBERRY);
		else if (item == 'H')
			setIcon(Icons.HEART);

	}

	// Getters and Setters
	public char getItem() {
		return item;
	}

	public void setItem(char item) {
		this.item = item;
	}

}
