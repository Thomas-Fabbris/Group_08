//package view;
//
//import java.awt.Dimension;
//
//import javax.swing.ImageIcon;
//import javax.swing.JLabel;
//
//public class PointTileLabel extends JLabel {
//	private final String folder_path = "Assets/Point_tiles/Xp.jpg";
//	ImageIcon image;
//	
//	//informazioni sulla posizione del tile in pixels
//	private int x_pos;
//	private int y_pos;
//	
//	//dimensioni del tile in pixels
//	private final int side_length;
//	private final int top_offset;
//	private final int left_offset;
//	
//	public PointTileLabel(Dimension card_size) {		
//		this.side_length = (int)(card_size.width / 3.55);
//		this.top_offset = (int)(card_size.height / 3.55);
//		this.left_offset = (int)(card_size.width / 1.7);
//		
//		x_pos = left_offset;
//		y_pos = top_offset;
//
//		this.setVerticalAlignment(JLabel.CENTER);
//		this.setHorizontalAlignment(JLabel.CENTER);
//		
//		this.setBounds(x_pos, y_pos, side_length, side_length);
//
//		this.setVisible(true);
//	}
//
////	private String GetImagePath(int points) {
////		String image_id = (Integer.valueOf(points)).toString();
////		return folder_path.replaceAll("X", image_id);
////	}
//	
//	//TODO: move this to model and manage through controller
//	public void setImage(int points) {
//		
//		if(points != 2 && points != 4 && points != 6 && points != 8)
//			throw new IllegalArgumentException("A point tile with " + points + " points does not exist!");
//		
////		this.image = ImageUtils.LoadImageAsIcon(this.getWidth(), this.getHeight(), GetImagePath(points));
////		this.image = ImageUtils.RotateIcon(image, 352);
////
////		setIcon(this.image);
//	}
//}
