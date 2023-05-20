package model.shared;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import model.commongamearea.Pouch;

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
	
	public static TileType[] types = {BOOKS, CATS, FRAMES, GAMES, PLANTS, TROPHIES};
	
	public static TileType randomType() {
		int i = 0;
		do {
		Random rd = new Random();
		i = rd.nextInt(6);
		}while(Pouch.extract(types[i]));
		return types[i];
	}
	
//	public static TileType randomType() {
//		Random rd = new Random();
//		return types[rd.nextInt(6)];
//	}
}
