package kr.ac.snu.sbkim28.snake.core;

import kr.ac.snu.sbkim28.core.Direction;
import kr.ac.snu.sbkim28.core.GameCell;
import kr.ac.snu.sbkim28.core.GamePlate;

import java.util.Iterator;

public class SnakeGamePlate extends GamePlate<SnakeState>{
    public SnakeGamePlate(int sizeX, int sizeY) {
        super(sizeX, sizeY);
    }

    @Override
    public void clear() {
        int index = 0, posx, posy;
        int maxX = sizeX - 1, maxY = sizeY - 1;
        for(GameCell<SnakeState> cell : this){
            posx = index % sizeX;
            posy = index / sizeX;
            ++index;

            cell.changeState((posx == 0 || posx == maxX || posy == 0 || posy == maxY) ?
                    SnakeState.WALL : SnakeState.EMPTY);
        }
    }
}
