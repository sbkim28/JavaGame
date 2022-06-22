package kr.ac.snu.sbkim28.snake.core;

import kr.ac.snu.sbkim28.core.Direction;
import kr.ac.snu.sbkim28.core.GameCell;
import kr.ac.snu.sbkim28.core.IEnvironment;
import kr.ac.snu.sbkim28.gui.PlateRenderer;
import kr.ac.snu.sbkim28.gui.RenderComposite;
import kr.ac.snu.sbkim28.snake.gui.SnakeColorAdapter;

import java.util.Random;

public class SnakeGameEnvironment implements IEnvironment {
    private final Random random;
    private SnakeGamePlate plate;
    private PlateRenderer renderer;
    private SnakeQueue snake;
    private final int x;
    private final int y;
    private final int start_x;
    private final int start_y;
    private boolean gameOver;
    private boolean win;

    public SnakeGameEnvironment(int x, int y, Random random) {
        if (x < 5 && y < 5)
            throw new IllegalArgumentException("Too small plate");
        this.random = random;
        this.x = x;
        this.y = y;
        plate = new SnakeGamePlate(x + 2, y + 2);
        snake = new Snake(x * y);
        this.renderer = new PlateRenderer(plate);
        renderer.setAdapter(new SnakeColorAdapter());
        start_x = x / 2 + 1;
        start_y = y / 2 + 1;
        initialize();
    }

    public void initialize(){
        gameOver = false;
        win = false;
        plate.clear();
        snake.clear();
        assert plate.get(start_x, start_y).getState() == SnakeState.EMPTY;
        assert plate.get(start_x, start_y, Direction.DOWN).getState() == SnakeState.EMPTY;
        snake.enqueue(plate.get(start_x, start_y, Direction.DOWN), Direction.UP);
        snake.getHead().changeState(SnakeState.BODY);
        snake.enqueue(plate.get(start_x, start_y), Direction.UP);
        snake.getHead().changeState(SnakeState.HEAD);
        generateFood();
    }

    public void generateFood(){
        int len = x * y - snake.size();
        GameCell<SnakeState>[] cells = new GameCell[len];
        if (len <= 0){
            gameOver = true;
            win = true;
            return;
        }
        int index = 0;
        for (GameCell<SnakeState> cell : plate){
            if(cell.getState() == SnakeState.EMPTY)
                cells[index++] = cell;
        }
        assert index == cells.length;


        cells[random.nextInt(index)].changeState(SnakeState.FOOD);

    }

    public void move(Direction direction){
        if(gameOver)
            return;

        Direction prev = snake.getPreviousDirection();
        if(direction == null || Direction.isOpposite(prev, direction)){
            direction = prev;
        }
        GameCell<SnakeState> head = snake.getHead();
        GameCell<SnakeState> next = plate.get(head.x, head.y, direction);
        switch (next.getState()) {
            case WALL -> {
                next.changeState(SnakeState.COLLISIONWALL);
                head.changeState(SnakeState.COLLISIONHEAD);
                gameOver = true;
            }
            case BODY -> {
                GameCell<SnakeState> tail = snake.dequeue();
                if (next != tail) {
                    next.changeState(SnakeState.COLLISION);
                    head.changeState(SnakeState.COLLISIONHEAD);
                    gameOver = true;
                }else{
                    snake.enqueue(next, direction);
                    head.changeState(SnakeState.BODY);
                    next.changeState(SnakeState.HEAD);
                }
            }
            case EMPTY -> {
                snake.dequeue().changeState(SnakeState.EMPTY);
                snake.enqueue(next, direction);
                head.changeState(SnakeState.BODY);
                next.changeState(SnakeState.HEAD);
            }
            case FOOD -> {
                snake.enqueue(next, direction);
                head.changeState(SnakeState.BODY);
                next.changeState(SnakeState.FOODHEAD);
                generateFood();
            }
            default -> throw new IllegalStateException("Not able");
        }
    }


    public boolean gameOver(){
        return gameOver;
    }

    @Override
    public RenderComposite getRenderer(){
        return renderer;
    }

}
