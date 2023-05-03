//package view.sharedgamearea;
//
//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.FlowLayout;
//import java.awt.Toolkit;
//
//import javax.swing.JFrame;
//
//public class SharedGameAreaWindow extends JFrame {
//	private final Dimension screenSize;
//	private Dimension boardSize;
//	
//	public SharedGameAreaWindow(Dimension screenSize) {
//		super("MyShelfie");
//		this.screenSize = screenSize;
//		this.setLayout(new FlowLayout());
//		Init();
//	}
//	
//	public Dimension getBoardSize() {
//		return boardSize;
//	}
//	
//	private void Init() {
//		this.boardSize = new Dimension(screenSize.getSize().height*2/3, screenSize.getSize().height*2/3);
//		this.setResizable(false);
//		this.setSize(screenSize.width/2, screenSize.height-40);
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		this.getContentPane().setBackground(Color.DARK_GRAY);
//		this.setLocation(0, 0);
//	}
//}
