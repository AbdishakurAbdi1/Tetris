package no.uib.inf101.tetris;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;

import no.uib.inf101.tetris.model.TetrisModel;
import no.uib.inf101.tetris.view.TetrisView;

import no.uib.inf101.tetris.model.tetromino.TetrominoFactory;
import no.uib.inf101.tetris.model.tetromino.RandomTetrominoFactory;
import no.uib.inf101.tetris.controller.TetrisController;

//AA

public class Main {

    public static final String WINDOW_TITLE = "INF101 TETRIS";

    public static void main(String[] args) {
        TetrominoFactory factory = new RandomTetrominoFactory(); // Opprett en TetrominoFactory
        TetrisModel model = new TetrisModel(factory); // Send factory til TetrisModel
        TetrisView view = new TetrisView(model);

        // Opprett en TetrisController og koble det til modellen og visningen
        new TetrisController(model, view);
      
        view.requestFocusInWindow(); // Sørger for at vinduet fanger opp tatstetrykk

        JFrame frame = new JFrame(WINDOW_TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(view);
        frame.pack();
        frame.setVisible(true);

        // Åpne GUI i midten av skjermen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
    }
}
