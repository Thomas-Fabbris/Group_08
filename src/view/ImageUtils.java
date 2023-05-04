package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.color.ColorSpace;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;

import javax.swing.ImageIcon;

public class ImageUtils {
	
	/**
	 * Returns an ImageIcon loaded from the specified path
	 * @param width
	 * @param height
	 * @param imagePath
	 * @return ImageIcon
	 */
	public static ImageIcon loadImageAsIcon(int width, int height, String imagePath) {
		ImageIcon icon = new ImageIcon(imagePath);
		Image tmp_image = icon.getImage();
		tmp_image = tmp_image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(tmp_image);
	}
	
	/**
	 * Returns an ImageIcon loaded from the specified path
	 * @param size
	 * @param imagePath
	 * @return ImageIcon
	 */
	public static ImageIcon loadImageAsIcon(Dimension size, String imagePath) {
		ImageIcon icon = new ImageIcon(imagePath);
		Image tmp_image = icon.getImage();
		tmp_image = tmp_image.getScaledInstance(size.width, size.height, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(tmp_image);
	}
	
	/**
	 * Returns a rotated ImageIcon
	 * @param icon
	 * @param angle (degrees)
	 * @return
	 */
	public static ImageIcon rotateIcon(ImageIcon icon, int angle) {
		BufferedImage originalImage = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB); //load the icon as a buffered image
		
		Graphics g = originalImage.createGraphics();
		icon.paintIcon(null, g, 0, 0);
		g.dispose();
		
		double radians = Math.toRadians(angle); //then apply a rotation to the buffered image through a transform
		AffineTransform transform = new AffineTransform();
		transform.rotate(radians, icon.getIconWidth()/ 2, icon.getIconHeight()/ 2);
		AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
		BufferedImage rotatedImage = op.filter(originalImage, null);
		
		return new ImageIcon(rotatedImage);
	}
	
	/**
	 * Returns a gray-scale version of the icon
	 * @param icon
	 * @return
	 */
	public static ImageIcon getGrayImage(ImageIcon icon) {
		BufferedImage iconToImage = imageIconToBufferedImage(icon);
		ColorConvertOp op = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
		BufferedImage tmp = op.filter(iconToImage, iconToImage);
		return new ImageIcon(tmp);
	}
	
	private static BufferedImage imageIconToBufferedImage(ImageIcon icon) {
        BufferedImage bufferedImage = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics graphics = bufferedImage.createGraphics();
        icon.paintIcon(null, graphics, 0, 0);
        graphics.dispose();
        return bufferedImage;
    }
}
