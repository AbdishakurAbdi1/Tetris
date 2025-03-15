//-Spesifiserer hvilke metoder TetrisView har tilgang til
package no.uib.inf101.tetris.view;

import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.tetris.model.GameState;

public interface ViewableTetrisModel {

    /**
     * The dimensions of the board, i.e. number of rows and columns
     *
     * @return an object of type GridDimension
     */
    GridDimension getDimension();

    /**
     * An object that when iterated over returns all positions
     * and corresponding values
     *
     * @return an iterable object
     */
    Iterable<GridCell> getTilesOnBoard();

    /**
     * Returns an iterable object containing all the cells of the falling tetromino.
     *
     * @return an iterable object with the falling tetromino's cells
     */
    Iterable<GridCell> getFallingTetrominoCells();


 // under er metodene jeg har laget

    /**
     * Henter den nåværende Game State.
     * 
     * @return Returnerer et objekt av typen GameState.
     */
    GameState getGameState ();

    /**
     * Metode som henter ut poengoversikten
     * 
     * @return int som angir poengene
     */
    int getPointCounter ();

    //Iterable<GridCell> fallingPiece();


    /**
     * Henter skyggebrikken som viser hvor den nåværende tetrominoen vil lande.
     *
     * @return En iterable med GridCell-objektene som utgjør skyggen.
     */
    Iterable<GridCell> getShadowTetrominoCells();
}
