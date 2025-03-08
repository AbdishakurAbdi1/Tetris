//– representerer rutenettet brettet består av.
package no.uib.inf101.tetris.model;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.Grid;
import no.uib.inf101.grid.GridDimension;


/**
 * Representerer brettet i Tetris-spillet.
 * Denne klassen håndterer hvordan celler legges til og fjernes, 
 * for eksempel når en rad fylles opp og forsvinner.
 * Arver fra 'Grid'.
 */
public class TetrisBoard extends Grid{
   /**
     * Oppretter et nytt Tetris-brett med spesifisert antall rader og kolonner.
     *
     * @param rows Antall rader i brettet.
     * @param cols Antall kolonner i brettet. 
     * @param super for å arve konskuktøren i Grid som den er definert.
     */
    public TetrisBoard(int rows, int cols) {
        super(rows, cols,'-'); //standard tom celle begynner med "-"
        set(new CellPosition(0, 0), 'r'); // Rød i øvre venstre hjørne
        set(new CellPosition(rows()-1, 0), 'b'); // Blå i nedre venstre hjørne
        set(new CellPosition(0, cols()-1), 'y'); // Gul i øvre høyre hjørne
        set(new CellPosition(rows()-1, cols()-1), 'w');   // Hvit i nedre høyre hjørne
    }

    /**
     * A string representation of the board in a readable format.
     * For testing purposes.
     *
     * @return a string representation of the board
     * StringBuilder kommer fra Java’s standardbibliotek,
     * den gjør string mutable 
     */
    public String prettyString(){
        StringBuilder sb= new StringBuilder();
        for (int row=0; row<rows(); row++) {
            for (int col=0; col<cols();col++) {
                sb.append(get(new CellPosition(row, col))); // get henter riktig char for hvert celle 
            }
            sb.append("\n"); //ny linje for hvert row
        }
        return sb.toString(); // Konverterer StringBuilder objketet til en vanlig String objekt og retunerer det
    }

    public GridDimension getDimension() {
        return this; // Returnerer seg selv fordi det implementerer GridDimension
    }


    /**
     * Sjekker om en gitt posisjon er innenfor brettets grenser.
     *
     * @param pos Posisjonen som skal sjekkes.
     * @return "true" hvis posisjonen er innenfor brettet, ellers false".
     */
    public boolean contains(CellPosition pos) {
        return pos.row() >= 0 && pos.row() < this.rows() &&
               pos.col() >= 0 && pos.col() < this.cols();
    }
}
