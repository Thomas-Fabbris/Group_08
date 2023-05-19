package controller;

public enum GameState {
	RUNNING,
	LAST_TURN, // The game is set do LAST_TURN when a player has received the gameEndTile
	ENDED
}
