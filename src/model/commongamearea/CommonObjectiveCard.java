package model.commongamearea;

import java.awt.event.ActionEvent;
import java.util.Stack;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import model.personalgamearea.PointTile;
import model.shared.IdGenerator;
import model.shared.Player;
import view.sharedgamearea.CommonObjectiveCardLabel;

public class CommonObjectiveCard {
	
	private int card_id;
	private CommonObjectiveCardLabel label;
	public static int MAX_CARD_ID = 12;
	private Stack<PointTile> point_tiles = new Stack<>();
	
	//Used for debug - should be removed
	private AbstractAction XpressedAction = new AbstractAction() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			IdGenerator gen = new IdGenerator();
			Player player = new Player("Francioschio", gen);
			award(player);
		}
	};
	
	public CommonObjectiveCard(int card_id) {
		this.card_id = card_id;
		
		initPointsList();
		this.label = new CommonObjectiveCardLabel(card_id, point_tiles.lastElement().getPoints());
		
		//debug
		this.label.getInputMap().put(KeyStroke.getKeyStroke("X"), "pressed");
		this.label.getActionMap().put("pressed", XpressedAction);
	}
	
	public CommonObjectiveCardLabel getLabel() {
		return label;
	}
	
	public int getId() {
		return this.card_id;
	}
	
	private void initPointsList() {
		
		switch (Player.getNumberOfPlayers()) {
		case 2:
			point_tiles.push(new PointTile(this, 4));
			point_tiles.push(new PointTile(this, 8));
			return;

		case 3:
			point_tiles.push(new PointTile(this, 4));
			point_tiles.push(new PointTile(this, 6));
			point_tiles.push(new PointTile(this, 8));
			return;
		
		case 4:
			
			point_tiles.push(new PointTile(this, 2));
			point_tiles.push(new PointTile(this, 4));
			point_tiles.push(new PointTile(this, 6));
			point_tiles.push(new PointTile(this, 8));
			return;
			
		default:
			throw new IllegalStateException("Something went wrong while initializing common objective card with id " + this.card_id);
		}
		
	}
	
	/**
	 * Give the points displayed on the first available tile to the player
	 * @param player to give the points to
	 */
	public void award(Player player) {
		
		//If there are no points left, don't give any
		if(point_tiles.size() <= 0) {
			((CommonObjectiveCardLabel) (this.label)).hidePointsTile();
			return;
		}
		
		//Give points and remove the value of points awarded to the player
//		player.addPoints(point_tiles.lastElement().getPoints());
		player.awardPointTile(point_tiles.lastElement());
		point_tiles.pop();
		
		try {
			((CommonObjectiveCardLabel) (this.label)).updatePointsTile(point_tiles.lastElement().getPoints());
		} catch (Exception e) {
			((CommonObjectiveCardLabel) (this.label)).hidePointsTile();
		}
	}
	
	//Check if player can receive points - needs to be implemented
	private boolean checkPoints(Player player) {
		return true;
	}
}
