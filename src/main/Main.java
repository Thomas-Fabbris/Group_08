package main;


import gui.SharedGameAreaWindow;
import sharedgamearea.Board;
import sharedgamearea.CommonObjectiveCard;

public class Main {

	public static void main(String[] args) {
		Board board = new Board(); //Gets a reference to the board
		
		CommonObjectiveCard card1 = new CommonObjectiveCard(3);
		CommonObjectiveCard card2 = new CommonObjectiveCard(5);
		
		SharedGameAreaWindow.getInstance().getCards().addCard(card1);
		SharedGameAreaWindow.getInstance().getCards().addCard(card2);
		
		for (int i = 0; i < Player.getNumberOfPlayers(); i++) {
			System.out.println(Player.players[i].getName() + " - id: " + Player.players[i].id);
		}
		
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
