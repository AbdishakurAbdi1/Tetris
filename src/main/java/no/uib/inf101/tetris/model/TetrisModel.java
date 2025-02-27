package no.uib.inf101.tetris.model;

import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.tetris.view.ViewableTetrisModel;

public class TetrisModel implements ViewableTetrisModel {

    public TetrisModel() {
        
    }

    @Override
    public GridDimension getDimension() {
        throw new UnsupportedOperationException("Implement me :)");
    }

    @Override
    public Iterable<GridCell> getTilesOnBoard() {
        throw new UnsupportedOperationException("Implement me :)");
    }
    
}
