package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

//Singleton
public class BoardWindow extends JFrame {
	private static BoardWindow instance = null;
	private final Dimension screen_size = Toolkit.getDefaultToolkit().getScreenSize();
	private Dimension board_size;
	
	public BoardWindow() {
		super("MyShelfie");
		Init();
	}
	
	public static BoardWindow getInstance() {
		if(instance == null)
			instance = new BoardWindow();
		return instance;
	}
	
	public Dimension getBoardSize() {
		return board_size;
	}
	
	private void Init() {
		this.board_size = new Dimension(screen_size.getSize().height*2/3, screen_size.getSize().height*2/3);
		this.setResizable(false);
		this.setSize(screen_size.width/2, screen_size.height*2/3);
//		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setBackground(Color.DARK_GRAY);
		this.setLocation(0, 0);
	}
}
