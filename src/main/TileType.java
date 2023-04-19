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
	GAME_END,
	NULL;
	
	private static TileType[] types = {BOOKS, CATS, FRAMES, GAMES, PLANTS, TROPHIES};
	
	public static TileType randomType() {
//		do {
		Random rd = new Random();
//		int i =rd.nextInt(6);		
//		}while(Pouch.isAvailable(i))
		return types[rd.nextInt(6)];
	}
}
