package model.shared;
/**
 * The class {@code BookshelfTile} models the tile object
 *
 */
public abstract class Tile{
	protected TileType type;
	protected boolean isActive;
	
	public Tile(TileType tile_type) {
		this.type = tile_type;
	}
	
	public void enable() {
		this.setActive(true);
	}
	
	public void disable() {
		this.setActive(false);
	}
	
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	public boolean isActive() {
		return this.isActive;
	}
	
	public TileType getType() {
		return type;
	}
		
	public void setType(TileType tile_type) {
		this.type = tile_type;
	}
	
	public boolean equals(Tile other) {
		if(!(other instanceof Tile)) {
			return false;
		}
		if (this.type.equals(other.type)) {
			return true;
		}
		return false;
	}
}
