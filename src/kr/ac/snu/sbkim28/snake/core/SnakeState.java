package kr.ac.snu.sbkim28.snake.core;

import kr.ac.snu.sbkim28.core.IState;

import java.awt.Color;

public enum SnakeState implements IState {
    EMPTY(0, new Color(196, 196, 196)),
    BODY(0B1, new Color(64, 128, 64)),
    HEAD(0B11, new Color(128, 255, 128)),
    COLLISION(0B101, new Color(128, 64, 64)),
    COLLISIONHEAD(0B111, new Color(255, 128, 128)),
    FOOD(0B1000, new Color(64, 64, 128)),
    FOODHEAD(0B10011, new Color(128, 128, 255)),
    WALL(0B10000, new Color(64, 64, 64)),
    COLLISIONWALL(0B10100, new Color(96, 64, 64));

    public final int value;
    public final Color color;

    SnakeState(int value, Color color) {
        this.value = value;
        this.color = color;
    }

    @Override
    public int getState() {
        return value;
    }
}
