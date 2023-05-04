package view.personalgamearea;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import model.shared.TileType;

public class BookshelfTileLabel extends JLabel {
	
	private int tileLength;
	private int xOffset;
	private int yOffset;
	
	public BookshelfTileLabel(int row, int column, Dimension shelfLabelSize) {
		
		//Compute the size of the various elements needed to place the tiles
		//This method causes some displacement on screens with weird aspect ratios (eg 16:10) or with low resolutions
		this.tileLength = (int)(shelfLabelSize.height / 9); // '1/7 -> 1/9'

		this.xOffset = (int)(tileLength / 2.2);
		this.yOffset = (int)(tileLength / 3.8);
		int xPos = convertColumnToXCoords(column);
		int yPos = convertRowToYCoords(row);

		this.setBounds(xPos, yPos, tileLength, tileLength);
		this.setOpaque(true);
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
