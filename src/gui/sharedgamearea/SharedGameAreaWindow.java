package gui.sharedgamearea;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;

//Singleton
public class SharedGameAreaWindow extends JFrame {
	private static SharedGameAreaWindow instance = null;
	private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private Dimension boardSize;
	
	public SharedGameAreaWindow() {
		super("MyShelfie");
		this.setLayout(new FlowLayout());
		Init();
	}
	
	public static SharedGameAreaWindow getInstance() {
		if(instance == null)
			instance = new SharedGameAreaWindow();
		return instance;
	}
	
	public Dimension getBoardSize() {
		return boardSize;
	}
	
	private void Init() {
		this.boardSize = new Dimension(screenSize.getSize().height*2/3, screenSize.getSize().height*2/3);
		this.setResizable(false);
		this.setSize(screenSize.width/2, screenSize.height-40);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setBackground(Color.DARK_GRAY);
		this.setLocation(0, 0);
	}
}
