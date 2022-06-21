package kr.ac.snu.sbkim28.gui.game;

import kr.ac.snu.sbkim28.gui.RenderComposite;
import kr.ac.snu.sbkim28.gui.Renderable;

import javax.swing.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

public class GameGUI extends JFrame implements Renderable {

    private final GameCanvas gameCanvas;

    public GameGUI(String title, GameCanvas gameCanvas) {
        super(title);
        this.gameCanvas = gameCanvas;
        setTitle(title);
        add(gameCanvas);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setVisible(true);
        requestFocus();
        setLocationRelativeTo(null);
    }

    @Override
    public synchronized void addKeyListener(KeyListener l) {
        super.addKeyListener(l);
        gameCanvas.addKeyListener(l);
    }

    @Override
    public synchronized void addMouseListener(MouseListener l) {
        super.addMouseListener(l);
        gameCanvas.addMouseListener(l);
    }

    /**
     * Delegate methods {@link GameCanvas#render()}
     */
    @Override
    public void render(){
        gameCanvas.render();
    }

    public void setRenderComposite(RenderComposite composite) {
        gameCanvas.setRenderComposite(composite);
    }
}
