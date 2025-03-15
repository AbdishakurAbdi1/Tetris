package no.uib.inf101.grid.tetris;

import static org.junit.jupiter.api.Assertions.assertEquals;
import no.uib.inf101.tetris.model.tetromino.PatternedTetrominoFactory;


import org.junit.jupiter.api.Test;

public class TestPatternedTetrominoFactory {
    
    @Test
    public void sanityTestPatternedTetrominoFactory() {
        PatternedTetrominoFactory factory = new PatternedTetrominoFactory("TSZ");

        // Sjekker om rekkefølgen gjentas korrekt
        assertEquals('T', factory.getNext().getSymbol());
        assertEquals('S', factory.getNext().getSymbol());
        assertEquals('Z', factory.getNext().getSymbol());
        assertEquals('T', factory.getNext().getSymbol());
        assertEquals('S', factory.getNext().getSymbol()); 
    }
}