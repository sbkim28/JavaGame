package kr.ac.snu.sbkim28.snake.core;

import kr.ac.snu.sbkim28.core.Direction;
import kr.ac.snu.sbkim28.core.GameCell;

import java.util.Iterator;

public interface SnakeQueue extends Iterable<GameCell<SnakeState>> {

    Direction getPreviousDirection();

    GameCell<SnakeState> getHead();
    void enqueue(GameCell<SnakeState> cell, Direction direction);

    GameCell<SnakeState> dequeue();
    int size();
    boolean isEmpty();
    boolean isFull();
    void clear();

    Iterator<GameCell<SnakeState>> iterator();
}
