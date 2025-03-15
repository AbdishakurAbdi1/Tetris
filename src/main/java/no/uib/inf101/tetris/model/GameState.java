package no.uib.inf101.tetris.model;

public enum GameState {
    /**
     * Definerer de mulige spilltilstandene for Tetris-spillet.
     * 
     * - ACTIVE_GAME: Spillet er aktivt, og spilleren kan fortsette å spille.
     * - GAME_OVER: Spillet er over, enten fordi spilleren har tapt, eller det er
     * nådd et visst poengnivå.
     */

    ACTIVE_GAME, // Spillet er i gang
    GAME_OVER; // Spillet er over/tapt
}
