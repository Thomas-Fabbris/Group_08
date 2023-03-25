package main;

import java.awt.Dimension;
import javax.swing.JLabel;

import gui.TileLabel;

public class Tile {
	private TileType type;
	protected TileLabel label; //image displayed on the GUI
	private boolean isVisible;
	
	private int column;
	private int row;
	
	public Tile(TileType tile_type, int row, int column, Dimension board_size) {
		this.type = tile_type;
		this.row = row;
		this.column = column;
		this.label = new TileLabel(type, row, column, board_size);
		this.isVisible = true;
	}

	public TileLabel getLabel() {
		return label;
	}
	
	public boolean isVisible() {
		return this.isVisible;
	}
	
	public void setVisible(boolean isVisible) {
		this.label.setVisible(isVisible);
		this.isVisible = isVisible;
	}
	
	public TileType getType() {
		return type;
	}
	
	public int getRow() {
		return this.row;
	}
	
	public int getColumn() {
		return this.column;
	}
}
