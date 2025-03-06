//-representerer hele spillet og holder styr på brettet, den fallende brikken og om spillet er over.
package no.uib.inf101.tetris.model;

import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.tetris.view.ViewableTetrisModel;
import no.uib.inf101.tetris.controller.ControllableTetrisModel;
import no.uib.inf101.tetris.model.tetromino.RandomTetrominoFactory;
import no.uib.inf101.tetris.model.tetromino.Tetromino;
import no.uib.inf101.tetris.model.tetromino.TetrominoFactory;

/**
 * Representerer tilstanden i Tetris-spillet.
 * Holder styr på spillbrettet og brikkene.
 */
public class TetrisModel implements ViewableTetrisModel, ControllableTetrisModel{
    /*Feltvariabel som representerer brettet */
    private TetrisBoard board;
    private TetrominoFactory tetrominoFactory; // Legg til en fabrikk for å generere Tetrominoer
    private Tetromino fallingTetromino; // Legg til en variabel for den fallende Tetrominoen


    public TetrisModel() {
        /** Oppretter et nytt Tetris-spill med et brett på 15x10*/
        this.board= new TetrisBoard(15, 10);
        this.tetrominoFactory= new RandomTetrominoFactory(); 
        this.fallingTetromino = tetrominoFactory.getNext().shiftedToTopCenterOf(board.getDimension()); // Initialiser Tetromino            
        };

    /**
     * Oppretter et nytt Tetris-spill med et brett på 15x10 og en gitt TetrominoFactory.
     *
     * @param factory En TetrominoFactory som genererer Tetromino-brikker.
     */
    public TetrisModel(TetrominoFactory factory){
        this.board= new TetrisBoard(15, 10);
        this.tetrominoFactory = factory;  //Bruker factory fra Main
        this.fallingTetromino = tetrominoFactory.getNext().shiftedToTopCenterOf(board.getDimension()); // Initialiser Tetromino)
    }

    @Override
    public GridDimension getDimension() {
        return board; // Dette er nok fordi TetrisBoard arver fra Grid som har dimensjoner
        }

    @Override
    public Iterable<GridCell> getTilesOnBoard() {
        return board; // Siden board allerede er Iterable<GridCell>, kan vi returnere det direkte
        }

    /**
     * @return Den nåværende fallende Tetrominoen.
     */
    public Tetromino getFallingTetromino() {
        return fallingTetromino;
        }

    /**
     * Returnerer en iterable av GridCell som representerer den fallende Tetrominoen.
     *
     * @return En iterable av GridCell for den fallende Tetrominoen.
     */
    @Override
    public Iterable<GridCell> getFallingTetrominoCells() {
        System.out.println("Falling Tetromino: " + fallingTetromino);
        for (GridCell cell : fallingTetromino) {
            System.out.println("Cell: " + cell);
        }
        return fallingTetromino;
    }
    

}