package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;

//Singleton
public class SharedGameAreaWindow extends JFrame {
	private static SharedGameAreaWindow instance = null;
	private final Dimension screen_size = Toolkit.getDefaultToolkit().getScreenSize();
	private Dimension board_size;
	private BoardLabel board;
	private CommonObjectiveCardContainer cards;
	
	public SharedGameAreaWindow() {
		super("MyShelfie");
		this.setLayout(new FlowLayout());
		Init();
		
		cards = new CommonObjectiveCardContainer();
//		cards.addCard(1);
//		cards.addCard(5);
		this.add(cards);
	}
	
	public static SharedGameAreaWindow getInstance() {
		if(instance == null)
			instance = new SharedGameAreaWindow();
		return instance;
	}
	
	public Dimension getBoardSize() {
		return board_size;
	}
	
	public CommonObjectiveCardContainer getCards() {
		return cards;
	}
	
	private void Init() {
		this.board_size = new Dimension(screen_size.getSize().height*2/3, screen_size.getSize().height*2/3);
		this.setResizable(false);
		this.setSize(screen_size.width/2, screen_size.height-40);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setBackground(Color.DARK_GRAY);
		this.setLocation(0, 0);
	}
}
