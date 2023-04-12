package main;

import javax.swing.JLabel;

public abstract class Card {
	protected JLabel label;
	protected int card_id;
	
	public Card(int card_id) {
		
	}
	
	public JLabel getLabel() {
		return label;
	}
}
