//- Endrer modellen basert på tastetrykk fra brukeren 
//og Styrer hvordan brikken faller automatisk over tid.
package no.uib.inf101.tetris.controller;
import java.security.Key;
import no.uib.inf101.tetris.view.TetrisView;
import java.awt.event.KeyListener;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.Timer;

import no.uib.inf101.tetris.midi.TetrisSong;
//import no.uib.inf101.tetris.model.GameState;


/**
 * Kontrollerklasse som håndterer tastetrykk for å flytte tetrominoen.
 */
public class TetrisController implements KeyListener {
    private final ControllableTetrisModel model;
    private final TetrisView view;
    private long lastMoveTime = 0;

    public TetrisController(ControllableTetrisModel model, TetrisView view) {
        this.model = model;
        this.view = view;

        //Registrer denne kontrolleren som KeyListener for view
        view.addKeyListener(this);
        view.setFocusable(true);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Tastetrykk registrert: " + e.getKeyCode());
        long now = System.currentTimeMillis();
        if (now - lastMoveTime < 100) { // 100ms debounce dette er fordi tetromino hoppet 2 rader/kolloner isteden for 1.
            return;
        }
        lastMoveTime = now;

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            // Venstre pil - flytter brikken én kolonne til venstre
            model.moveTetromino(0, -1);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            // Høyre pil - flytter brikken én kolonne til høyre
            model.moveTetromino(0, 1);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            // Ned pil - flytter brikken én rad ned
            model.moveTetromino(1, 0);
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            // Opp pil - for fremtidig rotering av brikken (kan implementeres senere)
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            // Mellomrom - kan brukes til å slippe brikken til bunnen (ikke implementert ennå)
        }
        view.repaint(); // Oppdater GUI
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}


}
