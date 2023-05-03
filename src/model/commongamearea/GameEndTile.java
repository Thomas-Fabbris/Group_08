package model.commongamearea;

import model.shared.Player;
import model.shared.Tile;
import model.shared.TileType;

public class GameEndTile extends Tile {
	
	private Board board;
	
	public GameEndTile(Board board) {
		super(TileType.GAME_END);
		
		this.board = board;
//		this.label = new GameEndTileLabel(board.getLabel().getSize());
		this.enable();
	}
	
	//TODO: set the game end tile visible on the player's personal area
	public void award(Player player) {
		player.addPoints(1);
		player.setEndOfGameToken(true);
		this.disable();
	}

//	public GameEndTileLabel getLabel() {
//		return (GameEndTileLabel)this.label;
//	}
}
