package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;

//This is a singleton
public class CommonObjectiveCardWindow extends JFrame {
	
	private static CommonObjectiveCardWindow instance = null;
	private final Dimension screen_size = Toolkit.getDefaultToolkit().getScreenSize();
	private final CommonObjectiveCardLabel[] card_labels = new CommonObjectiveCardLabel[2];
	
	private CommonObjectiveCardWindow() {
		super("Common Objective Cards");
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setResizable(false);
		this.setLayout(new FlowLayout(0, 10, 10));
		this.setBounds(0, screen_size.height-40-screen_size.height*1/3, screen_size.width/2, screen_size.height*1/3);
		this.getContentPane().setBackground(Color.DARK_GRAY);
	}
	
	/**
	 * Adds a card's label to the window and saves its reference in this object for later use
	 * @param card_label
	 */
	public void registerCard(JLabel card_label) {
		card_labels[0] = (CommonObjectiveCardLabel) card_label;
		this.add(card_label);
	}
	
	public static CommonObjectiveCardWindow getInstance() {
		if(instance == null)
			instance = new CommonObjectiveCardWindow();
		return instance;
	}
}
