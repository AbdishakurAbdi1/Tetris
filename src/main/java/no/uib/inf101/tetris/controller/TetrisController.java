//- Endrer modellen basert på tastetrykk fra brukeren 
//og Styrer hvordan brikken faller automatisk over tid.
package no.uib.inf101.tetris.controller;


import no.uib.inf101.tetris.view.TetrisView;
import java.awt.event.KeyListener;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.Timer;

import no.uib.inf101.tetris.midi.TetrisSong;
import no.uib.inf101.tetris.model.GameState;

/**
 * Kontrollerklasse som håndterer tastetrykk for å flytte tetrominoen.
 */
public class TetrisController implements KeyListener {
    private final ControllableTetrisModel model;
    private final TetrisView view;
    private long lastMoveTime = 0;
    private GameState gameState;
    private final Timer timer;
    private final TetrisSong song;


    /**
     * Oppretter en TetrisController som styrer spillet og håndterer brukerinput.
     * 
     * @param model Spillmodellen som håndterer logikken.
     * @param view Visningen som tegner spillet.
     */
    public TetrisController(ControllableTetrisModel model, TetrisView view) {
        this.model = model;
        this.view = view;
        this.gameState = this.model.getGameState();

        // Initialiserer timer
        this.timer = new Timer(model.getTimerDelay(), this::clockTick);

        // Initialiserer song
        this.song = new TetrisSong();

        // Registrer denne kontrolleren som KeyListener for view
        view.addKeyListener(this);
        view.setFocusable(true);

        //Starter timer og song
        timer.start();
        song.run();
    }

    /**
     * Håndterer tastetrykk i Tetris-spillet.
     * Håndterer også rotering eller slipping av Tetromino brikken. Den ignorerer 
     * input hvis spillet er over.
     * 
     * @param e KeyEvent-objektet som inneholder informasjon om tastetrykket.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(gameState);
        // Blokkerer input hvis spillet er over
        if (gameState == GameState.GAME_OVER) {
            return;
        }
        System.out.println("Tastetrykk registrert: " + e.getKeyCode());
        long now = System.currentTimeMillis();
        if (now - lastMoveTime < 100) { // 100ms debounce for å unngå doble bevegelser
            return;
        }
        lastMoveTime = now;
        boolean moved = false; // sjekker om brikken faktisk beveger seg.
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            // Venstre pil - flytter brikken en kolonne til venstre
            moved = this.model.moveTetromino(0, -1);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            // Høyre pil - flytter brikken en kolonne til høyre
            moved = this.model.moveTetromino(0, 1);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            // Ned pil - flytter brikken en rad ned
            moved = this.model.moveTetromino(1, 0);
            if (moved) {
                timer.restart(); // Start timeren på nytt kun hvis brikken flyttet seg
            }
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            // Opp pil - som roterer Tetromino
            this.model.rotateTetromino();
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            this.model.dropTetromino(); // Dropper brikken til bunnen
            timer.restart(); // Start timeren på nytt etter drop
        }
        view.repaint(); // Oppdaterer GUI
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    /**
     * Håndterer den automatiske bevegelsen av den fallende tetrominoen.
     * Den sjekker om spillet er aktivt og flytter tetrominoen nedover.
     * Hvis tetrominoen ikke kan bevege seg lenger, låses den fast,
     * og en ny tetromino genereres.
     *
     * @param e ActionEvent som blir utløst av timeren.
     */
    private void clockTick(ActionEvent e) {
        System.out.println("Timer tick! Brikken skal flytte seg ned.");
        // System.out.println(model.getGameState());
        if (model.getGameState() == GameState.ACTIVE_GAME) {
            model.clockTick();// //Utfører et klokkeslag i modellen (flytt brikken nedover)
            getDelay(); // Juster timerforsinkelsen hvis nødvendig
            System.out.println("Repainting view!");
            view.repaint(); // Oppdater GUI
        }
    }

    /**
     * Denne metoden henter den gjeldende forsinkelsen fra modellen og setter
     * den på timeren, slik at tetrominoen faller med riktig hastighet.
     */
    private void getDelay() {
        int newDelay = model.getTimerDelay();
        timer.setDelay(newDelay);
        timer.setInitialDelay(newDelay);
    }
}
