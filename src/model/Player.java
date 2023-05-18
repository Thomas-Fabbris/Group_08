package model;

import java.util.LinkedList;
import java.util.List;

import model.commongamearea.BoardTile;
import model.commongamearea.GameEndTile;
import model.commongamearea.PointTile;
import model.personalgamearea.Bookshelf;
import model.personalgamearea.PersonalObjectiveCard;
import model.shared.IdGenerator;

/**
 * La classe {@code Player} modella un giocatore che partecipa alla partita.
 * 
 * @since 1.0
 *
 */
public class Player {

	private String name;
	public final int id;
	private int points;
	private boolean hasChair = false;
	private boolean hasEndOfGameToken = false;
	private boolean hasCompletedCommonGoal1 = false;
	private boolean hasCompletedCommonGoal2 = false;

	private int selectedColumn = -1; // Identifies which the column on the bookshelf the player is adding the tiles
										// to (value -1 means no column has been decided yet)

	private List<BoardTile> selectedTiles; // List of tiles selected from the board (can have max 3 tiles at a time)
	public final PersonalObjectiveCard objectiveCard;
	public final Bookshelf bookshelf;
	private PointTile pointTile1;
	private PointTile pointTile2;

	/**
	 * Il costruttore definisce una nuova istanza della classe {@code Player}
	 * 
	 * @param name        nome del giocatore
	 * @param idgenerator istanza della classe {@link IdGenerator}
	 */

	public Player(String name, IdGenerator idgenerator) {
		this.name = name;
		this.id = idgenerator.getNewId();
		this.selectedTiles = new LinkedList<BoardTile>();
		objectiveCard = new PersonalObjectiveCard(idgenerator.getNewPersonalObjectiveCardId());
		bookshelf = new Bookshelf(this);
	}

	/**
	 * Il metodo {@code awardPointTile} controlla se il {@link model.Player
	 * giocatore} può ricevere la {@link PointTile PointTile}.<br/>
	 * Se il {@link model.Player giocatore} può riceverla, allora gli vengono
	 * assegnati i punti corrispondenti
	 * 
	 * @param tile {@link PointTile PointTile} da assegnare al giocatore
	 */
	public void awardPointTile(PointTile tile) {

		// If both spots are empty, then fill pointTile1
		if (pointTile1 == null && pointTile2 == null) {
			pointTile1 = tile;
			this.hasCompletedCommonGoal1 = true;
			this.addPoints(tile.getPoints());
			return;
		}

		// If pointTile1 already has a tile from this card, then don't award anything
		if (pointTile1.getCardId() == tile.getCardId())
			return;

		// If pointTile2 is empty and pointTile1 doesn't come from the same card, then
		// fill pointTile2
		if (pointTile2 == null && pointTile1.getCardId() != tile.getCardId()) {
			pointTile2 = tile;
			this.hasCompletedCommonGoal2 = true;
			this.addPoints(tile.getPoints());
		}
	}

	/**
	 * Il metodo {@code getPointTile} restituisce la {@link PointTile PointTile} da
	 * ritornare.
	 * 
	 * @param tileNumber, numero della {@code PointTile PointTile} da ritornare (1
	 *                    oppure 2)
	 * @return pointTile, la {@link PointTile PointTile} richiesta
	 */
	public PointTile getPointTile(int tileNumber) {
		if (tileNumber == 1)
			return this.pointTile1;
		if (tileNumber == 2)
			return this.pointTile2;
		else
			throw new IllegalArgumentException(tileNumber + " is not a valid tile number! Choose tile 1 or 2");
	}

	/**
	 * Il metodo {@code equals} permette di confrontare tra loro due giocatori,
	 * sulla base dell'{@code id} ad essi assegnato automaticamente all'inizio della
	 * partita
	 * 
	 * @param player il {@link Player giocatore} da confrontare con l'istanza
	 *               dell'oggetto che chiama questo metodo
	 * @return {@code true} se i due giocatori confrontati sono gli stessi,
	 *         {@code false} in tutti gli altri casi
	 */
	public boolean equals(Player player) {
		return this.id == player.id;
	}

	/**
	 * Il metodo {@code getName} restituisce il nome del {@link model.Player
	 * giocatore} che lo ha invocato
	 * 
	 * @return il nome del {@link model.Player giocatore}
	 */

	public String getName() {
		return this.name;
	}

	/**
	 * Il metodo{@code addPoints} serve per assegnare {@link model.Player giocatore}
	 * dei nuovi punti
	 * 
	 * @param points i punti da assegnare al {@link model.Player giocatore}
	 */

	public void addPoints(int points) {
		this.points += points;
	}

	/**
	 * Il metodo {@code getPoints} restituisce i punti del {@link model.Player
	 * giocatore} che lo ha invocato
	 * 
	 * @return i punti correnti del {@link model.Player giocatore}
	 */
	public int getPoints() {
		return this.points;
	}

	/**
	 * Il metodo {@code hasEndOfGameToken} serve per vedere se il
	 * {@link model.Player giocatore} che ha invocato il metodo è stato lui ad
	 * innescare la fine della partita
	 * 
	 * @return {@code true} se il {@link model.Player giocatore} possiede il
	 *         {@link GameEndTile gettone di fine partita}
	 */
	public boolean hasEndOfGameToken() {
		return this.hasEndOfGameToken;
	}

	public void setEndOfGameToken(boolean hasEndOfGameToken) {
		this.hasEndOfGameToken = hasEndOfGameToken;
	}

	/**
	 * Il metodo {@code getObjectiveCard} restituisce la
	 * {@link PersonalObjectiveCard} posseduta {@link model.Player giocatore}
	 * durante la partita corrente
	 * 
	 * @return la {@link PersonalObjectiveCard} del {@link model.Player giocatore}
	 */
	public PersonalObjectiveCard getObjectiveCard() {
		return objectiveCard;
	}

	/**
	 * Il metodo {@code hasEndOfGameToken} serve per vedere se il
	 * {@link model.Player giocatore} che ha invocato il metodo è il primo a
	 * cominciare la partita
	 * 
	 * @return {@code true} se il {@link model.Player giocatore} ha cominciato la
	 *         partita attuale
	 */
	public boolean hasChair() {
		return hasChair;
	}

	public void setHasChair(boolean hasChair) {
		this.hasChair = hasChair;
	}

	public PointTile getPointTile1() {
		return pointTile1;
	}

	public void setPointTile1(PointTile pointTile1) {
		this.pointTile1 = pointTile1;
	}

	public PointTile getPointTile2() {
		return pointTile2;
	}

	public void setPointTile2(PointTile pointTile2) {
		this.pointTile2 = pointTile2;
	}

	public int getId() {
		return id;
	}

	public Bookshelf getBookshelf() {
		return bookshelf;
	}

	public boolean hasCompletedCommonGoal1() {
		return hasCompletedCommonGoal1;
	}

	public void setHasCompletedCommonGoal1(boolean hasCompletedCommonGoal1) {
		this.hasCompletedCommonGoal1 = hasCompletedCommonGoal1;
	}

	public boolean hasCompletedCommonGoal2() {
		return hasCompletedCommonGoal2;
	}

	public void setHasCompletedCommonGoal2(boolean hasCompletedCommonGoal2) {
		this.hasCompletedCommonGoal2 = hasCompletedCommonGoal2;
	}

	public List<BoardTile> getSelectedTiles() {
		return selectedTiles;
	}

	public BoardTile getSelectedTile(int id) {
		return selectedTiles.get(id);
	}

	/**
	 * Returns the column selected by the player while inserting the tiles in the
	 * bookshelf. Value -1 means that the player has not yet selected a column.
	 * 
	 * @return
	 */
	public int getSelectedColumn() {
		return selectedColumn;
	}

	/**
	 * Set the column selected by the player when inserting a tile in the bookshelf.
	 * 
	 * @param bookshelfColumn which column the player is inserting the tiles into.
	 *                        Must be 0, 1, 2, 3 or 4.
	 */
	public void setSelectedColumn(int bookshelfColumn) {
		if (bookshelfColumn < 0 || bookshelfColumn > 4) {
			throw new IllegalArgumentException(
					"The player must select a column between 0 and 4 (inclusive): " + bookshelfColumn);
		}
		this.selectedColumn = bookshelfColumn;
	}

	/**
	 * Reset the flag to -1, which means that the player has not yet selected a row
	 */
	public void resetSelectedColumn() {
		this.selectedColumn = -1;
	}

	/**
	 * Returns true if the player has already added a tile to the bookshelf (which
	 * means the player has selected a column to insert the tiles into)
	 * 
	 * @return
	 */
	public boolean hasSelectedColumn() {
		return this.selectedColumn != -1;
	}
}