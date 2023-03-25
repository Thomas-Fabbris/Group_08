package main;


import gui.BoardWindow;

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
		BoardWindow board_frame = new BoardWindow("MyShelfie"); //Creates game window
		
		Board board = board_frame.getBoard(); //Gets a reference to the board
//		board.addTile(TileType.randomType(), 1, 4);
//		board.addTile(TileType.randomType(), 1, 5);
//		board.addTile(TileType.randomType(), 5, 9);
//		board.addTile(TileType.randomType(), 9, 6);
		
		System.out.println(board.getTileType(4, 4));
		board.setTileVisible(4, 4, false);
		board.setTile(4, 4, TileType.GAMES);
//		System.out.println(board.tileAt(4, 4));
		board_frame.setVisible(true); //Sets the game window to visible
	}

}
