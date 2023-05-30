package model.commongamearea;

import java.util.Stack;

import model.Player;
import model.shared.IdGenerator;

public class CommonObjectiveCard {

	private int cardId; // This id identifies which operation this common objective card has to perform.
	private int romanNumber; // This identifies which point tiles the card holds.
	private CommonGoals relatedCommonGoal;
	public static final int MAX_CARD_ID = 12;
	private static int ROMAN_NUMBER = 0;
	private Stack<PointTile> pointTiles = new Stack<>();

	/**
	 * This is the constructor of the class
	 * 
	 * @param idGenerator
	 * @param numberOfPlayers
	 */
	public CommonObjectiveCard(IdGenerator idGenerator, int numberOfPlayers) {
		if(idGenerator == null) {
			throw new NullPointerException("idGenerator must not be set to null while creating a CommonObjectiveCard instance!");
		}
		
		this.romanNumber = CommonObjectiveCard.ROMAN_NUMBER++;
		this.cardId = idGenerator.getNewPersonalObjectiveCardId();
//		this.cardId = 6; // debug


		initRelatedCommonGoal();
		initPointsList(numberOfPlayers);
	}

	/**
	 * This method is an id getter for the CommonObjectiveCard
	 * 
	 * @return
	 */
	public int getId() {
		return this.cardId;
	}

	/**
	 * This method allows to assign different number of points for a common objective.
	 * The number of points and objectives that can be collected change according to
	 * the different number of players in the game.
	 * 
	 * @param numberOfPlayers
	 */
	private void initPointsList(int numberOfPlayers) {

		switch (numberOfPlayers) {
		case 2:
			pointTiles.push(new PointTile(this, 4));
			pointTiles.push(new PointTile(this, 8));
			return;

		case 3:
			pointTiles.push(new PointTile(this, 4));
			pointTiles.push(new PointTile(this, 6));
			pointTiles.push(new PointTile(this, 8));
			return;

		case 4:

			pointTiles.push(new PointTile(this, 2));
			pointTiles.push(new PointTile(this, 4));
			pointTiles.push(new PointTile(this, 6));
			pointTiles.push(new PointTile(this, 8));
			return;

		default:
			throw new IllegalStateException(
					"Something went wrong while initializing common objective card with id " + this.cardId);
		}
	}
	
	/**
	 * This method assigns the common goals according to the number of the id selected
	 */
	private void initRelatedCommonGoal() {
		switch (this.getId()) {

		case 1:
			this.relatedCommonGoal = CommonGoals.CommonGoal1;
			break;

		case 2:
			this.relatedCommonGoal = CommonGoals.CommonGoal2;
			break;

		case 3:
			this.relatedCommonGoal = CommonGoals.CommonGoal3;
			break;

		case 4:
			this.relatedCommonGoal = CommonGoals.CommonGoal4;
			break;

		case 5:
			this.relatedCommonGoal = CommonGoals.CommonGoal5;
			break;

		case 6:
			this.relatedCommonGoal = CommonGoals.CommonGoal6;
			break;

		case 7:
			this.relatedCommonGoal = CommonGoals.CommonGoal7;
			break;

		case 8:
			this.relatedCommonGoal = CommonGoals.CommonGoal8;
			break;

		case 9:
			this.relatedCommonGoal = CommonGoals.CommonGoal9;
			break;

		case 10:
			this.relatedCommonGoal = CommonGoals.CommonGoal10;
			break;

		case 11:
			this.relatedCommonGoal = CommonGoals.CommonGoal11;
			break;

		case 12:
			this.relatedCommonGoal = CommonGoals.CommonGoal12;
			break;

		default:
			throw new IllegalStateException(
					"Something went wrong while initializing common objective card with id " + this.cardId);
		}
	}

	/**
	 * This method gives the points displayed on the first available tile to the player
	 * If there are no points left, the player doesn't receive anything even if he completes the goal
	 * 
	 * @param player (the one who receives the points)
	 */
	public void award(Player player) {
		
		if (pointTiles.size() <= 0) {
			return;
		}

		// Give points and remove the value of points awarded to the player
		player.awardPointTile(pointTiles.lastElement());
		pointTiles.pop();
	}

	public Stack<PointTile> getPointTiles() {
		return pointTiles;
	}

	/**
	 * This method returns pointTiles.lastElement() if the stack is not empty.
	 * Else it returns null
	 * 
	 * @return
	 */
	public PointTile getLastPointTile() {
		if (pointTiles.size() > 0) {
			return pointTiles.lastElement();
		} else {
			return null;
		}
	}

	/**
	 * This method is a getter
	 * 
	 * @return the relatedCommonGoal
	 */
	public CommonGoals getRelatedCommonGoal() {
		return relatedCommonGoal;
	}
	
	/**
	 * This method is a getter
	 * 
	 * @return romanNumber
	 */
	public int getRomanNumber() {
		return romanNumber;
	}
}
