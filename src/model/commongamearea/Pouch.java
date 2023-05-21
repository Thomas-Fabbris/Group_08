package model.commongamearea;

import java.util.HashMap;
import java.util.Map;

import model.shared.TileType;

// This class is a Singleton i.e. it can only be instantiated once
public class Pouch {

	private static Pouch instance = null;

	private final int initialTileCount = 22;
	private final int totalNumberOfTiles;
	private Map<TileType, Integer> tileCounter = new HashMap<TileType, Integer>();

	private Pouch() {
		tileCounter.put(TileType.BOOKS, initialTileCount);
		tileCounter.put(TileType.CATS, initialTileCount);
		tileCounter.put(TileType.FRAMES, initialTileCount);
		tileCounter.put(TileType.GAMES, initialTileCount);
		tileCounter.put(TileType.PLANTS, initialTileCount);
		tileCounter.put(TileType.TROPHIES, initialTileCount);

		this.totalNumberOfTiles = initialTileCount * tileCounter.size();
	}

	public static Pouch getInstance() {
		if (instance == null)
			instance = new Pouch();

		return instance;
	}

	public TileType extractRandom() {

		TileType tileToExtract = TileType.randomType();
		int iteration = 0;

		// Select a new tile if the selected one is not available
		while (!isTileAvailable(tileToExtract)) {
			tileToExtract = TileType.randomType();

			iteration++;
			if (iteration >= totalNumberOfTiles) {
				throw new IllegalStateException("The pouch has been completely emptied!");
			}
		}

		tileCounter.put(tileToExtract, tileCounter.get(tileToExtract) - 1); // decrease the value by one
		return tileToExtract;
	}

	/**
	 * Returns whether there are still tiles of the specified type inside the pouch
	 * 
	 * @param type
	 * @return
	 */
	private boolean isTileAvailable(TileType type) {
		return tileCounter.get(type) > 0;
	}

	/**
	 * Adds the tile back to the pouch (increases the count by one)
	 * 
	 * @param type
	 */
//	public void add(TileType type) {
//		tileCounter.put(type, tileCounter.get(type) + 1);
//	}
}
