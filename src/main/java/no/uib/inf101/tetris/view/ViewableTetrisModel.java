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


    /**
     * Henter den nåværende Game State.
     * 
     * @return Returnerer et objekt av typen GameState.
     */
    GameState getGameState ();

    int getPointCounter();

    Iterable<GridCell> fallingPiece();
}
