package main;

import gui.BoardTileLabel;

public abstract class Tile {
	private TileType type;
	private BoardTileLabel label; //image displayed on the GUI
	private boolean isVisible;
	
	public Tile(TileType tile_type) {
		this.type = tile_type;
	}

	public BoardTileLabel getLabel() {
		return label;
	}
	
	protected void setLabel(BoardTileLabel label) {
		this.label = label;
	}
	
	protected void setLabel(TileType tile_type) {
		label.setType(tile_type);
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
	
	public void setType(TileType tile_type) {
		this.type = tile_type;
		setLabel(tile_type);
	}
}
