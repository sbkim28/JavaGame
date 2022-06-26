package kr.ac.snu.sbkim28.tetris.gui;

import kr.ac.snu.sbkim28.core.IState;
import kr.ac.snu.sbkim28.gui.ColorAdapter;
import kr.ac.snu.sbkim28.tetris.core.TetrisState;

import java.awt.*;

public class TetrisColorAdapter implements ColorAdapter {


    public static final Color EMPTY = new Color(64, 64, 64);
    public static final Color WALL = new Color(48, 48, 48);
    public static final Color L = new Color(192, 128, 0);
    public static final Color J = new Color(64, 64, 192);
    public static final Color S = new Color(64, 192, 64);
    public static final Color Z = new Color(192, 64, 64);
    public static final Color T = new Color(192, 32, 192);
    public static final Color I = new Color(0, 128, 192);
    public static final Color O = new Color(192, 192, 32);
    public static final Color HIGHLIGHT = new Color(192, 192, 192);
    @Override
    public Color getColorOfCell(IState stateValue) {
        return switch (stateValue.getState()){
            case 0 -> EMPTY;
            case 8 -> WALL;
            case 1, 1 + 8 -> L;
            case 2, 2 + 8 -> J;
            case 3, 3 + 8 -> S;
            case 4, 4 + 8 -> Z;
            case 5, 5 + 8 -> T;
            case 6, 6 + 8 -> I;
            case 7, 7 + 8 -> O;
            case 32 ->  HIGHLIGHT;
            default -> throw new IllegalArgumentException("Invalid " + stateValue);
        };
    }
}
