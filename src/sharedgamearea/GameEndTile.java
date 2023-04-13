package sharedgamearea;

import gui.BoardTileLabel;
import gui.GameEndTileLabel;
import main.Tile;
import main.TileType;

public class GameEndTile extends Tile {
	
	Board board;
	
	public GameEndTile(Board board) {
		super(TileType.GAME_END);
		
		this.board = board;
		this.setLabel(new GameEndTileLabel(board.getLabel().getSize()));
		this.setVisible(true);
	}
	
	@Override
	public void setType(TileType tile_type) {
		throw new UnsupportedOperationException("The game end tile cannot have a different type than " + this.getType());
	}
}
