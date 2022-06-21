package kr.ac.snu.sbkim28.game;

import java.awt.event.KeyEvent;

/**
 * @author sbkim28
 * Class for holding the inputs of users.
 * The values can indirectly be changed through {@link Setter}
 * methods with name containing 'on' checks if the specific key pressed in the current frame.
 * <br>
 * 유저의 입력을 담기 위한 클래스
 * 값은 {@link Setter}을 통해서 간접적으로만 수정된다.
 * on이라는 이름을 갖는 메써드는 특정 키가 현재의 프레임에 눌렸는지를 검사한다.
 */
public class InputHolder {
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;
    private boolean mouseLeft;
    private boolean c;
    private boolean z;
    private boolean space;
    private boolean shift;
    private boolean enter;
    private int horizontalAxis;
    private int verticalAxis;

    private boolean onC;
    private boolean onZ;
    private boolean onSpace;
    private boolean onShift;
    private boolean onEnter;

    private boolean onLeft;
    private boolean onRight;
    private boolean onUp;
    private boolean onDown;
    private boolean onClicked;

    public boolean onLeft() {
        return onLeft;
    }

    public boolean onRight() {
        return onRight;
    }

    public boolean onUp() {
        return onUp;
    }

    public boolean onDown() {
        return onDown;
    }

    /**
     * checks if mouse clicked at the very current frame.
     */
    public boolean onClicked(){
        return onClicked;
    }

    public boolean onC() {
        return onC;
    }

    public boolean onZ() {
        return onZ;
    }

    public boolean onSpace() {
        return onSpace;
    }

    public boolean onShift() {
        return onShift;
    }
    public boolean onEnter() {
        return onEnter;
    }


    /**
     * returns the user input of horizontal axis. <br>
     * 수평 방향을 나타내는 유저의 입력을 반환한다.
     *
     */
    public int getHorizontalAxis() {
        return horizontalAxis;
    }

    /**
     * returns the user input of vertical axis. <br>
     * 수직 방향을 나타내는 유저의 입력을 반환한다.
     */
    public int getVerticalAxis() {
        return verticalAxis;
    }

    class Setter {

        Setter(){}

        public void keyDown(int keyCode){
            switch (keyCode) {
                case KeyEvent.VK_C:
                    c = true;
                    onC = true;
                    break;
                case KeyEvent.VK_Z:
                    z = true;
                    onZ = true;
                    break;
                case KeyEvent.VK_SHIFT:
                    shift = true;
                    onShift = true;
                    break;
                case KeyEvent.VK_SPACE:
                    space = true;
                    onSpace = true;
                    break;
                case KeyEvent.VK_ENTER:
                    enter = true;
                    onEnter = true;
                    break;
                case KeyEvent.VK_D:
                case KeyEvent.VK_RIGHT:
                    right = true;
                    onRight = true;
                    setHorizontal();
                    break;
                case KeyEvent.VK_A:
                case KeyEvent.VK_LEFT:
                    left = true;
                    onLeft = true;
                    setHorizontal();
                    break;
                case KeyEvent.VK_W:
                case KeyEvent.VK_UP:
                    up = true;
                    onUp = true;
                    setVertical();
                    break;
                case KeyEvent.VK_S:
                case KeyEvent.VK_DOWN:
                    down = true;
                    onDown = true;
                    setVertical();
                    break;
            }
        }

        public void keyUp(int keyCode) {
            switch (keyCode) {
                case KeyEvent.VK_C:
                    c = false;
                    break;
                case KeyEvent.VK_Z:
                    z = false;
                    break;
                case KeyEvent.VK_SHIFT:
                    shift = false;
                    break;
                case KeyEvent.VK_SPACE:
                    space = false;
                    break;
                case KeyEvent.VK_ENTER:
                    enter = false;
                    break;
                case KeyEvent.VK_D:
                case KeyEvent.VK_RIGHT:
                    right = false;
                    setHorizontal();
                    break;
                case KeyEvent.VK_A:
                case KeyEvent.VK_LEFT:
                    left = false;
                    setHorizontal();
                    break;
                case KeyEvent.VK_W:
                case KeyEvent.VK_UP:
                    up = false;
                    setVertical();
                    break;
                case KeyEvent.VK_S:
                case KeyEvent.VK_DOWN:
                    down = false;
                    setVertical();
                    break;
            }
        }

        public void mouseDown(){
            mouseLeft = true;
            onClicked = true;
        }
        public void mouseUp(){
            mouseLeft = false;
        }
        private void setHorizontal(){
            int value = 0;
            if (left)
                --value;
            if (right)
                ++value;
            horizontalAxis = value;
        }
        private void setVertical(){
            int value = 0;
            if (up)
                ++value;
            if (down)
                --value;
            verticalAxis = value;

        }
        public void flash(){
            onLeft = false;
            onRight = false;
            onUp = false;
            onDown = false;
            onClicked = false;
            onZ = false;
            onC = false;
            onShift = false;
            onSpace = false;
            onEnter = false;
        }
    }
}
