package no.uib.inf101.tetris.view;

import java.awt.Color;

public class ColorTheme {

    public Color getCellColor(Character c) {
        Color color = switch (c) {
            case 'r' -> Color.RED;
            case 'g' -> Color.GREEN;
            case 'y' -> Color.YELLOW;
            case 'b' -> Color.BLUE;
            case 'w' -> Color.WHITE;
            case '-' -> Color.BLACK;

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
