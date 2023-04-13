package sharedgamearea;

import gui.GameEndTileLabel;
import main.Player;
import main.Tile;
import main.TileType;

public class GameEndTile extends Tile {
	
	private Board board;
	
	public GameEndTile(Board board) {
		super(TileType.GAME_END);
		
		this.board = board;
		this.label = new GameEndTileLabel(board.getLabel().getSize());
		this.setVisible(true);
	}
	
	//TODO: set the game end tile visible on the player's personal area
	public void award(Player player) {
		player.givePoints(1);
		label.setVisible(false);
	}

	public GameEndTileLabel getLabel() {
		return (GameEndTileLabel)this.label;
	}
}
