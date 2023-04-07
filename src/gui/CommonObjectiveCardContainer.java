package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JPanel;

import sharedgamearea.CommonObjectiveCard;

//Singleton
public class CommonObjectiveCardContainer extends JPanel {
	
	private final Dimension screen_size = Toolkit.getDefaultToolkit().getScreenSize();
	private final CommonObjectiveCardLabel[] card_labels = new CommonObjectiveCardLabel[2];
	private static int index = 0;
	
	public CommonObjectiveCardContainer() {
		this.setLayout(new FlowLayout(0, 10, 10));
		this.setSize(screen_size.width/2, screen_size.height*1/3);
		this.setBackground(Color.DARK_GRAY);
	}
	
	/**
	 * Adds a card's label to the window and saves its reference in this object for later use
	 * @param card_label
	 */
	private void registerCardLabel(JLabel card_label) {
		card_labels[index++] = (CommonObjectiveCardLabel) card_label;
		this.add(card_label);
	}
	
	public void addCard(int card_id) {
		if(index >= 2) {
			throw new IndexOutOfBoundsException();
		}
		
		CommonObjectiveCardLabel label = new CommonObjectiveCardLabel(card_id);
		registerCardLabel(label);
	}
	
	public void addCard(CommonObjectiveCard card) {
		addCard(card.getId());
	}
}
