package view.personalgamearea;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import model.personalgamearea.Bookshelf;
import model.shared.TileType;

public class BookshelfTileLabel extends JLabel {
	
	private final String folder_path = "Assets/tiles/";
	ImageIcon image;
	private int tileLength;
	private int tileXLength;
	private int tileYLength;
	private int xOffset;
	private int yOffset;
	private int xPos;
	private int yPos;
	
	public BookshelfTileLabel(TileType tile_type, int row, int column, BookshelfLabel shelfLabel) {
		
		//Compute the size of the various elements needed to place the tiles
		//This method causes some displacement on screens with weird aspect ratios (eg 16:10) or with low resolutions
		this.tileLength = (int)(shelfLabel.getHeight() / 9); // '1/7 -> 1/9'
		this.tileXLength = shelfLabel.tilesContainer.getWidth() / 8;
		this.tileYLength = shelfLabel.tilesContainer.getHeight() / 10;
		this.xOffset = (int)(tileLength / 2.2);
		this.yOffset = (int)(tileLength / 3.8);
		this.xPos = convertColumnToXCoords(column);
		this.yPos = convertRowToYCoords(row);
		
		setType(tile_type);
		this.setBounds(xPos, yPos, tileLength, tileLength);
	}
	
	private String GetImagePath(TileType tile_type) {		
		String image_name = tile_type.toString(); //BOOKS
		String image_substring = image_name.substring(1,image_name.length()); //BOOKS -> OOKS
		return this.folder_path + image_name.charAt(0) + image_substring.toLowerCase() + ".png"; //'Assets/tiles/' + 'B' + 'ooks' + '.png'
	}
	
	private ImageIcon LoadImageAsIcon(String image_path) {
		ImageIcon icon = new ImageIcon(image_path);
		Image tmp_image = icon.getImage();
		tmp_image = tmp_image.getScaledInstance(this.tileLength, this.tileLength, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(tmp_image);
	}
	
	public void setType(TileType type) {
		if(type == TileType.NULL) return;
		this.image = LoadImageAsIcon(GetImagePath(type));
		setIcon(this.image);
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
