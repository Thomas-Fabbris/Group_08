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

    private int toCoords(int row, int col)
    {
        // Funzione che trasforma 2 numeri (row e col) in un numero singolo
        // più facile da controllare in inCoords

        return row * 10 + col;
    }

    private boolean inCoords(int row, int col)
    {
        // Funzione di check per vedere se le coordinate passate sono già
        // state controllate in precedenza

        for (int tile : this.checked_places)
        {
            if (toCoords(row, col) == tile) return true;
        }
        return false;
    }

    private int[] Beautify()
    {
        // Questa funzione serve per ritornare un array composto solamente dal numero
        // di gruppi con composto da più di 2 tile uguali

        // Esempio pratico:
        // INPUT:  {1, 1, 2, 4, 5, 3, 2, 2}
        // OUTPUT: {4, 5, 3}

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

    public int[] PointsPathfinding()
    {
        // Questa funzione serve da base per la funziona Pathfinder
        // Il suo compito è quello di decidere la tile da cui partire a cercare
        // e gestire variabili utili per contare il numero di gruppi

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

    private void Pathfinder(TileType tile, int row, int col)
    {
        // Questa funzione fa pathfinding per tutte le tessere dell'array che sono dello stesso tipo
        // dell'argomento che viene passato da PointsPathfinding.
        // Per come è stato scritto Pathfinder controllerà le tessere seguendo questo ordine:
        // 1) sotto 2) destra 3) sopra 4) sinistra

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
