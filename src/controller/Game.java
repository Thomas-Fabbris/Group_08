package controller;

import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import model.commongamearea.Board;
import model.commongamearea.CommonObjectiveCard;
import model.personalgamearea.Bookshelf;
import model.shared.Player;
import model.shared.TileType;
import view.personalgamearea.PersonalGameAreaWindow;
import view.sharedgamearea.MainMenuWindow;
import view.sharedgamearea.SharedGameAreaWindow;

public class Game {

	ArrayList<Player> players = new ArrayList<Player>();
	public Bookshelf[] playerShelves = new Bookshelf[players.size()];

	//shared game area stuff
	Board board;
	CommonObjectiveCard card1 = new CommonObjectiveCard(4);
	CommonObjectiveCard card2 = new CommonObjectiveCard(6);
	
	public Game(MainMenuWindow main_menu) {
		this.board = new Board();
		this.players = main_menu.getPlayers();
		main_menu.dispose();

		//Create the common objective cards container and add it to the window
		JPanel cards = new JPanel();
		cards.setLayout(new FlowLayout(0, 10, 10));
		cards.setSize(SharedGameAreaWindow.getInstance().getSize().width/2, SharedGameAreaWindow.getInstance().getSize().height*1/3);
		cards.setOpaque(false);
		cards.add(card1.getLabel());
		cards.add(card2.getLabel());
		
		SharedGameAreaWindow.getInstance().add(board.getLabel());
		SharedGameAreaWindow.getInstance().add(cards);
		
		SharedGameAreaWindow.getInstance().toFront(); //I'm not sure why, but the window goes to background when it is opened
		PersonalGameAreaWindow.getInstance().toFront();
		
		Start();
	}
	
	public void Start() {
		
		//Prints player names and ids
		for (int i = 0; i < players.size(); i++) {
			System.out.println(players.get(i).getName() + " - id: " + players.get(i).id);
//			System.out.println(players.get(i).bookshelf.toString());
		}
		
		PersonalGameAreaWindow.getInstance().setCurrentPlayer(players.get(0));
		
		players.get(0).bookshelf.setTileType(3, 2, TileType.FRAMES);

		players.get(0).bookshelf.getTile(3, 2).tileUp().setType(TileType.GAMES);
		players.get(0).bookshelf.getTile(3, 2).tileDown().setType(TileType.CATS);
		players.get(0).bookshelf.getTile(3, 2).tileLeft().setType(TileType.PLANTS);
		players.get(0).bookshelf.getTile(3, 2).tileRigth().setType(TileType.TROPHIES);
		
		players.get(0).bookshelf.setTileType(0, 1, TileType.BOOKS);
		
//		board.hideTile(2, 4);
//		System.out.println("Tile @ 2, 4 before refill: " + board.getTileType(2, 4));
//		board.refill();
//		System.out.println("Tile @ 2, 4 after refill: " + board.getTileType(2, 4));
//		
//		System.out.println("Tile below that is: " + board.getTile(2, 4).tileDown().getType());
		
	}
}
