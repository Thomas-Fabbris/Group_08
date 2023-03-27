package main;

import sharedgamearea.Board;
import sharedgamearea.CommonObjectiveCard;

public class Game {
	
	public void StartGame() {
		
		for (int i = 0; i < Player.getNumberOfPlayers(); i++) {
			System.out.println("Player: " + Player.players[i].getName() + " " + Player.players[i].id);
		}
		
		Board board = new Board(); //Gets a reference to the board
		CommonObjectiveCard card1 = new CommonObjectiveCard(3);
		CommonObjectiveCard card2 = new CommonObjectiveCard(5);
		
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		board.hideAllTiles();
		board.setTileType(1, 4, TileType.GAMES);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		board.refill();
		System.out.println(board.getTileType(1, 4));
	}
}
