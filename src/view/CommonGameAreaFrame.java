package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class CommonGameAreaFrame extends JFrame {

	private Dimension screenSize;
	private JLabel boardLabel;
	private JLabel card1;
	private JLabel card2;
	
	public CommonGameAreaFrame(Dimension screenSize) {
		super("MyShelfie");
		
		this.screenSize = screenSize;
		this.setLayout(new FlowLayout());
		Init();
		
		//Initialise the board
		this.boardLabel = new JLabel();
		this.boardLabel.setSize(screenSize.getSize().height*2/3 - 20, screenSize.getSize().height*2/3 - 20);
		this.boardLabel.setIcon(Utils.LoadImageAsIcon(boardLabel.getWidth(), boardLabel.getHeight(), "Assets/Board.jpg"));
		
		//Initialise both cards
		this.card1 = new JLabel();
		this.card2 = new JLabel();
		
		this.card1.setSize(this.getHeight() * 3/8, this.getHeight() / 4); //TODO: controller should have the responsibility of deciding and setting the icon
		this.card2.setSize(this.getHeight() * 3/8, this.getHeight() / 4); //TODO: controller should have the responsibility of deciding and setting the icon
		
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
	
	public JLabel getBoardLabel() {
		return this.boardLabel;
	}
	
	public JLabel getCard1Label() {
		return this.card1;
	}
	
	public JLabel getCard2Label() {
		return this.card2;
	}
}
