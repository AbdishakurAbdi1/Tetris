package no.uib.inf101.grid;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import no.uib.inf101.grid.GridCell;
import no.uib.inf101.tetris.model.TetrisModel;
import no.uib.inf101.tetris.model.TetrisBoard;
import no.uib.inf101.tetris.model.tetromino.Tetromino;
import no.uib.inf101.tetris.model.tetromino.TetrominoFactory;
import no.uib.inf101.tetris.model.tetromino.RandomTetrominoFactory;
import no.uib.inf101.grid.CellPosition;

public class TetrisModelTest {
    private TetrisModel model;
    private TetrominoFactory factory;

    @BeforeEach
    void setUp() {
        factory = new RandomTetrominoFactory();
        model = new TetrisModel(factory); // Bruker din versjon av TetrisModel
    }

    @Test
    void testSuccessfulMoveReturnsTrue() {
        // Tester at en gyldig flytting returnerer true
        assertTrue(model.moveTetromino(1, 0), "Flytting én rad ned burde være mulig.");
    }

    @Test
    void testSuccessfulMoveChangesFallingTetromino() {
        // Tester at brikken faktisk endrer posisjon etter en gyldig flytting
        Tetromino beforeMove = model.getFallingTetromino();
        model.moveTetromino(1, 0);
        Tetromino afterMove = model.getFallingTetromino();
        assertNotEquals(beforeMove, afterMove, "Tetrominoen burde ha endret posisjon.");
    }

    @Test
    void testCannotMoveOutOfBounds() {
        // Flytter brikken helt til venstre og forsøker å flytte enda lenger til venstre
        while (model.moveTetromino(0, -1)); // Flytter så langt til venstre som mulig
        assertFalse(model.moveTetromino(0, -1), "Brikken skal ikke kunne forlate brettet til venstre.");
    }

    @Test
    void testCannotMoveIntoOccupiedSpace() {
        // Plasser en blokk på brettet som hindrer bevegelse
        Tetromino falling = model.getFallingTetromino();
        for (GridCell cell : falling) {
            model.getBoard().set(cell.pos(), 'X'); // Simulerer en blokkert celle
        }
        assertFalse(model.moveTetromino(1, 0), "Brikken skal ikke kunne flytte til en opptatt posisjon.");
    }
}
