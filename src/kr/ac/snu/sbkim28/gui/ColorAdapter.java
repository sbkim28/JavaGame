package kr.ac.snu.sbkim28.gui;

import kr.ac.snu.sbkim28.core.GameCell;
import kr.ac.snu.sbkim28.core.IState;

import java.awt.*;

public interface ColorAdapter {
    Color getColorOfCell(IState stateValue);
}
