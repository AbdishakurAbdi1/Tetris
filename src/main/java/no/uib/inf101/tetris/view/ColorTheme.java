package no.uib.inf101.tetris.view;

import java.awt.Color;

public class ColorTheme {

    public Color getCellColor(Character c) {
        Color color = switch (c) {
            case 'r', 'L' -> Color.RED;       // L-blokka blir rød
            case 'g', 'S' -> Color.GREEN;     // S-blokka blir grønn
            case 'y', 'O' -> Color.YELLOW;    // O-blokka blir gul
            case 'b', 'J' -> Color.BLUE;      // J-blokka blir blå
            case 'w', 'I' -> Color.CYAN;      // I-blokka blir cyan
            case 'T' -> Color.MAGENTA;        // T-blokka blir magenta
            case 'Z' -> Color.ORANGE;         // Z-blokka blir oransje
            case '-' -> Color.BLACK;          // Bakgrunn er svart
            default -> throw new IllegalArgumentException(
                    "No available color for '" + c + "'");
        };
        return color;
    }

    public Color getFrameColor() {
        return Color.WHITE;
    }

    public Color getBackgroundColor() {
        return new Color(0, 0, 0, 128);
    }

}
