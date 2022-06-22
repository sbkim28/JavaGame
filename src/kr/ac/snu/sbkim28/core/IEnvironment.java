package kr.ac.snu.sbkim28.core;

import kr.ac.snu.sbkim28.gui.RenderComposite;

public interface IEnvironment {
    void initialize();
    boolean gameOver();

    RenderComposite getRenderer();
}
