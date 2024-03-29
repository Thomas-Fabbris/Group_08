package model.commongamearea;

import java.util.ArrayList;

import model.shared.Tile;
import model.shared.TileSides;
import model.shared.TileType;

public class BoardTile extends Tile {
	private int column;
	private int row;
	private Board board;

	/**
	 * This is the constructor of the class
	 * @param tile_type
	 * @param row
	 * @param column
	 * @param board
	 */
	public BoardTile(TileType tile_type, int row, int column, Board board) {
		super(tile_type);
		if (board == null) {
			throw new NullPointerException("board must no be set to null while creating a BoardTile instance!");
		}
		this.row = row;
		this.column = column;
		this.board = board;
		this.setActive(true);
	}
	
	/**
	 * Sets the the type of the BoardTile
	 * 
	 * @param type
	 */
	public void setType(TileType type) {
		super.setType(type);
	}
	
	/**
	 * Getter of this.row
	 * 
	 * @return row
	 */
	public int getRow() {
		return this.row;
	}
	
	/**
	 * Getter of this.column
	 * 
	 * @return column
	 */
	public int getColumn() {
		return this.column;
	}

	/**
	 * This method allows to get the upper Tile from the selected one
	 * 
	 * @return Tile
	 */
	public BoardTile tileUp() {
		return board.getTile(row - 1, column);
	}
	
	/**
	 * This method allows to get the lower Tile from the selected one
	 * 
	 * @return Tile
	 */
	public BoardTile tileDown() {
		return board.getTile(row + 1, column);
	}

	/**
	 * This method allows to get the right Tile from the selected one
	 * 
	 * @return Tile
	 */
	public BoardTile tileRight() {
		return board.getTile(row, column + 1);
	}

	/**
	 * This method allows to get the left Tile from the selected one
	 * 
	 * @return Tile
	 */
	public BoardTile tileLeft() {
		return board.getTile(row, column - 1);
	}

	/**
	 * This method returns whether the two tiles are adjacent on the board
	 * 
	 * @param adjacent tile
	 * @return
	 */
	public boolean isAdjacent(BoardTile tile) {
		return getDistance(tile) <= 1;
	}

	/**
	 * This method returns the distance between two tiles
	 *  
	 * @param tile
	 * @return
	 */
	public double getDistance(BoardTile tile) {
		// Pythagora's theorem
		return Math.pow(this.getRow() - tile.getRow(), 2) + Math.pow(this.getColumn() - tile.getColumn(), 2);
	}

	/**
	 * This method checks if the tile exists on the board
	 * 
	 * @return
	 */
	public boolean isInteractible() {
		return (!this.getType().equals(TileType.NULL) && this.isActive());
	}

	/**
	 * This method assigns checks the boolean (isSelectable)
	 * and verifies if the tile can be picked up from the board
	 * 
	 * @return
	 */
	public boolean canBePickedUp() {
		int contatore = 0;

		if (tileUp() != null && tileUp().isInteractible())
			contatore++;

		if (tileDown() != null && tileDown().isInteractible())
			contatore++;

		if (tileLeft() != null && tileLeft().isInteractible())
			contatore++;

		if (tileRight() != null && tileRight().isInteractible())
			contatore++;

		return contatore > 0 && contatore < 4;
	}

	/**
	 * This method returns whether all sides are blocked, all sides are free, or at least on side
	 * is free
	 * 
	 * @return
	 */
	public TileSides getBlockedSides() {
		int blockedSides = 0;

		if (tileUp() != null && tileUp().isActive)
			blockedSides++;

		if (tileDown() != null && tileDown().isActive)
			blockedSides++;

		if (tileLeft() != null && tileLeft().isActive)
			blockedSides++;

		if (tileRight() != null && tileRight().isActive)
			blockedSides++;

		if (blockedSides == 0) {
			return TileSides.ALL_SIDES_FREE;
		}

		if (blockedSides == 4) {
			return TileSides.ALL_SIDES_BLOCKED;
		}

		return TileSides.AT_LEAST_ONE_SIDE_FREE;
	}

	/**
	 * This method helps to understand which tiles can be picked at the same time of the selected one.
	 * The purpose is to create a list of option with the possible choices of Tiles that the
	 * player can pick up from the board.
	 * 
	 * @return
	 */
	public ArrayList<BoardTile> Choices() {
		ArrayList<BoardTile> choices = new ArrayList<BoardTile>();// si crea un arraylist per salvare le varie opzioni
		if (this.canBePickedUp()) {
			if ((this.tileRight().canBePickedUp()) && (this.tileRight().getType().equals(this.getType()))) {
				if ((this.tileRight().tileRight().canBePickedUp())
						&& (this.tileRight().tileRight().getType().equals(this.getType()))) {
					choices.add(this);
					choices.add(this.tileRight());
					choices.add(this.tileRight().tileRight());
				} else {
					choices.add(this);
					choices.add(this.tileRight());
				}
			} else {
				choices.add(this);// si creeranno dei duplicati della prima tile, ma servono per distinguere i
									// vari gruppi che possono essere scelti, poichè si possono prendere solo tile
									// adiacenti
			}
			if ((this.tileDown().canBePickedUp()) && (this.tileDown().getType().equals(this.getType()))) {
				if ((this.tileDown().tileDown().canBePickedUp())
						&& (this.tileDown().tileDown().getType().equals(this.getType()))) {
					choices.add(this);
					choices.add(this.tileDown());
					choices.add(this.tileDown().tileDown());
				} else {
					choices.add(this);
					choices.add(this.tileDown());
				}
			} else {
				choices.add(this);
			}
			if ((this.tileLeft().canBePickedUp()) && (this.tileLeft().getType().equals(this.getType()))) {
				if ((this.tileLeft().tileLeft().canBePickedUp())
						&& (this.tileLeft().tileLeft().getType().equals(this.getType()))) {
					choices.add(this);
					choices.add(this.tileLeft());
					choices.add(this.tileLeft().tileLeft());
				} else {
					choices.add(this);
					choices.add(this.tileLeft());
				}
			} else {
				choices.add(this);
			}
			if ((this.tileUp().canBePickedUp()) && (this.tileUp().getType().equals(this.getType()))) {
				if ((this.tileUp().tileUp().canBePickedUp())
						&& (this.tileUp().tileUp().getType().equals(this.getType()))) {
					choices.add(this);
					choices.add(this.tileUp());
					choices.add(this.tileUp().tileUp());
				} else {
					choices.add(this);
					choices.add(this.tileUp());
				}
			} else {
				choices.add(this);
			}
		}

		return choices;
	}

	@Override
	public String toString() {
		return this.getType().toString();
	}
}