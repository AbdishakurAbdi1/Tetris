//-Spesifiserer hvilke metoder TetrisView har tilgang til
package no.uib.inf101.tetris.view;

import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;

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

}
