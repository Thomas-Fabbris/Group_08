package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;

/**
 * @author Lorenzo Corbellini
 *
 */
public class TileSelectionHintDialog extends JDialog {

	public static boolean enabled = false; // Disable this to prevent the tutorial from appearing
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	ImageIcon gif;
	JLabel imageDisplay;
	JLabel[] textHints = new JLabel[5];

	public TileSelectionHintDialog() {

		if(!TileSelectionHintDialog.enabled) {
			return;
		}
		
		// Basic dialog window setup
		this.setModal(true);
		int imgWidth = screenSize.height;
		int imgHeight = imgWidth * 10 / 16;
		this.setSize(imgWidth, screenSize.height - 50);
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

		// Load the gif and resize it through some black magic
		ImageIcon imageIcon = new ImageIcon("Assets/TileSelectionInstruction.gif");
		Image image = imageIcon.getImage();
		Image newimg = image.getScaledInstance(imgWidth, imgHeight, Image.SCALE_DEFAULT);
		imageIcon = new ImageIcon(newimg);
		JLabel imageDisplay = new JLabel(imageIcon);
		imageDisplay.setHorizontalAlignment(JLabel.CENTER);
		imageDisplay.setVerticalAlignment(JLabel.BOTTOM);

		// Center the label and add a border
		imageDisplay.setBorder(BorderFactory.createLineBorder(Color.CYAN, screenSize.height / 120));

		// Create text
		textHints[0] = new JLabel("HOW TO SELECT TILES:");
		textHints[1] = new JLabel("> Click on tiles to select them: they will appear to the right of the board.");
		textHints[2] = new JLabel("> The tile on top will always be the first to go in the bookshelf.");
		textHints[3] = new JLabel("> You can move a tile to the top by clicking on it.");
		textHints[4] = new JLabel("[Close this window to keep playing]");

		// Create fonts and set each label's font
		Font f0 = new Font("Dialog", Font.BOLD, (int) (this.getHeight() / 30));
		Font f1 = new Font("Dialog", Font.BOLD, (int) (this.getHeight() / 35));
		Font f2 = new Font("Dialog", Font.ITALIC, (int) (this.getHeight() / 35));

		textHints[0].setFont(f0);
		textHints[1].setFont(f1);
		textHints[2].setFont(f1);
		textHints[3].setFont(f1);
		textHints[4].setFont(f2);
		setTextColor(textHints, Color.WHITE);
		
		// Add all elements in order
		this.add(textHints[0]);
		this.add(textHints[1]);
		this.add(textHints[2]);
		this.add(textHints[3]);
		this.add(textHints[4]);
		this.add(Box.createVerticalGlue());
		this.add(imageDisplay);

		// Setup the dialog window
		this.setResizable(false);
		this.getContentPane().setBackground(Color.DARK_GRAY);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("./Assets/Icon.png"));
		this.setTitle("MyShelfie - Hint on Selecting Tiles");
		this.setLocation(screenSize.width / 4, 0);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	
	private void setTextColor(JLabel[] labels, Color color) {
		for (int i = 0; i < labels.length; i++) {
			labels[i].setForeground(color);
		}
	}
}
