package view.commongamearea;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import model.shared.TileType;

public class BoardTileLabel extends JLabel {
	
	private final String folder_path = "Assets/tiles/";
	ImageIcon image;
	ImageIcon imageGray;
	
	//informazioni sulla posizione del tile in pixels
	private int x_pos;
	private int y_pos;
	
	//dimensioni del tile in pixels
	public final int tileLength;
	private final int tileCellLength;
	private final int offset;
	
	public BoardTileLabel(TileType tile_type, int row, int column, Dimension board_size) {
		this.tileLength = board_size.height/12;
		this.offset = board_size.height/17;
		this.tileCellLength = board_size.width / 10;
		this.x_pos = convertColumnToXCoords(column);
		this.y_pos = convertRowToYCoords(row);
		this.setBounds(this.x_pos, this.y_pos, tileLength, tileLength);

//		this.image = LoadImageAsIcon(GetImagePath(tile_type));
//		this.imageGray = getGrayImage(this.image);
		
		setType(tile_type);
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
	
	private ImageIcon getGrayImage(ImageIcon icon) {
		BufferedImage iconToImage = imageIconToBufferedImage(icon);
		ColorConvertOp op = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
		BufferedImage tmp = op.filter(iconToImage, iconToImage);
		return new ImageIcon(tmp);
	}
	
	private BufferedImage imageIconToBufferedImage(ImageIcon icon) {
        BufferedImage bufferedImage = new BufferedImage(tileLength, tileLength, BufferedImage.TYPE_INT_ARGB);
        Graphics graphics = bufferedImage.createGraphics();
        icon.paintIcon(null, graphics, 0, 0);
        graphics.dispose();
        return bufferedImage;
    }
	
	public void setType(TileType type) {
		if(type == TileType.NULL) return;
		this.image = LoadImageAsIcon(GetImagePath(type));
		this.imageGray =  this.getGrayImage(image);
		setIcon(this.image);
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
