package no.uib.inf101.tetris.view;

import java.awt.geom.Rectangle2D;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridDimension;

/**
 * Konverterer en celleposisjon i rutenettet til piksler innenfor en gitt boks.
 */
public class CellPositionToPixelConverter {

    private final Rectangle2D box;
    private final GridDimension gd;
    private final double margin;

     /**
     * Oppretter en ny konverter for celleposisjoner til pikselkoordinater.
     *
     * @param box    Rektangelet som definerer tegneområdet.
     * @param gd     Dimensjonene (antall rader og kolonner).
     * @param margin Margin som skal brukes ved beregning av cellestørrelser.
     */
    public CellPositionToPixelConverter(Rectangle2D box, GridDimension gd, double margin) {
        this.box = box;
        this.gd = gd;
        this.margin = margin;
    }

    /**
     * Beregner pikselgrensene for en gitt celleposisjon i brettet.
     *
     * @param cellPosition Posisjonen til cellen i brettet.
     * 
     * @return Et Rectangle2D-objekt som representerer cellens grenser i piksler.
     */
    public Rectangle2D getBoundsForCell(CellPosition cellPosition) {
        double cellW = (box.getWidth() - margin * gd.cols() - margin) / gd.cols();
        double cellH = (box.getHeight() - margin * gd.rows() - margin) / gd.rows();
        double cellX = box.getX() + margin + (cellW + margin) * cellPosition.col();
        double cellY = box.getY() + margin + (cellH + margin) * cellPosition.row();
        return new Rectangle2D.Double(cellX, cellY, cellW, cellH);
    }

}
