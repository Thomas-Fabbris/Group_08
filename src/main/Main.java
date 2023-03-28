package main;


import sharedgamearea.Board;
import sharedgamearea.CommonObjectiveCard;

public class Main {

	public static void main(String[] args) {
//		MainMenu main_menu = new MainMenu();
		
//		Thread main_menu_thread = new Thread(new MainMenu(Thread.currentThread()));
//		MainMenuWindow window = new MainMenuWindow();
		
		Board board = new Board(); //Gets a reference to the board
		CommonObjectiveCard card1 = new CommonObjectiveCard(3);
		CommonObjectiveCard card2 = new CommonObjectiveCard(5);
		
		for (int i = 0; i < Player.getNumberOfPlayers(); i++) {
			System.out.println(Player.players[i].getName() + " - " + Player.players[i].id);
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
