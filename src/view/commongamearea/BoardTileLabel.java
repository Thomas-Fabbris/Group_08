package view.commongamearea;

import java.awt.Dimension;

import javax.swing.JLabel;

public class BoardTileLabel extends JLabel {
	
	private int offset;
	private int tileCellLength;
	
	public BoardTileLabel(int row, int column, Dimension boardLabelSize) {
		this.offset = (int)(boardLabelSize.height / 17);
		this.tileCellLength = boardLabelSize.width / 10;

		int tileLength = boardLabelSize.height/12;
		int xPos = convertColumnToXCoords(column);
		int yPos = convertRowToYCoords(row);
		
		this.setBounds(xPos, yPos, tileLength, tileLength);
	}
	
	/***
	 * Converts the row number to the actual y coordinate
	 * @param row
	 * @return the location in pixels of the row on the GUI
	 */
	private int convertRowToYCoords(int row) {
		return offset+((row - 1) * tileCellLength);
	}
	
	/***
	 * Converts the column number to the actual x coordinate
	 * @param column
	 * @return the location in pixels of the column on the GUI
	 */
	private int convertColumnToXCoords(int column) {
		return offset+((column - 1) * tileCellLength);
	}
}
