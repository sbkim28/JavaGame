package kr.ac.snu.sbkim28.core;

import kr.ac.snu.sbkim28.snake.core.SnakeState;

import java.util.Iterator;

/**
 * @author sbkim28
 * GamePlate is class that handles the grid of plate.
 * Grid is composed of {@link GameCell}. <br>
 * Gameplate는 게임 판의 격자를 관리하는 class이다.
 * 격자는 {@link GameCell} 객체로 구성되어 있다.
 * @param <T> T determines the type of cell.
 *           T must extend {@link GameCell} class. <br>
 *           T는 격자에 들어갈 type을 결정한다.
 *           T는 {@link GameCell}을 상속받은 class여야 한다.
 */
public abstract class GamePlate<T extends IState>
        implements Iterable<GameCell<T>>{

    private final GameCell<T>[][] cells;

    /**
     * x size of the grid. <br>
     * 격자의 x 크기.
     */
    public final int sizeX;

    /**
     * y size of the grid. <br>
     * 격자의 y 크기.
     */
    public final int sizeY;

    public GamePlate(int sizeX, int sizeY) {
        cells = new GameCell[sizeY][sizeX];
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        int i, j;
        for(i = 0; i<sizeY; ++i){
            for (j = 0;j<sizeX; ++j){
                cells[i][j] = new GameCell<>(j, i);
            }
        }
        clear();

        assert cells[0][0].getState() != null;
    }

    /**
     * Get the iterator that loops all of the cells in the grid.
     * The order is x first, y last; (0, 0) -> (1, 0) -> (2, 0) -> ... -> (0, 1) -> (1, 1) -> .... <br>
     * 격자 내 모든 cell을 순회하는 iterator를 반환한다.
     * x가 우선이고, y가 나중이다; (0, 0) -> (1, 0) -> (2, 0) -> ... -> (0, 1) -> (1, 1) -> ....
     * @return Iterator
     */
    @Override
    public Iterator<GameCell<T>> iterator() {
        return new Iterator<>() {
            private final int size = sizeX * sizeY;
            private int cursor = 0;
            @Override
            public boolean hasNext() {
                return cursor < size;
            }

            @Override
            public GameCell<T> next() {
                return cells[cursor / sizeX][cursor++ % sizeX];
            }
        };
    }

    public Iterable<GameCell<T>[]> rows(){
        return () -> new Iterator<>() {
            private int cursor = 0;

            @Override
            public boolean hasNext() {
                return cursor < sizeY;
            }

            @Override
            public GameCell<T>[] next() {
                return cells[cursor++];
            }
        };
    }

    /**
     * Get the {@link GameCell} instance in the grid by the coordinate x, y <br>
     * 좌표 x, y에 의하여 격자 내 {@link GameCell} 객체를 반환한다.
     * @param x coordinate x
     * @param y coordinate y
     * @return GameCell located at (x, y)
     * @throws ArrayIndexOutOfBoundsException when x or y is negative or over the size of the grid.
     */
    public GameCell<T> get(int x, int y) {
        return cells[y][x];
    }

    /**
     * Get the {@link GameCell} instance in the grid by the coordinate x, y and the direction.
     * Start from (x, y) and move 1 toward the direction.
     * It returns the {@link GameCell} instance found at destination. <br>
     * 좌표 x, y와 방향을 이용해서 격자의 객체를 가져온다.
     * (x, y)좌표에서 시작해서 방향을 향해 1칸 움직인다.
     * 해당 도착지에서 얻은 {@link GameCell} 객체를 반환한다.
     * @param x coordinate x
     * @param y coordinate y
     * @param direction direction.
     * @return GameCell located at destination.
     * @throws ArrayIndexOutOfBoundsException when destination is out of the range of grid.
     */
    public GameCell<T> get(int x, int y, Direction direction){
        return switch (direction) {
            case LEFT -> cells[y][x - 1];
            case RIGHT -> cells[y][x + 1];
            case UP -> cells[y - 1][x];
            case DOWN -> cells[y + 1][x];
            default -> throw new IllegalArgumentException("Invalid Direction=" + direction);
        };
    }

    /**
     * Reset all the {@link GameCell} instances to the initial states.
     * This method does not replace the existing GameCell instance with new one.
     * It only changes the state of the GameCell instance. <br>
     * 모든 {@link GameCell} 객체을 초기 상태로 설정한다.
     * 이 method는 기존의 {@link GameCell} 객체를 새로운 것으로 교체하지 않는다.
     * 이 method는 {@link GameCell} 객체의 상태만을 변경한다.
     */
    protected abstract void clear();

}
