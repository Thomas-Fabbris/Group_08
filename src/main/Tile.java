package main;

import java.awt.Dimension;
import javax.swing.JLabel;

import gui.TileLabel;

public class Tile {
	public final TileType type;
	private final TileLabel label; //image displayed on the GUI
	
	private int column;
	private int row;
	
	public Tile(TileType tile_type, int row, int column, Dimension board_size) {
		this.type = tile_type;
		this.row = row;
		this.column = column;
		this.label = new TileLabel(type, row, column, board_size);
	}

	public JLabel getLabel() {
		return label;
	}
	
	public int getRow() {
		return this.row;
	}
	
	public int getColumn() {
		return this.column;
	}
}
