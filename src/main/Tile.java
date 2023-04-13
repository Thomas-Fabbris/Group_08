package main;

import javax.swing.JLabel;

import gui.TileLabel;

public abstract class Tile{
	protected TileType type;
	protected JLabel label; //image displayed on the GUI
	protected boolean isVisible;
	
	public Tile(TileType tile_type) {
		this.type = tile_type;
	}
	
	protected void setLabel(JLabel label) {
		this.label = label;
	}
	
	public boolean isVisible() {
		return label.isVisible();
	}
	
	public void setVisible(boolean isVisible) {
		this.label.setVisible(isVisible);
	}
	
	public TileType getType() {
		return type;
	}
	
	public void setType(TileType tile_type) {
		this.type = tile_type;
	}
}
