package kr.ac.snu.sbkim28.gui.game;

import kr.ac.snu.sbkim28.gui.RenderComposite;
import kr.ac.snu.sbkim28.gui.Renderable;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class GameCanvas extends Canvas implements Renderable {

    private RenderComposite renderComposite;

    public GameCanvas(int x, int y) {
        renderComposite = g -> {};
        Dimension size = new Dimension(x, y);
        setPreferredSize(size);
        setFocusable(false);
    }

    public void setRenderComposite(RenderComposite renderComposite) {
        this.renderComposite = renderComposite;
    }

    public RenderComposite getRenderComposite() {
        return renderComposite;
    }

    @Override
    public void render(){
        BufferStrategy bs = getBufferStrategy();
        if(bs == null){
            createBufferStrategy(2);
            bs = getBufferStrategy();
        }
        Graphics g = bs.getDrawGraphics();
        renderComposite.render(g);
        g.dispose();
        bs.show();
    }
}
