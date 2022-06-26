package kr.ac.snu.sbkim28.tetris.core;

import kr.ac.snu.sbkim28.core.IState;

public enum TetrisState implements IState {
    EMPTY(0),
    L(1),
    J(2),
    S(3),
    Z(4),
    T(5),
    I(6),
    O(7),
    WALL(8),
    L_PLACED(9),
    J_PLACED(10),
    S_PLACED(11),
    Z_PLACED(12),
    T_PLACED(13),
    I_PLACED(14),
    O_PLACED(15),
    HIGHLIGHTED(32);

    public final int value;

    TetrisState(int value) {
        this.value = value;
    }


    @Override
    public int getState() {
        return value;
    }

    public static TetrisState getPlaced(TetrisState state){
        return values()[state.value + 8];
    }

    public static TetrisState getTetris(int value){
        return values()[value];
    }

    public boolean isWall(){
        return value >= 8;
    }
}
