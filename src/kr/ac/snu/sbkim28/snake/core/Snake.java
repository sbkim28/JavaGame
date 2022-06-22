package kr.ac.snu.sbkim28.snake.core;

import kr.ac.snu.sbkim28.core.Direction;
import kr.ac.snu.sbkim28.core.GameCell;

import java.util.*;

public class Snake implements SnakeQueue{
    private final GameCell<SnakeState>[] snakes;
    private Direction previous;
    private int front;
    private int rear;
    private int length;
    private int capacity;

    private GameCell<SnakeState> head;

    public Snake(int capacity) {
        this.length = capacity + 1;
        snakes = new GameCell[length];
        this.capacity = capacity;
    }


    @Override
    public Direction getPreviousDirection(){
        return previous;
    }

    @Override
    public GameCell<SnakeState> getHead() {
        return head;
    }

    @Override
    public void enqueue(GameCell<SnakeState> cell, Direction direction) {
        if (isFull())
            throw new IllegalStateException("Queue is full");
        this.previous = direction;
        snakes[rear] = cell;
        head = cell;
        rear = (rear + 1) % length;
    }

    @Override
    public GameCell<SnakeState> dequeue() {
        if (isEmpty())
            throw new NoSuchElementException("Queue is Empty");
        GameCell<SnakeState> cell = snakes[front];
        front = (front + 1) % length;
        return cell;
    }

    @Override
    public int size() {
        return (rear - front + length) % length;
    }

    @Override
    public boolean isEmpty() {
        return front == rear;
    }

    @Override
    public boolean isFull() {
        return (rear + 1) % length == front;
    }

    @Override
    public void clear() {
        front = 0;
        rear = 0;
    }

    @Override
    public Iterator<GameCell<SnakeState>> iterator() {
        return new Iterator<GameCell<SnakeState>>() {
            int i = front;
            @Override
            public boolean hasNext() {
                return i % length != rear;
            }

            @Override
            public GameCell<SnakeState> next() {
                return snakes[i++ % length];
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Snake, ");
        builder.append("front=").append(front)
                .append(", rear=").append(rear).append("\ncells={");
        for (GameCell<SnakeState> cell : this)
            builder.append(cell).append(", ");
        builder.append("}");
        return builder.toString();
    }

}
