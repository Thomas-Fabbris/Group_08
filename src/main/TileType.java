package main;

import java.util.Random;

public enum TileType {
	BOOKS,
	CATS,
	FRAMES,
	GAMES,
	PLANTS,
	TROPHIES,
	POINTS,
	GAME_END;
	
	private static TileType[] types = {BOOKS, CATS, FRAMES, GAMES, PLANTS, TROPHIES};
	
	public static TileType randomType() {
		Random rd = new Random();
		return types[rd.nextInt(6)];
	}
}
