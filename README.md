# Java Pac-Man

A desktop Pac-Man style game built in Java Swing. The project includes animated sprites, sound effects, score tracking, power-ups, bonus items, lives, and a local leaderboard.

## Features

- Swing-based game window with title screen and in-game HUD
- Arrow-key movement with real-time ghost movement and collisions
- Pellets, bonus fruit, and multiple power-up types
- Lives system and score tracking
- Local leaderboard saved to `Leaderboard.csv` on the machine where the game is played

## Requirements

- Java 11 or newer
- A desktop environment that can run Java Swing applications

## Run

Compile the project:

```bash
javac -d bin src/*.java
```

Start the game:

```bash
java -cp bin PacManGame
```

## Controls

- Press any key once to start the round
- Use the arrow keys to move Pac-Man

## Project Layout

- `src/` contains the Java source files
- `images/` contains sprite and UI image assets
- `sounds/` contains sound effects and music
- `maze.txt` defines the board layout

## Notes

- `Leaderboard.csv` is intentionally gitignored so player names and scores stay local.
- If no leaderboard file exists yet, the game creates it automatically after a score is recorded.
- Review the licensing for bundled fonts, images, and audio before wider redistribution.
