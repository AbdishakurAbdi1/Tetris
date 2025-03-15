//- Tegne brettet og Tegne den fallende brikken over brettet.
package no.uib.inf101.tetris.view;

import javax.swing.JPanel;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.tetris.model.GameState;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.Color;
import java.awt.Font;


public class TetrisView extends JPanel {

    public static final int OUTERMARGIN = 15;
    public static final int CELLMARGIN = 2;
    public static final int PREFERREDSIDESIZE = 40;
    public Color color;

    private ViewableTetrisModel viewableTetrisModel;
    private ColorTheme colorTheme;

    public TetrisView(ViewableTetrisModel viewableTetrisModel) {
        this.viewableTetrisModel = viewableTetrisModel;

        this.colorTheme = new ColorTheme();
        this.setBackground(colorTheme.getBackgroundColor());

        this.setFocusable(true);
        this.setPreferredSize(getDefaultSize(viewableTetrisModel.getDimension()));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawGame(g2);
        drawScore(g2);
    }

    /**
     * Metode som tegner selve spill elementene i Tetris
     * 
     * @param g2 lerretet det skal tegnes på
     */
    private void drawGame(Graphics2D g2) {
        // Definerer spilleområdet med marginer
        double margin = OUTERMARGIN;
        double width = this.getWidth() - 2 * margin;
        double height = this.getHeight() - 2 * margin;

        Rectangle2D box = new Rectangle2D.Double(margin, margin, width, height);
        g2.setColor(colorTheme.getFrameColor());
        g2.fill(box);

        // Konverter for å plassere celler
        CellPositionToPixelConverter converter = new CellPositionToPixelConverter(
                box, viewableTetrisModel.getDimension(), CELLMARGIN);

        // Tegner brettet og den fallende brikken
        drawCells(g2, viewableTetrisModel.getTilesOnBoard(), converter, colorTheme);

        // Tegner skyggen før den fallende brikken
        drawShadow(g2, viewableTetrisModel.getShadowTetrominoCells(), converter);

        // Tegner den fallende brikken over brettet
        drawCells(g2, viewableTetrisModel.getFallingTetrominoCells(), converter, colorTheme);
        

        if (viewableTetrisModel.getGameState() == GameState.GAME_OVER) {
            // Tegner "Game Over" overlay
            g2.setColor(colorTheme.GameOverOverlayColor());
            g2.fill(box);

            g2.setColor(colorTheme.GameOverTextColor());
            g2.setFont(new Font("Arial", Font.BOLD, (int) height / 12));

            String gameOverText = "GAME OVER";
            String pointsText = "Points: " + viewableTetrisModel.getPointCounter();

            int xGameOver = (int) ((this.getWidth() - g2.getFontMetrics().stringWidth(gameOverText)) / 2);
            int yGameOver = (int) (this.getHeight() / 2.5);

            int xPoints = (int) ((this.getWidth() - g2.getFontMetrics().stringWidth(pointsText)) / 2);
            int yPoints = yGameOver + 40;

            g2.drawString(gameOverText, xGameOver, yGameOver);
            g2.drawString(pointsText, xPoints, yPoints);
        } else if (viewableTetrisModel.getGameState() == GameState.ACTIVE_GAME) {
            // drawCells(g2, viewableTetrisModel.getFallingTetrominoCells(), converter,
            // colorTheme);
        }
    }

    /**
     * Tegner en liste av celler på skjermen ved å bruke en gitt fargepalett og posisjon.
     *
     * @param g2        Grafikkobjektet som brukes for å tegne.
     * @param iterable  Iterable liste over GridCell objekter som skal tegnes.
     * @param converter Konverterer celleposisjoner til pikselkoordinater.
     * @param colorTheme Fargetemaet som bestemmer cellenes farge.
     */
    private static void drawCells(Graphics2D g2, Iterable<GridCell> iterable,
            CellPositionToPixelConverter converter, ColorTheme colorTheme) {
        for (GridCell cell : iterable) {
            CellPosition pos = cell.pos();
            Character c = cell.symbol();
            Rectangle2D tile = converter.getBoundsForCell(pos);

            g2.setColor(colorTheme.getCellColor(c));
            g2.fill(tile);
        }
    }

    /**
     * Beregner standard størrelsen basert på antall rader og kolonner.
     *
     * @param gd GridDimension objekt som inneholder rader og kolonner.
     * @return En Dimension objekt med beregnet bredde og høyde for rutenettet.
     */
    private static Dimension getDefaultSize(GridDimension gd) {
        int width = (int) (PREFERREDSIDESIZE * gd.cols() + CELLMARGIN * (gd.cols() + 1) + 2 * OUTERMARGIN);
        int height = (int) (PREFERREDSIDESIZE * gd.rows() + CELLMARGIN * (gd.cols() + 1) + 2 * OUTERMARGIN);
        return new Dimension(width, height);
    }

    /**
     * Formål: Bonus poeng.
     * Tegner poengsummen på skjermen under spillet.
     *
     * @param g2 Graphics2D-objektet som brukes til å tegne teksten.
     * Teksten viser gjeldende poengsum hentet fra
     * viewableTetrisModel.getPointCounter().
     */
    private void drawScore(Graphics2D g2) {
        g2.setColor(new Color(150, 111, 51)); // Pastell brun farge
        g2.setFont(new Font("Arial", Font.BOLD, 20));
    
        String scoreText = "SCORE: " + viewableTetrisModel.getPointCounter();
    
        int x = 20;  // Venstrejustert
        int y = 40;  // Øverst
    
        g2.drawString(scoreText, x, y);
    }

    /**
     * Formål: Bonus poeng.
     * Tegner en "skygge" av brikken der den vil lande.
     *
     * @param g2 Grafikkobjektet som tegner skyggen.
     * @param shadowCells Cellene som utgjør skyggebrikken.
     * @param converter Konverterer celler til piksler.
     */
    private void drawShadow(Graphics2D g2, Iterable<GridCell> shadowCells, 
                            CellPositionToPixelConverter converter) {
        for (GridCell cell : shadowCells) {
            Rectangle2D tile = converter.getBoundsForCell(cell.pos());

            g2.setColor(new Color(0, 0, 0, 50)); // Semi-transparent svart
            g2.fill(tile);
        }
    }

}
