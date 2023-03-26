package main;

import javax.swing.JLabel;

public abstract class Card {
	protected JLabel label;
	protected int card_id;
	public static final int MAX_CARD_ID = 12;
	
	public Card(int card_id) {
		
	}
}
