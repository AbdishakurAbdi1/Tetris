package no.uib.inf101.tetris.view;

import java.awt.Color;

public class ColorTheme {

    public Color getCellColor(Character c) {
        if (c == 'L') {
            return new Color(255, 178, 102); // Lys korall (L-brikken)
        } else if (c == 'S') {
            return new Color(180, 255, 180); // Mintgrønn (S-brikken)
        } else if (c == 'O') {
            return new Color(255, 236, 139); // Pastellgul (O-brikken)
        } else if (c == 'J') {
            return new Color(204, 153, 255); // Lavendel (J-brikken)
        } else if (c == 'I') {
            return new Color(173, 216, 230); // Himmelblå (I-brikken)
        } else if (c == 'T') {
            return new Color(255, 182, 193); // Pastellrosa (T-brikken)
        } else if (c == 'Z') {
            return new Color(255, 204, 153); // Lys fersken (Z-brikken)
        } else if (c == '-') {
            return new Color(230, 220, 200); // Lys beige bakgrunn
        } else {
            throw new IllegalArgumentException("Feil type symbol:" + c + "'");
        }
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
