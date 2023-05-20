package model.dfs;

import model.personalgamearea.Bookshelf;
import model.personalgamearea.BookshelfTile;
import model.shared.TileType;

public class DepthFirstSearch {

	private int rows;
	private int cols;
	private Node[][] nodes;

	public DepthFirstSearch(Bookshelf bookshelf) {
		this.rows = Bookshelf.ROWS;
		this.cols = Bookshelf.COLUMNS;
		fillNodes(bookshelf.tiles);
	}

	/**
	 * Given a starting position, find the size of the group of cells with the same
	 * value as the initial cell
	 * 
	 * @param row Starting row
	 * @param col Starting column
	 * @return int
	 */
	public int countAdjacentCells(int row, int col) {
		TileType startingNodeValue = nodes[row][col].value;
		return searchNext(row, col, startingNodeValue, 0);
	}

	private int searchNext(int row, int col, TileType targetValue, int counter) {

		if (!isVisited(row, col) && nodes[row][col].value == targetValue) {
			
			//TODO remove this Sysout
			System.out.println(String.format("Searching from [%d %d]", nodes[row][col].row, nodes[row][col].col, nodes[row][col].value));

			nodes[row][col].visited = true;
			counter++;
			counter = searchNext(row, col + 1, targetValue, counter); // Go right
			counter = searchNext(row + 1, col, targetValue, counter); // Go down
			counter = searchNext(row - 1, col, targetValue, counter); // Go up
			counter = searchNext(row, col - 1, targetValue, counter); // Go left
		}

		return counter;
	}
	
	private void setBookshelf(Bookshelf bookshelf) {
		
		resetNodesVisitedState();
	}
	
	private void resetNodesVisitedState() {
		for (int row = 0; row < nodes.length; row++) {
			for (int col = 0; col < nodes[0].length; col++) {
				nodes[row][col].visited = false;
			}
		}
	}

	private void fillNodes(BookshelfTile[][] values) {
		this.nodes = new Node[rows][cols];

		for (int row = 0; row < this.rows; row++) {
			for (int col = 0; col < this.cols; col++) {
				nodes[row][col] = new Node(row, col, values[row][col].getType());
			}
		}
	}

	/**
	 * // * Returns whether the node at row/col has been visited
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	private boolean isVisited(int row, int col) {
		if (row < 0 || col < 0 || row >= this.rows || col >= this.cols)
			return true;

		return nodes[row][col].visited;
	}

	public void printNodes() {
		for (int row = 0; row < nodes.length; row++) {
			for (int col = 0; col < nodes[0].length; col++) {
				System.out.print(nodes[row][col].value.toString().charAt(0) + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public void printLastSearchResult() {
		System.out.println();
		System.out.println("( ) = not visited\n[*] = visited");
		System.out.println();

		for (int row = 0; row < this.rows; row++) {
			for (int col = 0; col < this.cols; col++) {
				if (nodes[row][col].visited)
					System.out.printf("[*]", nodes[row][col].value);
				else
					System.out.printf("( )", nodes[row][col].value);
			}
			System.out.println();
		}
		System.out.println();
	}
}
