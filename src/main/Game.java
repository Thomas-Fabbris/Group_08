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
	
		Board board = Board.GetInstance(); //Instantiate the board (Board is a singleton)
		board_frame.getContentPane().add(board); //Adds the board to the game window
		
		Tile test = board.tileAt(3, 3); //Gets the tile at row 3 column 3
		System.out.println("Row: " + test.getRow() + " Column: " + test.getColumn() + " Type: " + test.type);
		System.out.println(board.isValidTilePosition(3, 3));
		
		board_frame.setVisible(true); //Sets the game window to visible
	}

}
