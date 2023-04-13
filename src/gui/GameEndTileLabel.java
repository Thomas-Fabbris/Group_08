package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import main.TileType;

public class GameEndTileLabel extends JLabel {
	private final String IMAGE_PATH = "Assets/Point_tiles/First_to_finish.png";
	ImageIcon image;
	
	//informazioni sulla posizione del tile in pixels
	private int x_pos;
	private int y_pos;
	
	//dimensioni del tile in pixels
	private final int side_length;
	private final int top_offset;
	private final int left_offset;
	
	public GameEndTileLabel(Dimension board_size) {
		this.side_length = board_size.height/12;
		this.top_offset = (int)(card_size.height / 3.55);
		this.left_offset = (int)(card_size.width / 1.6);
		
		x_pos = left_offset-left_offset/16;
		y_pos = top_offset;

		this.image = RotateIcon(LoadImageAsIcon(IMAGE_PATH));
		this.setIcon(image);
		
		this.setBounds(x_pos, y_pos, side_length, side_length);
		this.setVisible(true);
	}
	
	private ImageIcon LoadImageAsIcon(String image_path) {
		ImageIcon icon = new ImageIcon(image_path);
		Image tmp_image = icon.getImage();
		tmp_image = tmp_image.getScaledInstance(this.side_length, this.side_length, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(tmp_image);
	}
	
	private ImageIcon RotateIcon(ImageIcon icon) {
		BufferedImage originalImage = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics g = originalImage.createGraphics();
		icon.paintIcon(null, g, 0, 0);
		g.dispose();
		
		double radians = Math.toRadians(352);
		AffineTransform transform = new AffineTransform();
		transform.rotate(radians, this.side_length/ 2, this.side_length/ 2);
		AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
		BufferedImage rotatedImage = op.filter(originalImage, null);
		
		return new ImageIcon(rotatedImage);
	}
}
