//- Tegne brettet og Tegne den fallende brikken over brettet.
package no.uib.inf101.tetris.view;

import javax.swing.JPanel;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.tetris.model.GameState;

import javax.swing.SwingUtilities;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;



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
    }

    /**
     *Metode som tegner selve spill-elementene i Tetris

     * @param g2 lerretet det skal tegnes på
     */
    private void drawGame(Graphics2D g2) {
        Rectangle2D box = new Rectangle2D.Double(
            OUTERMARGIN,
            OUTERMARGIN,
            this.getWidth() - OUTERMARGIN * 2,
            this.getHeight() - OUTERMARGIN * 2
        );
        g2.setColor(colorTheme.getFrameColor());
        g2.fill(box);
    
        CellPositionToPixelConverter converter = new CellPositionToPixelConverter(
            box, viewableTetrisModel.getDimension(), CELLMARGIN
        );
    
        // Tegner brettet
        drawCells(g2, viewableTetrisModel.getTilesOnBoard(), converter, colorTheme);
        
        // Tegner den fallende brikken
        drawCells(g2, viewableTetrisModel.getFallingTetrominoCells(), converter, colorTheme);
    
        // Sjekk om spillet er over, og tegn en "Game Over"-overlay
        if (viewableTetrisModel.getGameState() == GameState.GAME_OVER) {
            Color gameOveColor = colorTheme.GameOverOverlayColor();
            Color text = colorTheme.GameOverTextColor();
            g2.setColor(gameOveColor);
            g2.fillRect(0, 0, this.getWidth(), this.getHeight());
            g2.setColor(text);
            g2.setFont(new Font("Arial", Font.BOLD, 48));
            int x = (this.getWidth() - g2.getFontMetrics().stringWidth("Game Over")) / 2;
            int y = (this.getHeight() - g2.getFontMetrics().getHeight()) / 2;
            g2.drawString("Game Over", x, y);
            g2.drawString("Points: " + Integer.toString(viewableTetrisModel.getPointCounter()), x, y + 50);
            SwingUtilities.invokeLater(() -> repaint());

        } else if (viewableTetrisModel.getGameState() == GameState.ACTIVE_GAME) {
            drawCells(g2, viewableTetrisModel.getFallingTetrominoCells(), converter, colorTheme);
            repaint();
        }
    }
    
        
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

    private static Dimension getDefaultSize(GridDimension gd) {
        int width = (int) (PREFERREDSIDESIZE * gd.cols() + CELLMARGIN * (gd.cols() + 1) + 2 * OUTERMARGIN);
        int height = (int) (PREFERREDSIDESIZE * gd.rows() + CELLMARGIN * (gd.cols() + 1) + 2 * OUTERMARGIN);
        return new Dimension(width, height);
    }

}
