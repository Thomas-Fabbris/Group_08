package gui.personalgamearea;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JPanel;

public class BookshelfTileContainer extends JPanel {
	
	private GridBagConstraints gbc;
	private int row;
	private int column;
	
	public BookshelfTileContainer() {
		this.gbc = new GridBagConstraints();
		this.row = 0;
		this.column = 0;
	}
	
	@Override
	public Component add(Component comp) {
		
		if(column > 4) {
        	column = 0;
        	row++;
        }
        
        gbc.gridx = column++;
        gbc.gridy = row;
        gbc.insets = new Insets(0, 0, 20, 10); //top, left, bottom, right
        
		addImpl(comp, gbc, -1);
        return comp;
    }
}
