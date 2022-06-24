package kr.ac.snu.sbkim28.tetris.core.tetromino;

import kr.ac.snu.sbkim28.core.Direction;
import kr.ac.snu.sbkim28.core.IntVector2;

import java.util.Iterator;

public interface IPolyomino extends Iterable<IntVector2> {
    int getSize();
    void move(Direction dir);
    void move(IntVector2 vector2);
    void rotate(Direction dir);

    @Override
    Iterator<IntVector2> iterator();
}
