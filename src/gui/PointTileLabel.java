package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class PointTileLabel extends JLabel {
	private final String folder_path = "Assets/Point_tiles/Xp.jpg";
	ImageIcon image;
	
	//informazioni sulla posizione del tile in pixels
	private int x_pos;
	private int y_pos;
	
	//dimensioni del tile in pixels
	private final int side_length;
	private final int top_offset;
	private final int left_offset;
	
	public PointTileLabel(Dimension card_size, int starting_point) {
//		this.setOpaque(true);
//		this.setBackground(Color.red);
		
		this.side_length = (int)(card_size.width / 3.55);
		this.top_offset = (int)(card_size.height / 3.55);
		this.left_offset = (int)(card_size.width / 1.7);
		
		x_pos = left_offset;
		y_pos = top_offset;

		this.setVerticalAlignment(JLabel.CENTER);
		this.setHorizontalAlignment(JLabel.CENTER);
		
		this.setBounds(x_pos, y_pos, side_length, side_length);
		this.setImage(starting_point);

		this.setVisible(true);
	}

	private String GetImagePath(int points) {
		String image_id = (Integer.valueOf(points)).toString();
		return folder_path.replaceAll("X", image_id);
	}
	
	private ImageIcon LoadImageAsIcon(String image_path) {
		ImageIcon icon = new ImageIcon(image_path);
		Image tmp_image = icon.getImage();
		tmp_image = tmp_image.getScaledInstance(side_length, side_length, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(tmp_image);
	}
	
	private ImageIcon RotateIcon(ImageIcon icon) {
		BufferedImage originalImage = new BufferedImage(side_length, side_length, BufferedImage.TYPE_INT_ARGB); //load the icon as a buffered image
		
		Graphics g = originalImage.createGraphics();
		icon.paintIcon(null, g, 0, 0);
		g.dispose();
		
		double radians = Math.toRadians(352); //then apply a rotation to the buffered image through a transform
		AffineTransform transform = new AffineTransform();
		transform.rotate(radians, this.side_length/ 2, this.side_length/ 2);
		AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
		BufferedImage rotatedImage = op.filter(originalImage, null);
		
		return new ImageIcon(rotatedImage);
	}
	
	public void setImage(int points) {
		
		if(points != 2 && points != 4 && points != 6 && points != 8)
			throw new IllegalArgumentException("A point tile with " + points + " points does not exist!");
		
		this.image = LoadImageAsIcon(GetImagePath(points));
		this.image = RotateIcon(image);

		setIcon(this.image);
	}
}
