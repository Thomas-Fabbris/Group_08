package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.commongamearea.Board;
import view.commongamearea.BoardTileLabel;

public class CommonGameAreaFrame extends JFrame {

	private static final long serialVersionUID = -4736645517826828496L;
	private Dimension screenSize;
	private JLabel boardLabel;
	private BoardTileLabel[][] boardTileLabels;
	private JLabel endOfGameTile;

	// Common objective cards
	private JLabel[] commonObjectiveCards;

	// Tiles that, on the real board-game, would go on the common objective cards
	private JLabel[] pointTiles;

	// Elements to display the tiles selected by the player
	private JPanel selectedTilesPanel;
	private JLabel[] selectedTiles;

	public CommonGameAreaFrame(Dimension screenSize) {
		super("MyShelfie");
		if(screenSize == null) {
			throw new NullPointerException("screenSize cannot be set to null while creating a CommonGameAreaFrame instance!");
		}
		this.screenSize = screenSize;
		this.setLayout(new FlowLayout());
		Init();

		// Initialise the board
		this.boardLabel = new JLabel();
		this.boardLabel.setSize(screenSize.getSize().height * 2 / 3 - 20, screenSize.getSize().height * 2 / 3 - 20);
		this.boardLabel
				.setIcon(ImageUtils.loadImageAsIcon(boardLabel.getWidth(), boardLabel.getHeight(), "Assets/Board.jpg"));

		this.boardTileLabels = new BoardTileLabel[Board.BOARD_LENGTH][Board.BOARD_LENGTH];
		
		// Initialise endOfGameTile, rotate it and then add it to the board
		int rotation = 15;

		this.endOfGameTile = new JLabel();
		this.endOfGameTile.setBounds((int) (boardLabel.getWidth() / 1.23), (int) (boardLabel.getHeight() / 1.42),
				boardLabel.getHeight() / 10, boardLabel.getHeight() / 10);
		this.endOfGameTile.setIcon(ImageUtils.rotateIcon(
				ImageUtils.loadImageAsIcon(endOfGameTile.getSize(), "Assets/Point_tiles/First_to_finish.jpg"),
				rotation));

		this.boardLabel.add(endOfGameTile);

		// Initialise both cards
		this.commonObjectiveCards = new JLabel[2];
		
		this.commonObjectiveCards[0] = new JLabel();
		this.commonObjectiveCards[1] = new JLabel();
		
		this.commonObjectiveCards[0].setSize(this.getHeight() * 3 / 8, this.getHeight() / 4);
		this.commonObjectiveCards[1].setSize(this.getHeight() * 3 / 8, this.getHeight() / 4);

		this.pointTiles = new JLabel[2];
		
		pointTiles[0] = new JLabel();
		pointTiles[1] = new JLabel();
		
		pointTiles[0].setBounds((int) (commonObjectiveCards[0].getWidth() / 1.7), (int) (commonObjectiveCards[0].getHeight() / 3.55),
				(int) (commonObjectiveCards[0].getWidth() / 3.55), (int) (commonObjectiveCards[0].getWidth() / 3.55));
		
		pointTiles[1].setBounds((int) (this.commonObjectiveCards[1].getWidth() / 1.7), (int) (this.commonObjectiveCards[1].getHeight() / 3.55),
				(int) (this.commonObjectiveCards[1].getWidth() / 3.55), (int) (this.commonObjectiveCards[1].getWidth() / 3.55));

		// Initialise selected tiles elements
		this.selectedTilesPanel = new JPanel();
		this.selectedTilesPanel.setLayout(new GridLayout(3, 2));
		this.selectedTilesPanel.setSize(endOfGameTile.getWidth(), endOfGameTile.getHeight() * 3);
		this.selectedTilesPanel.setOpaque(false);

		this.selectedTiles = new JLabel[3];
		
		this.selectedTiles[0] = new JLabel();
		this.selectedTiles[1] = new JLabel();
		this.selectedTiles[2] = new JLabel();
		this.selectedTiles[0].setSize(endOfGameTile.getSize());
		this.selectedTiles[1].setSize(endOfGameTile.getSize());
		this.selectedTiles[2].setSize(endOfGameTile.getSize());

		// Add all elements
		this.selectedTilesPanel.add(selectedTiles[0]);
		this.selectedTilesPanel.add(selectedTiles[1]);
		this.selectedTilesPanel.add(selectedTiles[2]);

		this.add(boardLabel);
		this.add(selectedTilesPanel);
		this.commonObjectiveCards[0].add(pointTiles[0]);
		this.commonObjectiveCards[1].add(pointTiles[1]);
		this.add(commonObjectiveCards[0]);
		this.add(commonObjectiveCards[1]);
	}

	private void Init() {
		this.setResizable(false);
		this.setSize(screenSize.width / 2, screenSize.height - 40);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setBackground(Color.DARK_GRAY);
		this.setLocation(0, 0);
		this.setVisible(true);
		this.setTitle("MyShelfie - Board");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("./Assets/Icon.png"));
		
	}
	
	public JLabel getPointTile(int index) {
		if(index == 0 || index == 1) {
				return pointTiles[index];
		}
		throw new IllegalArgumentException("index can be either 0 or 1 while calling CommonGameAreaFrame.getPointTile() method!");
	}

	public BoardTileLabel[][] getBoardTilesLabels() {
		return this.boardTileLabels;
	}

	public JLabel getBoardLabel() {
		return this.boardLabel;
	}
	
	/**
	 * Returns the associated label to the specified common objective card (either 0 or 1)
	 * @param index 0 or 1
	 * @return
	 */
	public JLabel getCommonObjectiveCard(int index) {
		if(index == 0 || index == 1) {
			return commonObjectiveCards[index];
	}
	throw new IllegalArgumentException("index can be either 0 or 1 while calling CommonGameAreaFrame.getCommonObjectiveCard() method!");
	}

	public JPanel getSelectedTiles() {
		return selectedTilesPanel;
	}
	
	/**
	 * Returns the selected tile 1, 2 or 3
	 * @param id
	 * @return
	 */
//	public JLabel getSelectedTile(int id) {
//		switch(id) {
//		case 0:
//			return selectedTiles[0];
//			
//		case 1:
//			return selectedTiles[1];
//			
//		case 2:
//			return selectedTiles[2];
//
//		default:
//			throw new IndexOutOfBoundsException("Selected tile with id (index) " +id+ " doesn't exist!");
//		}
//	}

	/**
	 * Returns the selected tile 1, 2 or 3
	 * @param id
	 * @return
	 */
	public JLabel getSelectedTile(int id) {
		if(id < 0 || id > 2) {
			throw new IndexOutOfBoundsException("Selected tile with id (index) " +id+ " doesn't exist!");
		}
			
		return selectedTiles[id];
	}
	
	public int getSelectedTilesLength() {
		return selectedTiles.length;
	}

	public JLabel getEndOfGameTile() {
		return endOfGameTile;
	}
}
