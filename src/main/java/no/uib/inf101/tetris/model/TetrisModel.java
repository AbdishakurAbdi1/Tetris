//-representerer hele spillet og holder styr på brettet, den fallende brikken og om spillet er over.
package no.uib.inf101.tetris.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import no.uib.inf101.grid.CellPosition;
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
public class TetrisModel implements ViewableTetrisModel, ControllableTetrisModel {
    /* Feltvariabel som representerer brettet */
    private TetrisBoard board;
    private TetrominoFactory tetrominoFactory; // Legg til en fabrikk for å generere Tetrominoer
    private Tetromino fallingTetromino; // Legg til en variabel for den fallende Tetrominoen
    private GameState gameState;
    private int pointCounter = 0;

    public TetrisModel() {
        /** Oppretter et nytt Tetris-spill med et brett på 15x10 */
        this.board = new TetrisBoard(15, 10);
        this.tetrominoFactory = new RandomTetrominoFactory();
        this.fallingTetromino = tetrominoFactory.getNext().shiftedToTopCenterOf(board.getDimension()); // Initialiser
                                                                                                       // Tetromino
        //this.gameState = GameState.ACTIVE_GAME; // Starter med ACTIVE_GAME
    };

    /**
     * Oppretter et nytt Tetris-spill med et brett på 15x10 og en gitt
     * TetrominoFactory.
     *
     * @param factory En TetrominoFactory som genererer Tetromino-brikker.
     */
    public TetrisModel(TetrominoFactory factory) {
        this.board = new TetrisBoard(15, 10);
        this.tetrominoFactory = factory; // Bruker factory fra Main
        this.fallingTetromino = tetrominoFactory.getNext().shiftedToTopCenterOf(board.getDimension()); // Initialiser
                                                                                                       // Tetromino)

        this.gameState = GameState.ACTIVE_GAME; // Starter med ACTIVE_GAME                                                                                                
    }

    @Override
    public boolean moveTetromino(int deltaRow, int deltaCol) {
        System.out.println("moveTetromino() kalt med deltaRow: " + deltaRow + ", deltaCol: " + deltaCol);

        Tetromino candidate = fallingTetromino.shiftedBy(deltaRow, deltaCol);

        // **Sjekk at alle posisjonene i Tetromino er gyldige**
        for (GridCell cell : candidate) {
            if (!board.positionIsOnGrid(cell.pos()) || board.get(cell.pos()) != '-') {
                System.out.println("Ugyldig posisjon: " + cell.pos()); // Debugging
                return false; // Avbryt flyttingen hvis en posisjon er ugyldig
            }
        }

        fallingTetromino = candidate;
        return true;
    }

    /**
     * Sjekker om en Tetromino kan plasseres på brettet.
     * 
     * @param tetromino Tetrominoen som skal sjekkes.
     * @return `true` hvis den kan plasseres, ellers `false`.
     */
    private boolean isValidPosition(Tetromino tetromino) {
        for (GridCell cell : tetromino) {
            if (!board.positionIsOnGrid(cell.pos()) || board.get(cell.pos()) != '-') {
                System.out.println("Ugyldig posisjon: " + cell.pos()); // Debugger
                return false;
            }
        }
        System.out.println("Tetromino er gyldig på brettet");
        return true;
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
     * Returnerer en iterable av GridCell som representerer den fallende
     * Tetrominoen.
     *
     * @return En iterable av GridCell for den fallende Tetrominoen.
     */
    @Override
    public Iterable<GridCell> getFallingTetrominoCells() {
        if (fallingTetromino == null) {
            return Collections.emptyList(); // Returnerer en tom liste hvis ingen brikke faller
        }

        List<GridCell> cells = new ArrayList<>();
        for (GridCell cell : fallingTetromino) {
            cells.add(cell);
        }
        // System.out.println("Falling tetromino cells: " + getFallingTetrominoCells());
        return cells;

    }

    @Override
    public Iterable<GridCell> fallingPiece() {
        return fallingTetromino;
    }

    public TetrisBoard getBoard() {
        return this.board;
    }

    @Override
    public boolean rotateTetromino() {
        Tetromino rotated = fallingTetromino.rotateTetromino();
        // Sjekk om normal rotasjon er gyldig
        if (isValidPosition(rotated)) {
            fallingTetromino = rotated;
            return true;
        }

        //Formål: Bonus poeng.
        //Wall Kick: Når Tetromino/brikken er helt øverst eller helt
        // inntill kantenene, kan de ikke roteres fordi deler av av den vil falle
        // utenfor brettet. Derfor må vi implementere wall kick som flytter brikken en
        // liten hakk inn igjen slik at hele brikken kan roteres innenfor brettet.
        int currentCol = fallingTetromino.getPosition().col();
        int currentRow = fallingTetromino.getPosition().row();

        // 1. Hvis Tetrominoen er for langt til venstre, flytt den til høyre
        if (currentCol < 0) {
            rotated = fallingTetromino.shiftedBy(0, 1).rotateTetromino();
            if (isValidPosition(rotated)) {
                fallingTetromino = rotated;
                return true;
            }
        }

        // 2. Hvis Tetrominoen er for langt til høyre, flytt den til venstre
        if (currentCol + rotated.getShape()[0].length > board.getDimension().cols()) {
            rotated = fallingTetromino.shiftedBy(0, -1).rotateTetromino();
            if (isValidPosition(rotated)) {
                fallingTetromino = rotated;
                return true;
            }
        }

        // 3. Hvis Tetrominoen er på toppen, flytt den ned
        if (currentRow < 0) {
            rotated = fallingTetromino.shiftedBy(1, 0).rotateTetromino();
            if (isValidPosition(rotated)) {
                fallingTetromino = rotated;
                return true;
            }
        }
        return false; // Ingen rotasjon mulig
    }

    @Override
    public GameState getGameState() {
        System.out.println("gs: " + gameState);
        return this.gameState;
    }

    /**
     * Slipper den fallende Tetrominoen rett ned til nærmeste blokk eller bunnen av
     * brettet.
     * Deretter låses Tetrominoen fast og en ny Tetromino genereres.
     */
    @Override
    public void dropTetromino() {
        if (gameState == GameState.GAME_OVER)
            return; // Ikke gjør noe hvis spillet er over

        for (int i = 0; i <= board.rows(); i++) {
            moveTetromino(1, 0);
        }
        System.out.println("Låser Tetromino: " + fallingTetromino); // Debugger

        lockTetromino(); // Låser brikken fast på brettet

        System.out.println("Før fjerning:\n" + board.prettyString());

        System.out.println("Brett før fjerning:\n" + board.prettyString());
        int rowsCleared = board.clearFilledRows(); // Fjerner fulle rader
        System.out.println("Brett etter fjerning:\n" + board.prettyString());
        System.out.println("Antall rader fjernet: " + rowsCleared);

        //pointCounter += rowsCleared * 100; // Oppdaterer poengsummen

        if (rowsCleared > 0) { 
            pointGiver(rowsCleared); // **Send antall rader fjernet til pointGiver**
        }
        newFallingTetromino(); // Lager ny brikke (hvis mulig)
    }

    /**
     * Låser den nåværende Tetrominoen fast på brettet ved å oppdatere rutenettet.
     * Setter alle celler som Tetrominoen dekker til sin respektive farge/symbol.
     */
    private void lockTetromino() {
        for (GridCell cell : fallingTetromino) {
            if (board.positionIsOnGrid(cell.pos())) { // Sikrer at vi ikke går utenfor brettet
                board.set(cell.pos(), fallingTetromino.getSymbol());
                System.out.println("Låser celle: " + cell.pos() + " med symbol: " + fallingTetromino.getSymbol());
                System.out.println("Board[" + cell.pos() + "] = " + board.get(cell.pos()));
            } else {
                System.out.println("FEIL! Prøver å låse en Tetromino utenfor brettet: " + cell.pos());
            }
        }
        System.out.println("Låser Tetromino på brettet:\n" + board.prettyString());
    }

    /**
     * Oppretter en ny fallende Tetromino fra fabrikken og setter den øverst på
     * brettet.
     * Hvis den nye Tetrominoen ikke kan plasseres, settes spillet til GAME_OVER.
     */
    private void newFallingTetromino() {
        fallingTetromino = tetrominoFactory.getNext().shiftedToTopCenterOf(board);
    
        // Ekstra sjekk: Er øverste rad blokkert?
        for (int col = 0; col < board.cols(); col++) {
            if (board.get(new CellPosition(0, col)) != '-') {
                this.gameState = GameState.GAME_OVER;
                this.fallingTetromino = null; // Ingen ny brikke etter GAME_OVER
                System.out.println("Game Over: Øverste rad er blokkert!");
                return;
            }
        }
    
        // Sjekk om den nye Tetrominoen faktisk kan plasseres
        if (!isValidPosition(this.fallingTetromino)) {
            this.gameState = GameState.GAME_OVER;
            this.fallingTetromino = null;
            System.out.println("Game Over: Ingen plass til ny brikke!");
        }
    }

    @Override
    public int getPointCounter() {
        System.out.println("Poengsum: " + pointCounter); // Debugging
        return this.pointCounter;
    }

    @Override
    public int getTimerDelay() {
        return 1000;
    }

    @Override
    public void clockTick() {
        boolean moved = moveTetromino(1, 0); // Flytter tetromino en hakk ned.
        if (!moved) {
            lockTetromino();

            int rowsCleared = board.clearFilledRows();
            if (rowsCleared > 0) {
                pointGiver(rowsCleared);
            }
            newFallingTetromino();
            System.out.println("Brikken kunne ikke flytte seg ned, låser den.");
        }

    }

    /**
     * Oppdaterer poengsummen basert på antall rader som fjernes fra brettet.
     * Denne metoden brukes i dropTetromino og clockTick.
     * Den oppdaterer feltvariabelen 'pointCounter',
     * som brukes i metoden getPointCounter for å vise det i GUI.

     *  */
    public void pointGiver(int rowsRemoved) {
        if (rowsRemoved == 1) {
            pointCounter += 100;
        } else if (rowsRemoved == 2) {
            pointCounter += 300;
        } else if (rowsRemoved == 3) {
            pointCounter += 500;
        } else if (rowsRemoved == 4) {
            pointCounter += 800;
        } else if (rowsRemoved == 5) {
            pointCounter += 1000;
        } 
    }
}