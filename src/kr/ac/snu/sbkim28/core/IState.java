package kr.ac.snu.sbkim28.core;

/**
 * @author sbkim28
 * IState is the interface of State class.
 * State of {@link GameCell} must implement this interface. <br>
 * IState는 상태를 나타내는 class의 interface이다.
 * {@link GameCell}의 state는 이 interface를 implement하여야 한다.
 */
public interface IState {
    /**
     * Convert current state to integer value. <br>
     * 현재 상태를 정수 값으로 변환한다.
     * @return integer value of current state
     */
    int getState();
}
