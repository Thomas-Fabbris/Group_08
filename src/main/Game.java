package main;

public class Game {

	public static void main(String[] args) {

		IdGenerator idgenerator = new IdGenerator();
		Player player1 = new Player("a", "a", idgenerator);
		Player player2 = new Player("b", "b", idgenerator);
		
		System.out.println(player1.id);
		System.out.println(player2.id);
		TileType tile = TileType.BOOKS;
		System.out.println(tile);
	}

}
