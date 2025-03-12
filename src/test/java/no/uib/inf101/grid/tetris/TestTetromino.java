package no.uib.inf101.grid.tetris;

import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.tetris.model.tetromino.Tetromino;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

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
    @DisplayName("Test getSymbol metoden i Tetromino")
    public void testGetSymbol() {
        assertEquals('T', t1.getSymbol(), "Tetromino symbol should be 'T'");
    }

    @Test
    @DisplayName("Test getPosition metoden i Tetromino")
    public void testGetPosition() {
        assertEquals(new CellPosition(0, 0), t1.getPosition(), "Tetromino position should be (0,0)");
    }

    


    @Test
    @DisplayName("Test hashCode metode i Tetromino")
    public void testHashcode() {
        assertEquals(t1.hashCode(), t2.hashCode());  // Samme symbol og fasong
        assertNotEquals(t1.hashCode(), t3.hashCode()); // Forskjellig symbol og fasong
    }

    @Test
    @DisplayName("Test equals metode i Tetromino")
    public void testEquals() {
        assertEquals(t1, t2);  // Samme symbol og fasong
        assertNotEquals(t1, t3); // Forskjellig symbol og fasong
    }

    @Test 
    @DisplayName("Test iterator metoden i Tetromino")
    public void testIterator(){
        Iterator<GridCell> iterator=t1.iterator();
        assertTrue(iterator.hasNext());
        
        while (iterator.hasNext()) {
            GridCell cell= iterator.next();
            assertNotNull(cell,"Iterator skal ikke retunere null verdier");
        }
    }

    @Test
    @DisplayName("Test shiftedBy method for Tetromino")
    public void testShiftedBy() {
        Tetromino shiftedT = t1.shiftedBy(2, 3); // Shift by (2,3)

        // Verify that position changed correctly
        assertEquals(new CellPosition(2, 3), shiftedT.getPosition(), "Tetromino should be shifted correctly");

        // Verify that shape and symbol remain unchanged
        assertEquals(t1.getSymbol(), shiftedT.getSymbol(), "Tetromino symbol should remain the same");
        assertEquals(t1, Tetromino.newTetromino('T'), "Shape should remain unchanged");
    }


    @Test
    void testRotationOfTShape() {
        boolean[][] originalShape = {
            {false, true, false},
            {true, true, true},
            {false, false, false}
        };
    
        Tetromino tPiece = new Tetromino('T', originalShape, new CellPosition(5, 5));
        Tetromino rotatedPiece = tPiece.rotateTetromino();
    
        boolean[][] expectedShape = {
            {false, true, false},
            {false, true, true},
            {false, true, false}
        };
    
        assertArrayEquals(expectedShape, rotatedPiece.getShape(), "Rotasjon av T-brikken er feil!");
    }
}