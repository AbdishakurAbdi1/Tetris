//- beskriver hvilke metoder kontrolleren trenger
package no.uib.inf101.tetris.controller;

import no.uib.inf101.tetris.model.GameState;

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

    /**
     * En metode som roterer Treromino.
     * 
     * @return En kopi av originale Tetromino men med ny fasong.
    */
    boolean rotateTetromino();

    /**
     * Dropper den fallende Tetromino helt til bunnen av brettet når man bruker space.
     */
    void dropTetromino();


    /**
     * Henter den nåværende Game State.
     * 
     * @return Returnerer et objekt av typen GameState som representerer nåværende state.
     */
    GameState getGameState ();
}
