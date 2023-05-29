package model.shared;

import java.util.Random;
/**
 * This Enum Class contains all the different types which a tile can be
 * 
 *
 */
public enum TileType {
	BOOKS, CATS, FRAMES, GAMES, PLANTS, TROPHIES, POINTS, GAME_END, NULL;

	public static final TileType[] values = { BOOKS, CATS, FRAMES, GAMES, PLANTS, TROPHIES };

	/**
	 * Generates a random TileType. This does NOT affect the Pouch. Use
	 * Pouch.getInstrance().extractRandom() if you need to change the number of
	 * tiles stored in the Pouch!
	 * 
	 * @return
	 */
	public static TileType randomType() {
		Random rd = new Random();
		return values[rd.nextInt(6)];
	}
}
