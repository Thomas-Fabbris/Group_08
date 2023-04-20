package sharedgamearea;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import gui.sharedgamearea.BoardLabel;
import gui.sharedgamearea.SharedGameAreaWindow;
import main.Player;
import main.TileType;

public class Board {
	private final static int BOARD_LENGTH = 11;
	
	private BoardTile[][] tiles = new BoardTile[BOARD_LENGTH][BOARD_LENGTH];
	private boolean[][] valid_positions = new boolean[BOARD_LENGTH][BOARD_LENGTH];
	private final String VALID_POSITIONS_FILE_PATH = "./Assets/valid_board_positions_Xplayers.txt";
	private BoardLabel label;
	private GameEndTile game_end_tile;
	
	public Board() {
		valid_positions = readValidBoardPositionsFile(selectValidPositionsFile(Player.getNumberOfPlayers()));
		this.label = new BoardLabel(SharedGameAreaWindow.getInstance().getBoardSize());
		this.initTiles();
		
		this.game_end_tile = new GameEndTile(this);
		label.add(game_end_tile.getLabel());
		SharedGameAreaWindow.getInstance().setVisible(true);
	}
	
	public BoardLabel getLabel() {
		return label;
	}
	
	/***
	 * Adds a tile
	 * @param tile_type type of tile to add
	 * @param row 1 to 9
	 * @param column 1 to 9
	 * @return reference to the new tile
	 */
	public void setTileType(int row, int column, TileType tile_type) {
		
		if(!isValidPosition(row, column))
			throw new InvalidBoardPositionException(row, column);			
		
		tiles[row][column].setType(tile_type);
		tiles[row][column].getLabel().setType(tile_type);
		tiles[row][column].setVisible(true);
	}
	
	/***
	 * Gets the TileType of the tile at the specified position
	 * @param row
	 * @param column
	 * @return the type of the tile at specified row / column
	 */
	public TileType getTileType(int row, int column) {
		if(!isValidPosition(row, column))
			throw new InvalidBoardPositionException(row, column);
		
		return tiles[row][column].getType();
	}
	
	
	/***
	 * Gets the tile at the specified position
	 * @param row
	 * @param column
	 * @return the tile at specified row / column
	 */
	public BoardTile getTile(int row, int column) {
		return tiles[row][column];
	}
	
	/**
	 * @param row
	 * @param column
	 * @return Returns whether the specified row/col position is valid
	 */
	public boolean isValidPosition(int row, int column) {
		return valid_positions[row][column];
	}
	
	/**
	 * Hides the tile on the board (will not affect TileType)
	 * @param row
	 * @param column
	 */
	public void hideTile(int row, int column) {
		if(!isValidPosition(row, column))
			throw new InvalidBoardPositionException(row, column);
		
		tiles[row][column].setVisible(false);
	}
	
	/**
	 * Tile becomes visible on the board (will not change TileType)
	 * @param row
	 * @param column
	 */
	public void showTile(int row, int column) {
		if(!isValidPosition(row, column))
			throw new InvalidBoardPositionException(row, column);
		
		tiles[row][column].setVisible(true);
	}
	
	public boolean isTileVisible(int row, int column) {
		if(!isValidPosition(row, column))
			throw new InvalidBoardPositionException(row, column);			
		
		return tiles[row][column].isVisible();
	}
	
	private File selectValidPositionsFile(int number_of_players) {
		Integer num = Integer.valueOf(number_of_players);
		String path = VALID_POSITIONS_FILE_PATH.replaceAll("X", num.toString());
		return new File(path);
	}
	
	/**
	 * Fills the valid_positions array according to the contents of the file
	 * @param file with positions: 1 for valid, 0 for non valid
	 * @return
	 */
	private boolean[][] readValidBoardPositionsFile(File file) {
		boolean[][] positions = new boolean[BOARD_LENGTH][BOARD_LENGTH];
		Scanner scanner = null;
		
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String line;
		char c;
		
		for (int i = 0; i < BOARD_LENGTH; i++) {
			
			line = scanner.nextLine().replaceAll("\\s+", "");
			
			for (int j = 0; j < BOARD_LENGTH; j++) {
				c = line.charAt(j);
				if(c == '1') positions[i][j] = true;
				else positions[i][j] = false;
			}
		}
		
		return positions;
	}
		
	/***
	 * Fills every cell on the board with a tile (non valid positions are set to TileType.NULL)
	 */
	private void initTiles() {
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles.length; j++) {
				if(valid_positions[i][j])
					tiles[i][j] = new BoardTile(TileType.randomType(), i, j, this);
				else {
					tiles[i][j] = new BoardTile(TileType.NULL, i, j, this);
					tiles[i][j].setVisible(false);
				}
				
				this.label.add(tiles[i][j].getLabel());
			}
		}
	}
	
	/***
	 * Regenerates the missing tiles on the board
	 */
//	public void l() {
//		for (int i = 0; i < tiles.length; i++) {
//			for (int j = 0; j < tiles.length; j++) {
//				
//				//Check if tile is not null and if it isn't visible
//				if(tiles[i][j].getType() != TileType.NULL && !tiles[i][j].isVisible()) {
//					tiles[i][j].setType(TileType.randomType());
//					showTile(i, j);
//				}
//			}
//		}
//	}
	
	
	 public void refill() {
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles.length; j++) {
				
				//svuota tutte le tessere rimaste e le rimette nella pouch
				if(tiles[i][j].getType() != TileType.NULL && tiles[i][j].isVisible()) {
					Pouch.add(tiles[i][j].getType());
					tiles[i][j].setVisible(false);
					
				}
			}
		}
			for (int i = 0; i < tiles.length; i++) {
				for (int j = 0; j < tiles.length; j++) {
				
				if(tiles[i][j].getType() != TileType.NULL && !tiles[i][j].isVisible()) {
					tiles[i][j].setType(TileType.randomType());
					showTile(i, j);
				}
			}
		}
	}
	
	 
	public void hideAllTiles() {
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles.length; j++) {
				tiles[i][j].setVisible(false);
			}
		}
	}
}
