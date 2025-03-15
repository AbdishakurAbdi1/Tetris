package no.uib.inf101.tetris.model.tetromino;

import java.util.Random;

public class RandomTetrominoFactory implements TetrominoFactory {
    private final Random random = new Random();

    /**
     * Genererer en ny tilfeldig Tetromino.
     * Velger et tilfeldig symbol fra de gyldige Tetromino brikkene ('L', 'J', 'S',
     * 'Z', 'T', 'I', 'O')
     * og oppretter en ny Tetromino basert på en av de symbolene.
     * 
     * @return En ny tilfeldig Tetromino.
     */
    @Override
    public Tetromino getNext() {
        char[] symbols = { 'L', 'J', 'S', 'Z', 'T', 'I', 'O' };
        int index = random.nextInt(symbols.length); // Trekker en tilfeldig fra 0 til 6
        char randomSymbol = symbols[index]; // Velger en tilfeldig symbol basert på den tilfeldige indexen.
        return Tetromino.newTetromino(randomSymbol);
    }
}
