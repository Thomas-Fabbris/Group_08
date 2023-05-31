package controller;
/**
 * The {@code GameState} enum lists all of the possible game states, or rather
 * RUNNING,
 * ENDED,
 * LAST_TURN, set when a player has received the {@link GameEndTile}
 * 
 *
 */
public enum GameState {
	RUNNING,
	LAST_TURN, // The game is set do LAST_TURN when a player has received the gameEndTile
	ENDED
}
