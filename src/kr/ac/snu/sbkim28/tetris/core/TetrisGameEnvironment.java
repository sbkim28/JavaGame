package kr.ac.snu.sbkim28.tetris.core;

import kr.ac.snu.sbkim28.core.Direction;
import kr.ac.snu.sbkim28.core.GameCell;
import kr.ac.snu.sbkim28.core.IEnvironment;
import kr.ac.snu.sbkim28.core.IntVector2;
import kr.ac.snu.sbkim28.gui.PlateRenderer;
import kr.ac.snu.sbkim28.gui.RenderComposite;
import kr.ac.snu.sbkim28.tetris.core.tetromino.IPolyomino;
import kr.ac.snu.sbkim28.tetris.gui.TetrisColorAdapter;

import java.util.Random;

public class TetrisGameEnvironment implements IEnvironment {

    public static final int X = 10;
    public static final int Y = 20;
    public static final int GROUP_LENGTH= 7;
    public static final int TETRO_LENGTH = 4;
    private final Random random;
    private TetrisGamePlate plate;
    private PlateRenderer renderer;

    private final int x;
    private final int y;
    private final int start_x;
    private final int start_y;
    private boolean gameOver;
    private int lines;

    private int cursor = 0;

    private IPolyomino polyomino;
    private TetrominoFactory[] tFactories;
    private TetrisState state;
    private GameCell<TetrisState>[] buffer;

    private int[] deleteBuffer;
    private int deleteLen;

    public TetrisGameEnvironment(Random random){
        this.random = random;
        x = X;
        y = Y;
        start_x = X / 2;
        start_y = 0;
        plate = new TetrisGamePlate(x + 2, y + 1);
        this.renderer = new PlateRenderer(plate);
        renderer.setAdapter(new TetrisColorAdapter());
        tFactories = TetrominoFactory.values();
        deleteBuffer = new int[TETRO_LENGTH];
        buffer = new GameCell[TETRO_LENGTH];
        deleteLen = 0;
    }

    public int getLines() {
        return lines;
    }

    @Override
    public void initialize() {
        gameOver = false;
        lines = 0;
        plate.clear();
        generateRandomOrder();
        pop();
    }

    public boolean pop() {
        polyomino = tFactories[cursor].createPolyomino();
        state = tFactories[cursor++].state;
        if (cursor == GROUP_LENGTH)
            generateRandomOrder();

        polyomino.move(new IntVector2(start_x - polyomino.getSize() / 2, start_y));
        for (IntVector2 vec : polyomino){
            if(plate.get(vec.x, vec.y).getState().isWall()){
                return false;
            }
            plate.get(vec.x, vec.y).changeState(state);
        }
        return true;
    }

    public void moveBottom(){
        while (move(Direction.DOWN));
    }
    public boolean move(Direction direction){
        setBuffer();
        polyomino.move(direction);

        boolean ret;
        if(!(ret = valid())) {
            polyomino.move(Direction.getOpposite(direction));
            if(direction == Direction.DOWN){
                put();
            }
        } else {
            changeState();
        }
        return ret;
    }

    public boolean rotate(Direction direction){
        setBuffer();
        polyomino.rotate(direction);
        boolean ret;
        if(!(ret = valid())){
            polyomino.rotate(Direction.getOpposite(direction));
        } else {
            changeState();
        }
        return ret;
    }

    private void changeState(){
        for (GameCell<TetrisState> cell: buffer){
            cell.changeState(TetrisState.EMPTY);
        }
        for (IntVector2 vec: polyomino){
            plate.get(vec.x, vec.y).changeState(state);
        }
    }
    private void setBuffer(){
        int i = 0;
        for (IntVector2 vec: polyomino) {
            buffer[i++] = plate.get(vec.x, vec.y);
        }
    }

    public boolean checkLine(){
        int index = 0;
        Iterable<GameCell<TetrisState>[]> rows = plate.rows();
        l: for (GameCell<TetrisState>[] row : rows){
            if(index == y) {
                ++index;
                continue;
            }

            for(GameCell<TetrisState> cell : row){
                if(!cell.getState().isWall()) {
                    ++index;
                    continue l;
                }
            }
            deleteBuffer[deleteLen++] = index;
            ++index;
        }

        return deleteLen > 0;
    }

    public void highlightDeleteBuffer(){
        for (int i = 0; i<deleteLen; ++i) {
            for (int k = 1; k<=X; ++k){
                plate.get(k, deleteBuffer[i]).changeState(TetrisState.HIGHLIGHTED);
            }
        }
    }

    public void deleteLine(){
        for (int i = 0; i<deleteLen; ++i){
            for (int j = deleteBuffer[i];j > 0; --j){
                for (int k = 1; k<=X; ++k){
                    plate.get(k, j).changeState(plate.get(k, j - 1).getState());
                }
            }
        }
        lines += deleteLen;
        deleteLen = 0;
    }
    private void put(){
        for (IntVector2 vec: polyomino){
            plate.get(vec.x, vec.y)
                    .changeState(TetrisState.getPlaced(state));
        }
    }

    private boolean valid(){
        for (IntVector2 vec : polyomino){
            if(plate.get(vec.x, vec.y).getState().isWall()){
                return false;
            }
        }
        return true;
    }

    private void generateRandomOrder(){
        cursor = 0;
        for (int i = GROUP_LENGTH - 1; i > 0; --i){
            int index = random.nextInt(i + 1);
            TetrominoFactory tmp = tFactories[index];
            tFactories[index] = tFactories[i];
            tFactories[i] = tmp;
        }
    }


    @Override
    public boolean gameOver() {
        return gameOver;
    }

    @Override
    public RenderComposite getRenderer(){
        return renderer;
    }
}
