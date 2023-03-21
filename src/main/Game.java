package main;

import java.awt.Container;

import gui.BoardFrame;
import gui.Board;
import gui.Tile;
import gui.TileType;

public class Game {

	public static void main(String[] args) {

		IdGenerator idgenerator = new IdGenerator();
		Player player1 = new Player("a", "a", idgenerator);
		Player player2 = new Player("b", "b", idgenerator);
		
		System.out.println(player1.id);
		System.out.println(player2.id);
		TileType tile = TileType.BOOKS;
		System.out.println(tile);
		
		//----------------------------------
		System.out.println("Creating JFrame");
		BoardFrame board_frame = new BoardFrame("MyShelfie", 3); //Creates game window
		
		Board board = new Board(board_frame.getSize()); //Creates the board label
		board_frame.add(board); //Adds the board label to the game window
		
		board_frame.setVisible(true); //Sets the game window to visible
	}

}
