package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

import view.commongamearea.BoardTileLabel;

public class CommonGameAreaFrame extends JFrame {

	private Dimension screenSize;
	private JLabel boardLabel;
	private BoardTileLabel[][] boardTileLabels;
	private JLabel endOfGameTile;
	
	//Common objective cards
	private JLabel card1;
	private JLabel card2;
	
	//Tiles that on the real board-game would go on the common objective cards
	private JLabel card1PointTile;
	private JLabel card2PointTile;
	
	public CommonGameAreaFrame(Dimension screenSize) {
		super("MyShelfie");
		
		this.screenSize = screenSize;
		this.setLayout(new FlowLayout());
		Init();
		
		//Initialise the board
		this.boardLabel = new JLabel();
		this.boardLabel.setSize(screenSize.getSize().height*2/3 - 20, screenSize.getSize().height*2/3 - 20);
		this.boardLabel.setIcon(ImageUtils.loadImageAsIcon(boardLabel.getWidth(), boardLabel.getHeight(), "Assets/Board.jpg"));

		//Initialise endOfGameTile, rotate it and then add it to the board
		int rotation = 15;
		
		this.endOfGameTile = new JLabel();
		this.endOfGameTile.setBounds((int)(boardLabel.getWidth() / 1.23), (int)(boardLabel.getHeight() / 1.42), boardLabel.getHeight()/10, boardLabel.getHeight()/10);
		this.endOfGameTile.setIcon(ImageUtils.rotateIcon(ImageUtils.loadImageAsIcon(endOfGameTile.getSize(), "Assets/Point_tiles/First_to_finish.jpg"), rotation));
		
		this.boardLabel.add(endOfGameTile);
		
		//Initialise both cards
		this.card1 = new JLabel();
		this.card2 = new JLabel();
		
		this.card1.setSize(this.getHeight() * 3/8, this.getHeight() / 4); //TODO: controller should have the responsibility of deciding and setting the icon
		this.card2.setSize(this.getHeight() * 3/8, this.getHeight() / 4); //TODO: controller should have the responsibility of deciding and setting the icon
		
		this.card1PointTile = new JLabel();
		this.card2PointTile = new JLabel();
		
		this.card1PointTile.setBounds((int)(card1.getWidth() / 1.7), (int)(card1.getHeight() / 3.55),
				(int)(card1.getWidth() / 3.55), (int)(card1.getWidth() / 3.55));
		
		this.card2PointTile.setBounds((int)(card2.getWidth() / 1.7), (int)(card2.getHeight() / 3.55),
				(int)(card2.getWidth() / 3.55), (int)(card2.getWidth() / 3.55));
		
		this.add(boardLabel);
		this.add(card1);
		this.add(card2);
		this.card1.add(card1PointTile);
		this.card2.add(card2PointTile);
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
	
	public JLabel getCard1PointTile() {
		return card1PointTile;
	}

	public JLabel getCard2PointTile() {
		return card2PointTile;
	}

	public BoardTileLabel[][] getBoardTiles() {
		return this.boardTileLabels;
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
