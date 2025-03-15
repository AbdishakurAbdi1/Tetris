package no.uib.inf101.tetris.model.tetromino;

/**
 * En fabrikk for å generere Tetrominoer i et forhåndsbestemt mønster.
 * Fabrikken går gjennom en sekvens av symboler og starter på nytt når den når slutten.
 */
public class PatternedTetrominoFactory implements TetrominoFactory {
    private String pattern;
    private int currentPosition;

    /**
     * Konstruktør for PatternedTetrominoFactory.
     * 
     * @param pattern En streng som representerer sekvensen av Tetromino-symboler (f.eks. "LJSTIO").
     * Fabrikken vil generere Tetrominoer i denne rekkefølgen.
     * 
     * @throws IllegalArgumentException hvis mønsteret er null eller tomt.
     */
    public PatternedTetrominoFactory(String pattern) {
        if (pattern == null || pattern.isEmpty()) {
            throw new IllegalArgumentException("Mønsteret kan ikke være null eller tomt");
        }
        this.pattern = pattern;
        this.currentPosition = 0;
    }

   
    @Override
    public Tetromino getNext() {
        char symbol = pattern.charAt(currentPosition);
        currentPosition = (currentPosition + 1) % pattern.length();
        return Tetromino.newTetromino(symbol);
    }
}