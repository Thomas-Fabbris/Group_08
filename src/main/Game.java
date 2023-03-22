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
		
		board_frame.setVisible(true); //Sets the game window to visible
	}

}
