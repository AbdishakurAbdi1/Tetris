package no.uib.inf101.tetris.model.tetromino;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

public class TestRandomTetrominoFactory {
    private TetrominoFactory factory;

    @BeforeEach
    void setUp() {
        factory = new RandomTetrominoFactory();
    }

    @Test
    void testGetNextReturnsValidTetromino() {
        Set<Character> validSymbols = Set.of('L', 'J', 'S', 'Z', 'T', 'I', 'O');

        for (int i = 0; i < 100; i++) { // Tester flere ganger
            Tetromino tetromino = factory.getNext();
            assertNotNull(tetromino, "getNext() should never return null");
            assertTrue(validSymbols.contains(tetromino.getSymbol()),
                    "Tetromino should be one of the valid symbols");
        }
    }
}