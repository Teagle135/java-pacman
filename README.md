# Java Pac-Man

A desktop Pac-Man style game built in Java Swing. The project includes animated sprites, sound effects, score tracking, power-ups, bonus items, lives, and a local leaderboard.

## Overview

This project recreates a Pac-Man style arcade game as a standalone Java desktop application. It uses Swing for rendering and UI, loads assets from the repository, and stores high scores locally on the machine where the game is played.

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

Run the commands from the repository root so the game can find `images/`, `sounds/`, `maze.txt`, and `pixelFont.ttf`.

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

## Local Data

- High scores are stored in `Leaderboard.csv` in the project root on the local machine only.
- If `Leaderboard.csv` does not exist, the game treats the leaderboard as empty and creates the file the next time a score is saved.
- Game progress during a run is not persisted beyond the current session.

## Project Layout

- `src/` contains the Java source files
- `images/` contains sprite and UI image assets
- `sounds/` contains sound effects and music
- `maze.txt` defines the board layout

## Publishing Notes

- `Leaderboard.csv` is intentionally gitignored so player names and scores stay local.
- Review the licensing for bundled fonts, images, and audio before wider redistribution.
- If you want the repository to be open source, add a `LICENSE` file before publishing. Without one, the default legal position is "all rights reserved."
