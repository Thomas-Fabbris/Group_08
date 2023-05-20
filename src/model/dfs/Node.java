package model.dfs;

import model.shared.TileType;

public class Node {	
	public boolean visited = false;
	public final int row;
	public final int col;
	public final TileType value;
	
	public Node(int row, int col, TileType value) {
		this.row = row;
		this.col = col;
		this.value = value;
	}
}
