/*Header
Name: Tony Ren
Date: 2023-12-15
Course Code: ICS3U1-08 Mr.Fernandes
Title: Mover.java
Description: This is a template class of mover such as pacman and ghost
Major Skills: import,  if-else statement and object and methods
*/

//import 
import javax.swing.JLabel;

//Mover template class
@SuppressWarnings("serial")
public class Mover extends JLabel{

	// Fields
	private int row;
	private int column;
	private int dRow;
	private int dColumn;
	private boolean isDead;

	// Constructor method
	public Mover(int row, int column) {
		super();
		this.row = row;
		this.column = column;
	}

	// Getters and Setters
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getdRow() {
		return dRow;
	}

	public void setdRow(int dRow) {
		this.dRow = dRow;
	}

	public int getdColumn() {
		return dColumn;
	}

	public void setdColumn(int dColumn) {
		this.dColumn = dColumn;
	}

	public boolean isDead() {
		return isDead;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
		this.setdColumn(0);
		this.setdRow(0);
	}

	// Move the mover
	public void move() {
		
		row += dRow;
		column += dColumn;
	}

	// Set the direction
	public void setDirection(int direction) {

		// intialize the variable
		dRow = 0;
		dColumn = 0;

		// set value
		if (direction == 0)
			dColumn = -1;
		if (direction == 1)
			dRow = -1;
		if (direction == 2)
			dColumn = 1;
		if (direction == 3)
			dRow = 1;
	}

	// Get the direction
	public int getDirection() {

		// get different direction
		if (dRow == 0 && dColumn == -1)
			return 0;
		else if (dRow == -1 && dColumn == 0)
			return 1;
		else if (dRow == 0 && dColumn == 1)
			return 2;
		else
			return 3;
	}

	// get next row
	public int getNextRow() {

		return row + dRow;
	}

	// get next column
	public int getNextColumn() {
		return column + dColumn;
	}
}
