package kr.ac.snu.sbkim28.tetris;

import kr.ac.snu.sbkim28.core.Direction;
import kr.ac.snu.sbkim28.game.GameManager;
import kr.ac.snu.sbkim28.gui.game.GameGUI;
import kr.ac.snu.sbkim28.tetris.core.TetrisGameEnvironment;

public class TetrisGameManager extends GameManager {

    private TetrisGameEnvironment environment;
    private int downtick;
    private int deletetick;
    private long prevtick;
    private boolean started;
    private boolean gameOver;
    private boolean placed;
    private boolean hasDeleteLine;

    private int level;

    public TetrisGameManager(GameGUI gui, TetrisGameEnvironment environment) {
        super(gui);
        this.environment = environment;
    }

    @Override
    public void initialize() {
        super.initialize();
        renderBody.add(environment.getRenderer());
        downtick = 60;
        deletetick = 20;
        level = 0;
    }

    @Override
    public void update() {
        if(!started){
            if(keyHolder.onEnter()){
                environment.initialize();
                prevtick = getTick();
                started = true;
            }

            return;
        }


        if(hasDeleteLine){
            if(getTick() - prevtick >= deletetick){
                environment.deleteLine();
                prevtick = getTick();
                popCheck();
                hasDeleteLine = false;
                setDifficulty();
            }
            return;
        }

        if(keyHolder.onLeft()){
            environment.move(Direction.LEFT);
        }else if(keyHolder.onRight()){
            environment.move(Direction.RIGHT);
        }
        if (keyHolder.onC()){
            environment.rotate(Direction.RIGHT);
        } else if (keyHolder.onZ()){
            environment.rotate(Direction.LEFT);
        }
        if(getTick() - prevtick >= downtick){
            placed = !environment.move(Direction.DOWN);
            prevtick = getTick();

             placed = !environment.move(Direction.DOWN);
        }

        if(keyHolder.onSpace()){
            environment.moveBottom();
            placed = true;
        }

        if(placed){
            hasDeleteLine = environment.checkLine();
            if(hasDeleteLine) {
                environment.highlightDeleteBuffer();
            } else {
                popCheck();
            }
            prevtick = getTick();
            placed = false;
        }
    }

    private void setDifficulty(){
        if(environment.getLines() >= 10 + level * 10){
            ++level;
            downtick -= 9;
            deletetick -= 3;
        }
    }

    private void popCheck(){
        boolean ret = environment.pop();
        if(!ret){
            started = false;
            gameOver = true;
        }
    }
}
