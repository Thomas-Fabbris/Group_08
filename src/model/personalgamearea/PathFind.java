package model.personalgamearea;

import java.util.Arrays;

import model.shared.TileType;
/**
 * The class {@code PathFind} finds groups of tiles of the same type in the bookshelf
 * 
 */
public class PathFind {

    private Bookshelf pshelf;
    private int[] checked_places;
    private int checked_index;
    private int[] groups;
    private int groups_index;
    private int group_count;
    private boolean started_search;

    public PathFind(Bookshelf bookshelf)
    {
    	if(bookshelf == null) {
    		throw new NullPointerException("pshelf cannot be set to null while creating a PathFind instance!");
    	}
        this.pshelf = bookshelf;
        this.checked_places = new int[30];
        Arrays.fill(checked_places, -99);
        this.checked_index = 0;
        this.groups = new int[30];
        this.groups_index = -1;
        this.group_count = 1;
        this.started_search = false;
    }

    /**
     * Function that turns 2 numbers (row and col) into a single number
     * easier to control {@code inCoords}
     *
     * @param row
     * @param col
     * @return row * 10 + col
     */
    private int toCoords(int row, int col)
    {
        return row * 10 + col;
    }

    /**
     * Check function to see whether the past coordinates have already
     * been checked previously
     *
     * @param row
     * @param col
     * @return boolean (true -> already checked, false -> no)
     */
    private boolean inCoords(int row, int col)
    {
        for (int tile : this.checked_places)
        {
            if (toCoords(row, col) == tile) return true;
        }
        return false;
    }

    /**
     * This function is used to return an array consisting only of the number
     * of groups with composed of more than 2 equal tiles. For example:
     * INPUT: {1, 1, 2, 4, 5, 3, 2, 2} =>
     * OUTPUT: {4, 5, 3}
     *
     * @return the beautified array
     */
    private int[] Beautify()
    {
        int count = 0;

        for (int group : this.groups)
        {
            if (group > 2) count++;
        }

        int[] result = new int[count];
        int result_index = 0;

        for (int i = 0; i < 30; i++)
        {
            if (this.groups[i] > 2)
            {
                result[result_index] = this.groups[i];
                result_index++;
            }
        }

        return result;

    }

    /**
     * This function serves as the basis for the Pathfinder function.
     * Its job is to decide the tile from which to start searching
     * and handle useful variables for counting the number of groups
     *
     * @return the array of all the groups found in the shelf
     */
    public int[] PointsPathfinding()
    {

        for (int row = 0; row <= 5; row++)
        {
            for (int col = 0; col <= 4; col++)
            {

                // Saltiamo le tile che abbiamo già controllato
                if (inCoords(row, col)) continue;

                // Prendiamo il tipo della tile da cui iniziamo a fare pathfinding
                TileType tile = this.pshelf.getTile(row, col).getType();

                // Non eseguire l'operazione se il tipo della tile è NULL
                if(tile == TileType.NULL)
                	continue;
                
                // Comincia a cercare a partire dalla tile di tipo _tile_ e
                // in posizione row e col
                this.Pathfinder(tile, row, col);
                // Una volta hce il pathfinder non è più in grado di trovare
                // tiles adiacenti la "ricerca" viene messa in pausa
                this.started_search = false;
                // Il numero di componenti nel gruppo viene aggiunto nell'array
                // dei gruppi
                this.groups[this.groups_index] = this.group_count;
                // E il conteggio dei gruppi e ri-portato a zero
                this.group_count = 1;

            }
        }

        // Vedi documentazione del metodo Beautify
        return Beautify();

    }

    /**
     * This function does pathfinding for all tiles in the array that are of the same type
     * of the argument that is passed by {@code PointsPathfinding}.
     * Pathfinder will check the tiles following this order:
     * 1) below 2) right 3) above 4) left
     *
     * @param tile
     * @param row
     * @param col
     */
    private void Pathfinder(TileType tile, int row, int col)
    {

        // Se la tile dove siamo è stata già controllata in un gruppo allora andiamo avanti
        if (inCoords(row, col)) return;

        // Comincia il conteggio di un nuovo gruppo, quindi l'indice
        // dei gruppi aumenta di uno
        if (!this.started_search)
        {
            // Se è la prima volta che
            this.started_search = true;
            this.groups_index++;
        }

        // Segnamo le caselle dove siamo già passati nell'array checked_places
        this.checked_places[this.checked_index] = toCoords(row, col);
        this.checked_index++;

        // Non andiamo oltre i bordi dell'ultima riga
        if (row != 5)
        {
            // CHECK DOWN
            // Se il tipo di tile è uguale a quella della tile da dove veniamo ci spostiamo
            // nella nuova tile
            boolean t1 = inCoords(row + 1, col);
            if (this.pshelf.getTile(row + 1, col).getType() == tile && !t1)
            {
                // Aumentiamo di uno il numero di componenti del gruppo
                this.group_count++;
                // Ci spostiamo nella prossima tile, lo stesso vale per le altre
                Pathfinder(tile, row + 1, col);
            }
        }

        // Non andiamo oltre i bordi dell'ultima colonna
        if (col != 4)
        {
            // CHECK RIGHT
            boolean t1 = inCoords(row, col + 1);
            if (this.pshelf.getTile(row, col + 1).getType() == tile && !t1)
            {
                this.group_count++;
                Pathfinder(tile, row, col + 1);
            }
        }

        // Non andiamo oltre i bordi della prima riga
        if (row != 0)
        {
            // CHECK UP
            boolean t1 = inCoords(row - 1, col);
            if (this.pshelf.getTile(row - 1, col).getType() == tile && !t1)
            {
                this.group_count++;
                Pathfinder(tile,row - 1, col);
            }
        }

        // Non andiamo oltre i bordi della prima colonna
        if (col != 0)
        {
            // CHECK LEFT
            boolean t1 = inCoords(row, col - 1);
            if (this.pshelf.getTile(row, col - 1).getType() == tile && !t1)
            {
                this.group_count++;
                Pathfinder(tile, row, col - 1);
            }
        }
    }
    

	/**
	 * The method {@code mapTilesGroupSizeToPoints} returns the number of points rewarded for having x adjacent tiles on the bookshelf
	 * @param numberOfAdjacentTiles number of adjacent tiles in a group on the bookshelf
	 * @return
	 */
	public int mapTilesGroupSizeToPoints(int numberOfAdjacentTiles) {
		// player gets 2 points total
		if(numberOfAdjacentTiles == 3)
			return 2;
		
		// player gets 3 points total
		if(numberOfAdjacentTiles == 4)
			return 3; 
		
		// player gets 5 points total
		if(numberOfAdjacentTiles == 5)
			return 5;
		
		//player gets 8 points total
		if(numberOfAdjacentTiles >= 6)
			return 8;
		
		return 0;
	}

}
