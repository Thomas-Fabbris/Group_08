package controller.commongamearea;

import javax.swing.JLabel;

import model.commongamearea.CommonObjectiveCard;
import view.Utils;

public class CommonObjectiveCardsController {

	private CommonObjectiveCard card1;
	private CommonObjectiveCard card2;
	
	private JLabel card1Label;
	private JLabel card2Label;
	
	public CommonObjectiveCardsController(CommonObjectiveCard card1, CommonObjectiveCard card2, JLabel card1Label, JLabel card2Label) {
		this.card1 = card1;
		this.card2 = card2;
		
		this.card1Label = card1Label;
		this.card2Label = card2Label;
		
		assignIcon(card1, card1Label);
		assignIcon(card2, card2Label);
	}
	
	private String getImagePath(int cardId) {
		String PATH = "Assets/Carte_Obiettivo_Comune/Carta_X.png";
		
		if(cardId < 1 || cardId > 12)
			return null;
		
		Integer value = cardId;
		return PATH.replace("X", value.toString());
	}
	
	private void assignIcon(CommonObjectiveCard card, JLabel cardLabel) {
		cardLabel.setIcon(Utils.LoadImageAsIcon(cardLabel.getWidth(), cardLabel.getHeight(), getImagePath(card.getId())));
	}
}
