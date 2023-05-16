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
	private JLabel card1;
	private JLabel card2;

	// Tiles that, on the real board-game, would go on the common objective cards
	private JLabel card1PointTile;
	private JLabel card2PointTile;

	// Elements to display the tiles selected by the player
	private JPanel selectedTiles;
	private JLabel selectedTile1;
	private JLabel selectedTile2;
	private JLabel selectedTile3;

	public CommonGameAreaFrame(Dimension screenSize) {
		super("MyShelfie");

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
		this.card1 = new JLabel();
		this.card2 = new JLabel();

		this.card1.setSize(this.getHeight() * 3 / 8, this.getHeight() / 4);
		this.card2.setSize(this.getHeight() * 3 / 8, this.getHeight() / 4);

		this.card1PointTile = new JLabel();
		this.card2PointTile = new JLabel();

		this.card1PointTile.setBounds((int) (card1.getWidth() / 1.7), (int) (card1.getHeight() / 3.55),
				(int) (card1.getWidth() / 3.55), (int) (card1.getWidth() / 3.55));

		this.card2PointTile.setBounds((int) (card2.getWidth() / 1.7), (int) (card2.getHeight() / 3.55),
				(int) (card2.getWidth() / 3.55), (int) (card2.getWidth() / 3.55));

		// Initialise selected tiles elements
		this.selectedTiles = new JPanel();
		this.selectedTiles.setLayout(new GridLayout(3, 0));
		this.selectedTiles.setSize(endOfGameTile.getWidth(), endOfGameTile.getHeight() * 3);
		this.selectedTiles.setOpaque(false);

		this.selectedTile1 = new JLabel();
		this.selectedTile2 = new JLabel();
		this.selectedTile3 = new JLabel();
		this.selectedTile1.setSize(endOfGameTile.getSize());
		this.selectedTile2.setSize(endOfGameTile.getSize());
		this.selectedTile3.setSize(endOfGameTile.getSize());

		// Add all elements
		this.selectedTiles.add(selectedTile1);
		this.selectedTiles.add(selectedTile2);
		this.selectedTiles.add(selectedTile3);

		this.add(boardLabel);
		this.add(selectedTiles);
		this.card1.add(card1PointTile);
		this.card2.add(card2PointTile);
		this.add(card1);
		this.add(card2);
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

	public JPanel getSelectedTiles() {
		return selectedTiles;
	}

	public JLabel getSelectedTile1() {
		return selectedTile1;
	}

	public JLabel getSelectedTile2() {
		return selectedTile2;
	}

	public JLabel getSelectedTile3() {
		return selectedTile3;
	}
}
