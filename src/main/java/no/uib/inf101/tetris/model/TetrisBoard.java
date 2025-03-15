//– representerer rutenettet brettet består av.
package no.uib.inf101.tetris.model;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.Grid;
import no.uib.inf101.grid.GridDimension;

/**
 * Representerer brettet i Tetris-spillet.
 * Denne klassen håndterer hvordan celler legges til og fjernes.
 */
public class TetrisBoard extends Grid {
    /**
     * Oppretter et nytt Tetris-brett med spesifisert antall rader og kolonner.
     *
     * @param rows  Antall rader i brettet.
     * @param cols  Antall kolonner i brettet.
     * @param super for å arve konskuktøren i Grid som den er definert.
     */
    public TetrisBoard(int rows, int cols) {
        super(rows, cols, '-'); // standard tom celle er definert som "-".
    }

    /**
     * En strengrepresentasjon av brettet i et lesbart format.
     * Brukes for testing.
     *
     * @return en strengrepresentasjon av brettet
     */
    public String prettyString() {
        StringBuilder sb = new StringBuilder(); //StringBuilder gjør string mutable.
        for (int row = 0; row < rows(); row++) {
            for (int col = 0; col < cols(); col++) {
                sb.append(get(new CellPosition(row, col))); // get henter riktig char for hvert celle
            }
            if (row != rows()-1) {
                sb.append("\n"); // ny linje for hvert row
            }
        }
        return sb.toString(); // Konverterer StringBuilder objketet til en vanlig String objekt og retunerer
                              // det
    }

    
    /**
     * @return denne instansen siden den implementerer GridDimension.
     */
    public GridDimension getDimension() {
        return this;
    }

    @Override
    public boolean positionIsOnGrid(CellPosition pos) {
        return pos.row() >= 0 && pos.row() < this.rows() &&
                pos.col() >= 0 && pos.col() < this.cols();
    }

    /**
     * Fjerner alle fulle rader.
     * 
     * @return variabelen som holder antall rader som ble fjernet.
     */
    public int clearFilledRows() {
        int removedRows = 0;
        for (int row = rows() - 1; row >= 0;) {
            if (isRowFilled(row)) {
                System.out.println("Removing" + row);
                removeRow(row);
                removedRows++;
            } else {
                row--;
            }
        }
        return removedRows;
    }

    /**
     * Sjekker om en rad er full.
     * 
     * @param row Raden som skal sjekkes.
     * @return True hvis raden er full, ellers false.
     */
    private boolean isRowFilled(int row) {
        for (int col = 0; col < cols(); col++) {
            if (get(new CellPosition(row, col)) == '-') {
                return false;
            }
        }
        return true;
    }

    /**
     * Fjerner en spesifisert rad ved å forskyve radene over den nedover.
     * 
     * @param row Raden som skal fjernes.
     */
    private void removeRow(int row) {
        for (int r = row; r > 0; r--) {
            copyRowTo(r - 1, r);
        }
        fillTopRowWithEmpty(0);
        System.out.println("Fjerner rad: " + row);
        System.out.println("Brett etter fjerning:\n" + prettyString());
    }

    /**
     * Kopierer innholdet fra en rad til en annen.
     * 
     * @param originalRow Raden som kopieres fra.
     * @param targetRow   Raden som kopieres til.
     */
    private void copyRowTo(int originalRow, int targetRow) {
        for (int col = 0; col < cols(); col++) {
            set(new CellPosition(targetRow, col), get(new CellPosition(originalRow, col)));
        }
        System.out.println("Kopierer rad " + originalRow + " til " + targetRow);
    }

    /**
     * Fyller den øverste raden med tomme celler.
     * 
     * @param row Raden som skal tømmes.
     */
    private void fillTopRowWithEmpty(int row) {
        for (int col = 0; col < cols(); col++) {
            set(new CellPosition(row, col), '-');
        }
    }
}