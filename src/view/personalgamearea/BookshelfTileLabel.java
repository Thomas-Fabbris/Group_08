package view.personalgamearea;

import java.awt.Dimension;

import javax.swing.JLabel;

public class BookshelfTileLabel extends JLabel {
	
	private int tileLength;
	private int xOffset;
	private int yOffset;
	
	public BookshelfTileLabel(int row, int column, Dimension shelfLabelSize) {
		
		//Compute the size of the various elements needed to place the tiles
		//This method of computing element sizes causes some displacement on screens with weird aspect ratios (eg 16:10) or with low resolutions
		this.tileLength = (int)(shelfLabelSize.height / 9);

		this.xOffset = (int)(tileLength / 2.2);
		this.yOffset = (int)(tileLength / 3.8);
		int xPos = convertColumnToXCoords(column);
		int yPos = convertRowToYCoords(row);
//		this.setOpaque(true); //used for debug
		this.setBounds(xPos, yPos, tileLength, tileLength);
	}
	
	private int convertRowToYCoords(int row) {
		if(row == 0)
			return row * tileLength;
		
		return row*tileLength + this.yOffset*row;
	}
	
	private int convertColumnToXCoords(int column) {
		if(column == 0)
			return column * tileLength;
		
		return column*tileLength + this.xOffset*column;
	}
}
