package main;

import gui.TileLabel;

public class Tile {
	private TileType type;
	private TileLabel label; //image displayed on the GUI
	private boolean isVisible;
	
	public Tile(TileType tile_type) {
		this.type = tile_type;
	}

	public TileLabel getLabel() {
		return label;
	}
	
	protected void setLabel(TileLabel label) {
		this.label = label;
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
		this.type = tile_type ;
	}
}
