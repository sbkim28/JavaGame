package kr.ac.snu.sbkim28.core;


/**
 * @author sbkim28
 * GameCell is a class that composes the grid of {@link GamePlate}.
 * GameCell has two coordinates 'x', 'y'. They are readonly and unchangable.
 * GameCell has {@link GameCell#state} which shows the current state of Cell. <br>
 * GameCell은 {@link GamePlate}의 격자를 구성하는 객체의 class이다.
 * GameCell은 'x', 'y' 두 개의 좌표를 가지고 있으며, 이 좌표는 변경할 수 없다.
 * GameCell은 현재 GameCell의 상태를 보여주는 {@link GameCell#state}를 멤버 변수로 갖는다.
 * @param <T> The class of {@link GameCell#state}. T must implement {@link IState} interface <br>
 *           {@link GameCell#state}의 클래스. T는 {@link IState} interface를 implement 하여야 한다.
 */
public class GameCell<T extends IState>{
    public final int x;
    public final int y;
    /**
     * State of Cell.
     * Cell의 상태
     */
    private T state;

    /**
     * @param x Cell의 x좌표
     * @param y Cell의 y좌표
     */
    public GameCell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Change the {@link GameCell#state} of this cell to new state. <br>
     * 현재 cell의 상태 {@link GameCell#state}를 새로운 상태로 변경한다.
     * @param newState new state.
     */
    public void changeState(T newState){
        this.state = newState;
    }

    /**
     * get current {@link GameCell#state} of this cell. <br>
     * 현재 cell의 상태 {@link GameCell#state}를 반환한다.
     * @return current state.
     */
    public T getState(){
        return state;
    }

    @Override
    public String toString() {
        return "Cell=(" + x + ", " + y + ", " + "State= " + getState() + ")";
    }
}
