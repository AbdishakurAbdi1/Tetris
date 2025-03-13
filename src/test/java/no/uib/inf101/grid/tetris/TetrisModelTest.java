package no.uib.inf101.grid.tetris;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.tetris.model.TetrisModel;
import no.uib.inf101.tetris.model.tetromino.Tetromino;
import no.uib.inf101.tetris.model.tetromino.TetrominoFactory;
import no.uib.inf101.tetris.model.tetromino.RandomTetrominoFactory;


public class TetrisModelTest {
    private TetrisModel model;
    private TetrominoFactory factory;

    @BeforeEach
    void setUp() {
        factory = new RandomTetrominoFactory();
        model = new TetrisModel(factory);
    }


    @Test
    void testMoveTetromino() {
        assertTrue(model.moveTetromino(1, 0), "Brikken burde kunne flyttes én rad ned.");
    }

    @Test
    void testMoveChangesTetrominoPosition() {
        Tetromino beforeMove = model.getFallingTetromino();
        model.moveTetromino(1, 0);
        assertNotEquals(beforeMove, model.getFallingTetromino(), "Tetrominoens posisjon burde endres.");
    }

    @Test
    void testCannotMoveOutOfBounds() {
        while (model.moveTetromino(0, -1)); // Flytt så langt til venstre som mulig
        assertFalse(model.moveTetromino(0, -1), "Brikken skal ikke kunne forlate brettet til venstre.");
    }

    @Test
    void testCannotMoveIntoOccupiedSpace() {
        for (GridCell cell : model.getFallingTetromino()) {
            model.getBoard().set(cell.pos(), 'X');
        }
        assertFalse(model.moveTetromino(1, 0), "Brikken skal ikke kunne flytte til en opptatt posisjon.");
    }

    @Test
    void testRotateTetromino() {
        Tetromino tPiece = Tetromino.newTetromino('T');
        Tetromino oPiece = Tetromino.newTetromino('O');

        // Sjekker at roteringen endrer fasongen til T
        assertNotEquals(tPiece.getShape(), tPiece.rotateTetromino().getShape(),
                "T piece should change shape after one rotation.");

        // Sjekker at etter 4 roteringer er T tilbake til sin originale fasong
        assertArrayEquals(tPiece.getShape(), tPiece.rotateTetromino()
                .rotateTetromino()
                .rotateTetromino()
                .rotateTetromino()
                .getShape(),
                "T piece should return to its original shape after four rotations.");

        // Check that rotating the O piece does not change its shape
        assertArrayEquals(oPiece.getShape(), oPiece.rotateTetromino().getShape(),
                "O piece should remain the same after rotation.");
    }

    @Test
    void testRotateTetrominoWithWallKick() {
        while (model.moveTetromino(0, -1)); // Sett brikken mot venstre vegg
        assertTrue(model.rotateTetromino(), "Rotasjon burde fungere med Wall Kick.");
    }

    @Test
    void testDropTetrominoCreatesNewPiece() {
        Tetromino beforeDrop = model.getFallingTetromino();
        model.dropTetromino();
        Tetromino afterDrop = model.getFallingTetromino();
    
        assertNotNull(afterDrop, "Det skal alltid være en ny brikke etter dropp, med mindre Game Over.");
        assertNotEquals(beforeDrop, afterDrop, "En ny brikke burde genereres etter dropp.");
    }

    @Test
    void testDropTetrominoStopsAtBlock() {
        for (GridCell cell : model.getFallingTetromino()) {
            CellPosition blockedPos = new CellPosition(cell.pos().row() + 1, cell.pos().col());
            model.getBoard().set(blockedPos, 'X');
        }
        model.dropTetromino();
        for (GridCell cell : model.getFallingTetrominoCells()) {
            assertEquals(cell.symbol(), model.getBoard().get(cell.pos()), "Brikken burde stoppe og låses på brettet.");
        }
    }

    // @Test
    // void testDropTetrominoFallsAndLocks() {
    //     Tetromino initialTetromino = model.getFallingTetromino();
        
    //     model.dropTetromino(); // Brikken skal falle rett ned og låses fast
    
    //     System.out.println("Brett etter dropping:\n" + model.getBoard().prettyString()); // Skriv ut brettet
    
    //     // Sjekk at den gamle brikken er låst fast på brettet
    //     for (GridCell cell : initialTetromino) {
    //         System.out.println("Sjekker celle: " + cell.pos() + " forventet: " + cell.symbol() + 
    //                            " faktisk: " + model.getBoard().get(cell.pos()));
    //         assertEquals(cell.symbol(), model.getBoard().get(cell.pos()), 
    //             "Brikken burde være låst fast på brettet etter dropp.");
    //     }
    // }

    // @Test
    // void testGameOverTriggersCorrectly() {
    //     for (int col = 0; col < model.getBoard().cols(); col++) {
    //         model.getBoard().set(new CellPosition(0, col), 'X');
    //     }
    //     model.dropTetromino();
    //     assertEquals(GameState.GAME_OVER, model.getGameState(), "Spillet burde være i GAME_OVER-tilstand.");
    //     assertNull(model.getFallingTetromino(), "Ingen ny brikke etter Game Over.");
    // }

    // @Test
    // void testCannotMoveAfterGameOver() {
    //     for (int col = 0; col < model.getBoard().cols(); col++) {
    //         model.getBoard().set(new CellPosition(0, col), 'X'); // Fyller øverste rad
    //     }
    
    //     model.dropTetromino();
    
    //     assertEquals(GameState.GAME_OVER, model.getGameState(), 
    //         "Spillet burde være i GAME_OVER-tilstand.");
    
    //     // Sjekk at det ikke er noen ny fallende brikke
    //     assertNull(model.getFallingTetromino(), 
    //         "Det burde ikke være en ny fallende brikke etter Game Over.");
    
    //     // Forsøk å flytte kun hvis en brikke faktisk eksisterer
    //     if (model.getFallingTetromino() != null) {
    //         assertFalse(model.moveTetromino(1, 0), 
    //             "Ingen bevegelse skal være mulig etter Game Over.");
    //     }
    // }


    @Test
    public void testClockTickMoves() {
        TetrisModel model = new TetrisModel();
        CellPosition initialPosition = model.getFallingTetromino().getPosition(); //Får tak i posisjonen.
    
        model.clockTick(); //klokkestikk tetromino skal flytte seg ned
    
        CellPosition newPosition = model.getFallingTetromino().getPosition();
    
        // Sjekk at tetrominoen har beveget seg ned en rad
        assertEquals(initialPosition.row() + 1, newPosition.row());
        assertEquals(initialPosition.col(), newPosition.col());
    }

    @Test
    public void testClockTickLocksTetromino() {
        TetrisModel model = new TetrisModel();

        //Flytt tetrominoen til bunnen for å stoppe den
        while (model.moveTetromino(1, 0));

        //Kaller clockTick så skal tetrominoen låses fast
        model.clockTick();

        //Sjekk at en ny tetromino er generert (den forrige er låst fast)
        assertNotEquals(model.getFallingTetromino(), null);
    }
}


