package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class QuitButtonController implements MouseListener {
	/**This method ends the program when QuitButton is clicked
	 * @param e (MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		System.exit(0);
	}
	/**This method has to be implemented according to the {@code MouseListener} interface
	 * @param e (MouseEvent)
	 **/
	@Override
	public void mousePressed(MouseEvent e) {
		
	}
	/**This method has to be implemented according to the {@code MouseListener} interface
	 *  @param e (MouseEvent)
	 **/
	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	/**This method has to be implemented according to the {@code MouseListener} interface
	 *  @param e (MouseEvent)
	 **/
	@Override
	public void mouseEntered(MouseEvent e) {
		
	}
	/**This method has to be implemented according to the {@code MouseListener} interface
	 *  @param e (MouseEvent)
	 **/
	@Override
	public void mouseExited(MouseEvent e) {
		
	}
}
