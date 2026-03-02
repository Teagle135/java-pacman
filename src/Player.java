// Stores leaderboard player data.

//Player object
public class Player {

	private String name;
	private int Score;

	// getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return Score;
	}

	public void setScore(int score) {
		Score = score;
	}

	// constructor
	public Player(String name, int score) {
		super();
		this.name = name;
		Score = score;
	}

	@Override
	public String toString() {
		return "Player: " + name + ", Score: " + Score;
	}

}
