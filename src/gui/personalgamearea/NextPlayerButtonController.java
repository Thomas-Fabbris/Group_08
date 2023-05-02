package gui.personalgamearea;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class NextPlayerButtonController implements MouseListener {

	//Using a JLabel instead of a button gives us more control on the icon
	JLabel label;
	ImageIcon pressedIcon;
	ImageIcon releasedIcon;
	
	public NextPlayerButtonController(JLabel label, ImageIcon pressedIcon, ImageIcon releasedIcon) {
		this.label = label;
		this.pressedIcon = pressedIcon;
		this.releasedIcon = releasedIcon;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		this.label.setIcon(pressedIcon);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		this.label.setIcon(releasedIcon);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

}
