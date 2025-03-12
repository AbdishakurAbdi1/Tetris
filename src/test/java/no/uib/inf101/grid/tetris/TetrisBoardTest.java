package no.uib.inf101.grid.tetris;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import no.uib.inf101.tetris.model.TetrisBoard;
import no.uib.inf101.grid.CellPosition;



public class TetrisBoardTest {
    @Test
    public void prettyStringTest() {
        TetrisBoard board = new TetrisBoard(3, 4);
    
        // Legger til test-celler i brettet
        board.set(new CellPosition(0, 0), 'r'); // Rød i øvre venstre hjørne
        board.set(new CellPosition(board.rows()-1, 0), 'b'); // Blå i nedre venstre hjørne
        board.set(new CellPosition(0, board.cols()-1), 'y'); // Gul i øvre høyre hjørne
        board.set(new CellPosition(board.rows()-1, board.cols()-1), 'w');   // Hvit i nedre høyre hjørne
    
        // Forventer at brettet ser slik ut:
        String expected = String.join("\n", new String[]{
            "r--y",
            "----",
            "b--w"
        }) + "\n"; // Må inkludere '\n' fordi prettyString() legger til linjeskift
    
        assertEquals(expected, board.prettyString());
    }

    private TetrisBoard getTetrisBoardWithContents(String[] rows) {
        int numRows = rows.length;
        int numCols = rows[0].length();
        TetrisBoard board = new TetrisBoard(numRows, numCols);
    
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                char symbol = rows[row].charAt(col);
                if (symbol != '-') { // Ikke sett tomme celler eksplisitt
                    board.set(new CellPosition(row, col), symbol);
                }
            }
        }
        return board;
    }

    @Test
    public void testRemoveFullRows() {
        TetrisBoard board = getTetrisBoardWithContents(new String[] {
            "-T",
            "TT",
            "LT",
            "L-",
            "LL"
        });
    
        assertEquals(3, board.clearFilledRows());
    
        String expected = String.join("\n", new String[] {
            "--",
            "--",
            "--",
            "-T",
            "L-"
        });
    
        assertEquals(expected, board.prettyString());
    }
}
