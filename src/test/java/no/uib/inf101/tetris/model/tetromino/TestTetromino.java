package no.uib.inf101.tetris.model.tetromino;

import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.CellPosition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class TestTetromino {
    private Tetromino t1;
    private Tetromino t2;
    private Tetromino t3;

    @BeforeEach
    void setUp() {
        t1 = Tetromino.newTetromino('T');
        t2 = Tetromino.newTetromino('T');
        t3 = Tetromino.newTetromino('L');
    }

    @Test
    public void testGetSymbol() {
        assertEquals('T', t1.getSymbol(), "Tetromino symbol should be 'T'");
    }

    @Test
    public void testGetPosition() {
        assertEquals(new CellPosition(0, 0), t1.getPosition(), "Tetromino position should be (0,0)");
    }

    @Test
    public void testHashcode() {
        assertEquals(t1.hashCode(), t2.hashCode()); // Samme symbol og fasong
        assertNotEquals(t1.hashCode(), t3.hashCode()); // Forskjellig symbol og fasong
    }

    @Test
    public void testEquals() {
        assertEquals(t1, t2); // Samme symbol og fasong
        assertNotEquals(t1, t3); // Forskjellig symbol og fasong
    }

    @Test
    public void testIterator() {
        Iterator<GridCell> iterator = t1.iterator();
        assertTrue(iterator.hasNext());

        while (iterator.hasNext()) {
            GridCell cell = iterator.next();
            assertNotNull(cell, "Iterator skal ikke retunere null verdier");
        }
    }

    @Test
    public void testShiftedBy() {
        Tetromino shiftedT = t1.shiftedBy(2, 3); // skift med (2,3)

        // sjekk om posisjonen endret
        assertEquals(new CellPosition(2, 3), shiftedT.getPosition(), "Tetromino should be shifted correctly");

        // sjekk at shape og symbol forblir uendret
        assertEquals(t1.getSymbol(), shiftedT.getSymbol(), "Tetromino symbol should remain the same");
        assertEquals(t1, Tetromino.newTetromino('T'), "Shape should remain unchanged");
    }

    @Test
    void testRotationOfTShape() {
        boolean[][] originalShape = {
                { false, true, false },
                { true, true, true },
                { false, false, false }
        };

        Tetromino tPiece = new Tetromino('T', originalShape, new CellPosition(5, 5));
        Tetromino rotatedPiece = tPiece.rotateTetromino();

        boolean[][] expectedShape = {
                { false, true, false },
                { false, true, true },
                { false, true, false }
        };

        assertArrayEquals(expectedShape, rotatedPiece.getShape(), "Rotasjon av T-brikken er feil!");
    }
}