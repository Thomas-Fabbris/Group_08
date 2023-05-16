package model.commongamearea;

import java.util.ArrayList;

import model.shared.Tile;
import model.shared.TileType;

public class BoardTile extends Tile {
	private int column;
	private int row;
	private Board board;

	public BoardTile(TileType tile_type, int row, int column, Board board) {
		super(tile_type);
		this.row = row;
		this.column = column;
		this.board = board;
		this.isActive = true;
	}

	public void setType(TileType type) {
		super.setType(type);
	}

	public int getRow() {
		return this.row;
	}

	public int getColumn() {
		return this.column;
	}

	public BoardTile tileUp() {
		return board.getTile(row - 1, column);
	}

	public BoardTile tileDown() {
		return board.getTile(row + 1, column);
	}

	public BoardTile tileRigth() {
		return board.getTile(row, column + 1);
	}

	public BoardTile tileLeft() {
		return board.getTile(row, column - 1);
	}

	/**
	 * Returns whether the two tiles are adjacent on the board
	 * 
	 * @param adjacent tile
	 * @return
	 */
	public boolean isAdjacent(BoardTile tile) {
		return getDistance(tile) <= 1;
	}
	
	public double getDistance(BoardTile tile) {
		// Pythagora's theorem
		return Math.pow(this.getRow() - tile.getRow(), 2) + Math.pow(this.getColumn() - tile.getColumn(), 2);
	}

	// checks if the tile exists on the board
	public boolean isInteractible() {
		return (!this.getType().equals(TileType.NULL) && this.isActive());
	}

	// assigns the boolean (isSelectable) to the checked tile
	public boolean canBePickedUp() {
		int contatore = 0;
		
		if (tileUp() != null && tileUp().isInteractible())
			contatore++;

		if (tileDown() != null && tileDown().isInteractible())
			contatore++;

		if (tileLeft() != null && tileLeft().isInteractible())
			contatore++;

		if (tileRigth() != null && tileRigth().isInteractible())
			contatore++;

		return contatore > 0 && contatore < 4;
	}

	// serve, dopo aver verificato se una tile può essere presa, per capire quali
	// altre tile possono essere prese insieme a lei
	// il senso è quello di creare una lista di opzioni tra le quali il giocatore
	// può scegliere quali gruppi di tile prendere
	public ArrayList<BoardTile> Choices() {
		ArrayList<BoardTile> choices = new ArrayList<BoardTile>();// si crea un arraylist per salvare le varie opzioni
		if (this.canBePickedUp()) {
			if ((this.tileRigth().canBePickedUp()) && (this.tileRigth().getType().equals(this.getType()))) {
				if ((this.tileRigth().tileRigth().canBePickedUp())
						&& (this.tileRigth().tileRigth().getType().equals(this.getType()))) {
					choices.add(this);
					choices.add(this.tileRigth());
					choices.add(this.tileRigth().tileRigth());
				} else {
					choices.add(this);
					choices.add(this.tileRigth());
				}
			} else {
				choices.add(this);// si creeranno dei duplicati della prima tile, ma servono per distinguere i
									// vari gruppi che possono essere scelti, poichè si possono prendere solo tile
									// adiacenti
			}
			if ((this.tileDown().canBePickedUp()) && (this.tileDown().getType().equals(this.getType()))) {// non mi è
																											// venuto in
																											// mente
																											// come
																											// ottimizzare
																											// il ciclo
																											// perciò ho
																											// semplicemente
																											// reiterato
																											// lo stesso
																											// processo
																											// più volte
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
}