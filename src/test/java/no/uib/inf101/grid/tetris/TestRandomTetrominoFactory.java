package no.uib.inf101.grid.tetris;

import no.uib.inf101.tetris.model.tetromino.RandomTetrominoFactory;
import no.uib.inf101.tetris.model.tetromino.TetrominoFactory;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import no.uib.inf101.tetris.model.tetromino.*;

import java.util.Set;

public class TestRandomTetrominoFactory{
    private TetrominoFactory factory;

    @BeforeEach
    void setUp() {
        factory = new RandomTetrominoFactory();
    }

    @Test
    void testGetNextReturnsValidTetromino() {
        Set<Character> validSymbols = Set.of('L', 'J', 'S', 'Z', 'T', 'I', 'O');

        for (int i = 0; i < 100; i++) { // Tester flere ganger for tilfeldighet
            Tetromino tetromino = factory.getNext();
            assertNotNull(tetromino, "getNext() should never return null");
            assertTrue(validSymbols.contains(tetromino.getSymbol()),
                    "Tetromino should be one of the valid symbols");
        }
    }
}