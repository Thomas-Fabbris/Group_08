package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import view.ImageUtils;

public class nextPlayerButtonController implements MouseListener {

	JLabel button;
	ImageIcon defaultStateIcon;
	ImageIcon pressedStateIcon;
	MainController mainController;

	public nextPlayerButtonController(JLabel button, MainController mainController) {
		this.button = button;
		this.defaultStateIcon = (ImageIcon) button.getIcon();
		this.pressedStateIcon = ImageUtils.loadImageAsIcon(defaultStateIcon.getIconWidth(),
				defaultStateIcon.getIconHeight(), "Assets/ArrowButton/ArrowButtonPressed.png");
		this.mainController = mainController;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int currentPlayerId = mainController.getCurrentPlayer().getId();
		int nextId = currentPlayerId + 1;

		if (nextId > mainController.getLastPlayer().getId())
			nextId = 0;

		mainController.setCurrentPlayer(mainController.getPlayer(nextId));

	}

	@Override
	public void mousePressed(MouseEvent e) {
		button.setIcon(pressedStateIcon);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		button.setIcon(defaultStateIcon);
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

}
