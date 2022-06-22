package kr.ac.snu.sbkim28.snake;

import kr.ac.snu.sbkim28.core.Direction;
import kr.ac.snu.sbkim28.game.GameManager;
import kr.ac.snu.sbkim28.gui.game.GameGUI;
import kr.ac.snu.sbkim28.snake.core.SnakeGameEnvironment;

public class SnakeGameManager extends GameManager {


    private Direction input;
    private SnakeGameEnvironment environment;

    public SnakeGameManager(GameGUI gui, SnakeGameEnvironment environment) {
        super(gui);
        this.environment = environment;
    }

    @Override
    public void initialize() {
        super.initialize();
        renderBody.add(environment.getRenderer());
    }

    @Override
    public void update() {
        if (keyHolder.onUp())
            input = Direction.UP;
        else if (keyHolder.onLeft())
            input = Direction.LEFT;
        else if (keyHolder.onDown())
            input = Direction.DOWN;
        else if (keyHolder.onRight())
            input = Direction.RIGHT;


        if (environment.gameOver()) {
            if (keyHolder.onClicked()) {
                environment.initialize();
            }
        } else if (getTick() % 2 == 0) {
            /*
            int horizontal = keyHolder.getHorizontalAxis();
            int vertical = keyHolder.getVerticalAxis();

            Direction dir = Direction.castInput(horizontal, vertical);
            입력 무시 자주 발생
            */
            environment.move(input);
            input = null;
        }
    }

}
