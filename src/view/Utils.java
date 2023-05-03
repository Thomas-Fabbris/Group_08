package view;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Utils {
	
	/**
	 * Returns an ImageIcon loaded from the specified path
	 * @param width
	 * @param height
	 * @param image_path
	 * @return
	 */
	public static ImageIcon LoadImageAsIcon(int width, int height, String image_path) {
		ImageIcon icon = new ImageIcon(image_path);
		Image tmp_image = icon.getImage();
		tmp_image = tmp_image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(tmp_image);
	}
}
