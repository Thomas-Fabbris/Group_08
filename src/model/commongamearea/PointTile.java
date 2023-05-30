package model.commongamearea;

import model.shared.Tile;
import model.shared.TileType;

public class PointTile extends Tile {
	
	private int cardId = -1; //default value -1 means this point tile is not associated with a card yet
	private int romanNumber;
	private int points;
	
	/**
	 * This is the constructor of the class
	 * 
	 * @param card
	 * @param points
	 */
	public PointTile(CommonObjectiveCard card, int points) {
		super(TileType.POINTS);
		if(card == null) {
			throw new NullPointerException("card cannot be set to null while creating a PointTile instance!");
		}
		this.cardId = card.getId();
		this.romanNumber = card.getRomanNumber();
		this.points = points;
	}
	
	/**
	 * This method is a getter
	 * 
	 * @return cardId
	 */
	public int getCardId() {
		return cardId;
	}

	/**
	 * This method is a setter
	 * 
	 * @param cardId
	 */
	public void setCardId(int cardId) {
		this.cardId = cardId;
	}
	
	/**
	 * This method is a getter
	 * 
	 * @return points
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * This method is a getter
	 * 
	 * @return romanNumber
	 */
	public int getRomanNumber() {
		return romanNumber;
	}
	
	@Override
	public String toString() {
		return "CardId: " +this.cardId+ " RomandNum: " +this.romanNumber+ " Points: " +this.points;
	}
}
