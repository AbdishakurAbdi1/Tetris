//-representerer hele spillet og holder styr på brettet, den fallende brikken og om spillet er over.
package no.uib.inf101.tetris.model;

import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.tetris.view.ViewableTetrisModel;
import no.uib.inf101.tetris.controller.ControllableTetrisModel;

/**
 * Representerer tilstanden i Tetris-spillet.
 * Holder styr på spillbrettet og brikkene.
 */
public class TetrisModel implements ViewableTetrisModel, ControllableTetrisModel{
    /*Feltvariabel som representerer brettet */
    private TetrisBoard board;

    public TetrisModel() {
        /** Oppretter et nytt Tetris-spill med et brett på 15x10*/
        this.board= new TetrisBoard(15, 10);
    }

    @Override
    public GridDimension getDimension() {
        return board; //Dette er nok fordi TetrisBoard arver fra Grid som implementerer IGrid som arver fra GridDimensions
    }


    @Override
    public Iterable<GridCell> getTilesOnBoard() {
        return board; //Siden board allerede er Iterable<GridCell>, kan vi returnere det direkte
    }
    
}
