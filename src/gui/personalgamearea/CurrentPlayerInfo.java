package gui.personalgamearea;

import javax.swing.JPanel;

import gui.PointTileLabel;

public class CurrentPlayerInfo {
	private JPanel panel;
	private BookshelfLabel bookshelfLabel;
	private PersonalObjectiveCardLabel personalObjectiveCardLabel;
	private PointTileLabel pointTile1;
	private PointTileLabel pointTile2;
	
	public CurrentPlayerInfo() {
		this.panel = new JPanel();
		this.panel.setOpaque(false);
	}

	public PointTileLabel getPointTile1() {
		return pointTile1;
	}

	public void setPointTile1(PointTileLabel pointTile1) {
		this.pointTile1 = pointTile1;
	}

	public PointTileLabel getPointTile2() {
		return pointTile2;
	}

	public void setPointTile2(PointTileLabel pointTile2) {
		this.pointTile2 = pointTile2;
	}

	public BookshelfLabel getBookshelfLabel() {
		return bookshelfLabel;
	}

	public void setBookshelfLabel(BookshelfLabel bookshelfLabel) {
		
		if(this.bookshelfLabel != null)
			panel.remove(this.bookshelfLabel);
		
		this.bookshelfLabel = bookshelfLabel;
		panel.add(bookshelfLabel);
	}

	public PersonalObjectiveCardLabel getPersonalObjectiveCardLabel() {
		return personalObjectiveCardLabel;
	}

	public void setPersonalObjectiveCardLabel(PersonalObjectiveCardLabel personalObjectiveCardLabel) {
		
		if(this.personalObjectiveCardLabel != null)
			panel.remove(this.personalObjectiveCardLabel);
		
		this.personalObjectiveCardLabel = personalObjectiveCardLabel;
		panel.add(personalObjectiveCardLabel);
	}
	
	public JPanel getPanel() {
		return panel;
	}
}
