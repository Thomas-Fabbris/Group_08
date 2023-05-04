package model.commongamearea;

import model.shared.Player;
import model.shared.Tile;
import model.shared.TileType;

public class GameEndTile extends Tile {
	
	private Board board;
	
	public GameEndTile(Board board) {
		super(TileType.GAME_END);
		
		this.board = board;
		this.enable();
	}
	
	public void award(Player player) {
		player.addPoints(1);
		player.setEndOfGameToken(true);
		this.disable();
	}
}
