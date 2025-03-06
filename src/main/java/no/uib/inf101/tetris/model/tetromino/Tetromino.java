package no.uib.inf101.tetris.model.tetromino;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.List;
import java.util.Iterator;

import no.uib.inf101.grid.*;
import no.uib.inf101.grid.GridDimension;


/**Representerer en Tetromino-brikke i Tetris */
public class Tetromino implements Iterable<GridCell> {

/**
 * Private feltvariabler for en Tetromino
 * @param symbol Symbolet som represneterer hvilken type.
 * @param shape Boolean matrise som definerer formen.
 * @param position Startposijon på brettet.
 */
    private final char symbol;
    private final boolean [][] shape;
    private final CellPosition position;

    private Tetromino(char symbol,boolean [][] shape,CellPosition position){
        this.symbol = symbol;
        this.shape = shape;
        this.position = position;
    }

    /**
     * Lager en ny Tetromino objekt basert på en gitt symbol/type.
     * @param symbol Er symbolet for Tetromino enten ('L', 'J', 'S', 'Z', 'T', 'I', 'O').
     * @return En ny Tetromino objekt.
     * @throws IlligalArgumentException Hvis symbolet macther de definert over. 
     */
    public static Tetromino newTetromino(char symbol){
        boolean [][] shape;
        if (symbol == 'L') {
            shape = new boolean[][]{
                {true, false,false},
                {true, true,true},
                {true, false,false}
            };
        } else if (symbol == 'J') {
            shape = new boolean[][]{
                {false, false,false},
                {true, true,true},
                {false, false,true}
            };
        } else if (symbol == 'S') {
            shape = new boolean[][]{
                {false, false, false},
                {false, true, true},
                {true,true,false}
            };
        } else if (symbol == 'Z') {
            shape = new boolean[][]{
                {false, false, false},
                {true, true, false},
                {false,true,true}
            };
        } else if (symbol == 'T') {
            shape = new boolean[][]{
                {false, false, false},
                {true, true, true},
                {false,true,false}
            };
        } else if (symbol == 'I') {
            shape = new boolean[][]{
                {false,false,false,false},
                {true,true,true,true},
                {false,false,false,false},
                {false,false,false,false}
            };
        } else if (symbol == 'O') {
            shape = new boolean[][]{
                {false,false,false,false},
                {false,true,true,false},
                {false,true,true,false},
                {false,false,false,false}
            };
        } else {
            throw new IllegalArgumentException("Ukjent Tetromino-symbol: " + symbol);
        }
    
        return new Tetromino(symbol, shape, new CellPosition(0, 0));
    }

    /**
     * Retunerer posisjonen av Tetromino.
     * @return Posisjonen.
     */
    public CellPosition getPosition() {
        return position;
    }

    /**
     * Retunerer symbolet av Tetromino.
     * @return Symbolet.
     */
    public char getSymbol() {
        return symbol;
    }

    /**
     * Retunerer fasongen av Tetromino.
     * @return Fasongen.
     */
    public boolean[][] getShape() {
        return shape;
    }

    /**
     * Flytter Tetrominoen til en ny posisjon.
     * @param deltaRow Antall rader å flytte.
     * @param deltaCol Antall kolonne å flytte.
     * @return En ny Tetromino (kopi) med oppdatert posisjon.
     */
    public Tetromino shiftedBy(int deltaRow,int deltaCol){
        return new Tetromino(symbol, shape, new CellPosition(position.row()+deltaRow, position.col()+deltaCol));
    }



    /**
     * Metoden sentrerer brikken i toppen av et gitt rutenett.
     * @param grid Griddimensions objket som representerer brettet.
     * @return En  ny Teromino som er sentrerer på brettet.
     */
    public Tetromino shiftedToTopCenterOf(GridDimension grid){
        int topGridRow=0;
        int centerGridCol=0;
        return new Tetromino(symbol, shape, new CellPosition(topGridRow, centerGridCol));
    }


    /**
     * Retunerer en iterator som iterer over alle cellene i Tetromino brikken.
     * Kun celler som er en del av brikken blir retunert
     * Kommenterer her selvom det er en arvet metode,
     * fordi den har annen implementasjon
     */
    @Override
    public Iterator<GridCell> iterator() {
        List<GridCell> cells = new ArrayList<>();
    
        for (int r = 0; r < shape.length; r++) {
            for (int c = 0; c < shape[r].length; c++) {
                if (shape[r][c]) {  // Kun celler som er en del av brikken
                    CellPosition pos = new CellPosition(position.row() + r, position.col() + c);
                    cells.add(new GridCell(pos, symbol));
                }
            }
        }
        return cells.iterator();
    }


    /**
     * Sammenligner denne Tetrominoen med et annet objekt.
     * to Tetrominoer regnes som like hvis de har samme symbol og samme fasong.
     * @param obj Objketet som skal sammenlignes, den er av typen Objekt.
     * @return {@code true} hvis objektet er en Teromino med samme symbol/fasong.
     */
    @Override
    public boolean equals(Object obj){
        if (this==obj){
            return true;
            }
        if (!(obj instanceof Tetromino other)){
            return false;
            }
        return symbol == other.symbol && Arrays.deepEquals(shape, other.shape);
    }

    /**
     * Beregner hash koden for denne Tetrominoen.
     * Hash koden er basert på symbolet og fasongen, slik at like tetrominoer for samme hash kode.
     * @return En integer verdi som representerer hash koden til Tetrominoen.
     */
    @Override
    public int hashCode(){
        return Objects.hash(symbol, Arrays.deepHashCode(shape));
    }
    


    
}
