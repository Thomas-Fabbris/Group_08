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
		

		board_frame.setVisible(true); //Sets the game window to visible
		
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
