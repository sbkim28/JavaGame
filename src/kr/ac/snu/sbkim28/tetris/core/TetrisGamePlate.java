package kr.ac.snu.sbkim28.tetris.core;

import kr.ac.snu.sbkim28.core.GameCell;
import kr.ac.snu.sbkim28.core.GamePlate;
import kr.ac.snu.sbkim28.snake.core.SnakeState;

public class TetrisGamePlate extends GamePlate<TetrisState> {


    public TetrisGamePlate(int sizeX, int sizeY) {
        super(sizeX, sizeY);
    }

    @Override
    protected void clear() {
        int index = 0, posx, posy;
        int maxX = sizeX - 1, maxY = sizeY - 1;
        for(GameCell<TetrisState> cell : this){
            posx = index % sizeX;
            posy = index / sizeX;
            ++index;

            cell.changeState((posx == 0 || posx == maxX || posy == maxY) ?
                    TetrisState.WALL : TetrisState.EMPTY);
        }
    }
}
