package no.uib.inf101.tetris.view;

import java.awt.Color;

public class ColorTheme {

    public Color getCellColor(Character c) {
        Color color = switch (c) {
            case 'r', 'L' -> Color.BLUE; // L-blokka blir rød
            case 'g', 'S' -> Color.GREEN; // S-blokka blir grønn
            case 'y', 'O' -> Color.ORANGE; // O-blokka blir gul
            case 'b', 'J' -> Color.MAGENTA; // J-blokka blir blå
            case 'w', 'I' -> Color.CYAN; // I-blokka blir cyan
            case 'T' -> Color.RED; // T-blokka blir magenta
            case 'Z' -> Color.YELLOW; // Z-blokka blir oransje
            case '-' -> Color.black; // Bakgrunn er svart
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


    /**
     * Henter fargen for griden ved spillslutt.
     * 
     * @return Gjennomsiktig farge.
     */
    public Color GameOverOverlayColor()  {
        return (new Color(0, 0, 0, 128));
    }
     /**
     * Henter fargen for teksten ved spillslutt.
     * 
     * @return Hvit farge.
     */

    public Color GameOverTextColor() {
        return Color.WHITE; 
   
    }

}
