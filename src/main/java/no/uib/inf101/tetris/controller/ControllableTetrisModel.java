//- beskriver hvilke metoder kontrolleren trenger
package no.uib.inf101.tetris.controller;

/**
 * Et Interface som spesifiserer metoden for å flytte den fallende tetromino.
 */
public interface ControllableTetrisModel {
    /**
     * Forsøker å flytte den fallende tetrominoen.
     * 
     * @param deltaRow Antall rader å flytte (posetiv verdi = nedover).
     * @param deltaCol Antall kolloner å flytte (posetiv verdi = høyre, negativ verdi = venstre).
     * @return "true" vist flyttingen var gjennomført, ellers false vist ikke.
     */
    boolean moveTetromino(int deltaRow, int deltaCol);
}
