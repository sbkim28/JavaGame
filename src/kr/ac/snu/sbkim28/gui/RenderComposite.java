package kr.ac.snu.sbkim28.gui;

import java.awt.*;

/**
 * @author sbkim28
 * The interface of classes for rendering.
 * It has {@link RenderComposite#render(Graphics)} methods where objects can be drawn.
 * 렌더링을 담당하는 class의 interface.
 * 
 */
public interface RenderComposite {
    /**
     * render object to gui canvas.
     * @param g
     */
    void render(Graphics g);
}
