package kr.ac.snu.sbkim28.snake.gui;

import kr.ac.snu.sbkim28.core.IState;
import kr.ac.snu.sbkim28.gui.ColorAdapter;
import kr.ac.snu.sbkim28.snake.core.SnakeState;

import java.awt.*;

public class SnakeColorAdapter implements ColorAdapter {
    @Override
    public Color getColorOfCell(IState stateValue) {
        return ((SnakeState) stateValue).color;
    }
}
