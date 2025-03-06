package no.uib.inf101.tetris.model.tetromino;


/**
 * Interface for en Tetromino fabrikk.
 * Definerer metoden getNext() som genrere en ny Teromino
 */
public interface TetrominoFactory {
    /**
     * Generer en ny tilfeldig Tetromino.
     * @return En ny Tetromino.
     */
    Tetromino getNext();
}
