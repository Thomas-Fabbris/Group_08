package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;

import view.commongamearea.BoardLabel;
import view.commongamearea.CommonObjectiveCardLabel;

public class CommonGameAreaFrame extends JFrame {

	private Dimension screenSize;
	private BoardLabel boardLabel;
	private CommonObjectiveCardLabel card1;
	private CommonObjectiveCardLabel card2;
	
	public CommonGameAreaFrame(Dimension screenSize) {
		super("MyShelfie");
		
		this.screenSize = screenSize;
		this.setLayout(new FlowLayout());
		Init();
		
		this.boardLabel = new BoardLabel(new Dimension(screenSize.getSize().height*2/3, screenSize.getSize().height*2/3));
		this.card1 = new CommonObjectiveCardLabel(this.getSize()); //TODO: controller should have the reponsibility of deciding and setting the icon
		this.card2 = new CommonObjectiveCardLabel(this.getSize());
		
		
		this.add(boardLabel);
		this.add(card1);
		this.add(card2);
		//TODO: set the card1 and card2 pointTiles' icons from a controller
	}
	
	private void Init() {
		this.setResizable(false);
		this.setSize(screenSize.width/2, screenSize.height-40);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setBackground(Color.DARK_GRAY);
		this.setLocation(0, 0);
		this.setVisible(true);
	}
	
	public BoardLabel getBoardLabel() {
		return this.boardLabel;
	}
	
	public CommonObjectiveCardLabel getCard1Label() {
		return this.card1;
	}
	
	public CommonObjectiveCardLabel getCard2Label() {
		return this.card2;
	}
}
