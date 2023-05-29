package model.commongamearea;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import model.shared.TileType;
/**
 *The class {@code Board} models the logic part of the playable board
 * 
 *
 */
public class Board {
	public final static int BOARD_LENGTH = 11;

	private BoardTile[][] tiles = new BoardTile[BOARD_LENGTH][BOARD_LENGTH];
	private boolean[][] validPositions = new boolean[BOARD_LENGTH][BOARD_LENGTH];
	private final String VALID_POSITIONS_FILE_PATH = "./Assets/valid_board_positions_Xplayers.txt";
/**
 * The constructor defines a new instance of the class {@code Board}
 * @param numberOfPlayers the number of the players
 */
	public Board(int numberOfPlayers) {
		//condizione di uscita dal programma
		if (numberOfPlayers < 2) {
			System.exit(0);
		}
		validPositions = readValidBoardPositionsFile(selectValidPositionsFile(numberOfPlayers));
		this.initTiles();
	}

	public boolean[][] getValidPositions() {
		return this.validPositions;
	}

	public BoardTile[][] getBoardTiles() {
		return this.tiles;
	}

	/***
	 * The method {@code setTileType} adds a tile
	 * 
	 * @param tile_type type of tile to add
	 * @param row       1 to 9
	 * @param column    1 to 9
	 * @return reference to the new tile
	 */
	public void setTileType(int row, int column, TileType tile_type) {

		if (!isValidPosition(row, column))
			throw new InvalidBoardPositionException(row, column);

		tiles[row][column].setType(tile_type);
		tiles[row][column].enable();
	}

	/***
	 * The method {@code getTileType}Gets the TileType of the tile at the specified position
	 * 
	 * @param row
	 * @param column
	 * @return the type of the tile at specified row / column
	 */
	public TileType getTileType(int row, int column) {
		if (!isValidPosition(row, column))
			throw new InvalidBoardPositionException(row, column);

		return tiles[row][column].getType();
	}

	/***
	 * The method {@code getTile} gets the tile at the specified position
	 * 
	 * @param row
	 * @param column
	 * @return the tile at specified row / column
	 */
	public BoardTile getTile(int row, int column) {
		try {
			return tiles[row][column];
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}

	/**
	 * The method {@code isValidPosition} returns whether the specified row/col position is valid
	 * @param row
	 * @param column
	 * @return Returns whether the specified row/col position is valid
	 */
	public boolean isValidPosition(int row, int column) {
		return validPositions[row][column];
	}

	/**
	 * The method {@code hideTile} hides the tile on the board (will not affect TileType)
	 * 
	 * @param row
	 * @param column
	 */
	public void hideTile(int row, int column) {
		if (!isValidPosition(row, column))
			throw new InvalidBoardPositionException(row, column);

		tiles[row][column].disable();
	}

	public boolean isTileVisible(int row, int column) {
		if (!isValidPosition(row, column))
			throw new InvalidBoardPositionException(row, column);

		return tiles[row][column].isActive();
	}

	private File selectValidPositionsFile(int number_of_players) {
		Integer num = Integer.valueOf(number_of_players);
		String path = VALID_POSITIONS_FILE_PATH.replaceAll("X", num.toString());
		return new File(path);
	}

	/**
	 * The method {@code readValidBoardPositionsFile} fills the valid_positions array according to the contents of the file
	 * 
	 * @param file with positions: 1 for valid, 0 for non valid
	 * @return
	 */
	private boolean[][] readValidBoardPositionsFile(File file) {
		boolean[][] positions = new boolean[BOARD_LENGTH][BOARD_LENGTH];
		Scanner scanner = null;

		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		String line;
		char c;

		for (int i = 0; i < BOARD_LENGTH; i++) {

			line = scanner.nextLine().replaceAll("\\s+", "");

			for (int j = 0; j < BOARD_LENGTH; j++) {
				c = line.charAt(j);
				if (c == '1')
					positions[i][j] = true;
				else
					positions[i][j] = false;
			}
		}

		return positions;
	}

	/***
	 * The method {@code initTiles} fills every cell on the board with a tile (non valid positions are set to
	 * TileType.NULL)
	 */
	private void initTiles() {
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles.length; j++) {
				if (validPositions[i][j])
					tiles[i][j] = new BoardTile(Pouch.getInstance().extractRandom(), i, j, this);
				else {
					tiles[i][j] = new BoardTile(TileType.NULL, i, j, this);
					tiles[i][j].disable();
				}
			}
		}
	}

	/**
	 * The method {@code getCurrentBlockedTiles} returns an array of booleans where each [true] represents a tile that can be
	 * picked up and each [false] represents a tile that cannot be picked up. This
	 * is used to decide if a tile is blocked by other tiles at the start of each
	 * turn.
	 * 
	 * @return
	 */
	public boolean[][] getCurrentBlockedTiles() {
		boolean[][] currentBoardState = new boolean[Board.BOARD_LENGTH][Board.BOARD_LENGTH];

		for (int row = 0; row < currentBoardState.length; row++) {
			for (int col = 0; col < currentBoardState.length; col++) {
				if (tiles[row][col].canBePickedUp()) {
					currentBoardState[row][col] = true;
				} else {
					currentBoardState[row][col] = false;
				}
			}
		}

		return currentBoardState;
	}

	/**
	 * The method {@code refill} takes tile from the pouch and puts them into the board
	 */
	public void refill() {
		for (int row = 0; row < Board.BOARD_LENGTH; row++) {
			for (int col = 0; col < Board.BOARD_LENGTH; col++) {
				if (isValidPosition(row, col) && !tiles[row][col].isActive()) {
					this.setTileType(row, col, Pouch.getInstance().extractRandom());
					tiles[row][col].enable();
				}
			}
		}
	}
/**
 * The method {@code isFull} checks if the the board is completely full or not
 * @return true if the board is full, false if not
 */
	public boolean isFull() {
		for (int row = 0; row < Board.BOARD_LENGTH; row++) {
			for (int col = 0; col < Board.BOARD_LENGTH; col++) {
				if (tiles[row][col].isActive() && tiles[row][col].canBePickedUp()) {
					return false;
				}
			}
		}
		return true;
	}
/**
 * The method {@code hideAllTiles} disables all the tiles
 */
	public void hideAllTiles() {
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles.length; j++) {
				tiles[i][j].disable();
			}
		}
	}

	public void printTiles() {
		for (int row = 0; row < Board.BOARD_LENGTH; row++) {
			for (int col = 0; col < Board.BOARD_LENGTH; col++) {
				if(tiles[row][col].getType() == TileType.NULL) {
					System.out.print("* ");
				} else {					
					System.out.print(tiles[row][col].toString().charAt(0) + " ");
				}
			}
			System.out.println();
		}
	}
}