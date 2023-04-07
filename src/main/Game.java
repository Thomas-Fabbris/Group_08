package main;

import gui.MainMenuWindow;
import gui.SharedGameAreaWindow;
import sharedgamearea.Board;
import sharedgamearea.CommonObjectiveCard;

public class Game {
	
	Board board;
	Player[] players;
	
	CommonObjectiveCard[] common_objective_cards = { new CommonObjectiveCard(3), new CommonObjectiveCard(5) };
	
	public Game(MainMenuWindow main_menu) {
		this.board = new Board();
		this.players = new Player[main_menu.getNumberOfPlayers()];
		this.players = main_menu.getPlayers();
		main_menu.dispose();

//		SharedGameAreaWindow.getInstance().add(board.getLabel());
		SharedGameAreaWindow.getInstance().getCards().addCard(common_objective_cards[0]);
		SharedGameAreaWindow.getInstance().getCards().addCard(common_objective_cards[1]);
		SharedGameAreaWindow.getInstance().toFront(); //I'm not sure why, but the window goes to background when it is opened
		
		Start();
	}
	
	public void Start() {
		
		for (int i = 0; i < players.length; i++) {
			System.out.println(players[i].getName() + " - id: " + players[i].id);
		}
		
//		board.hideAllTiles();
		board.setTileType(1, 4, TileType.GAMES);
		
//		board.refill();
		System.out.println(board.getTileType(1, 4));
		
	}
}
