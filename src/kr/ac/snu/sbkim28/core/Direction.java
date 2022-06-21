package kr.ac.snu.sbkim28.core;

/**
 * @author sbkim28 <p>
 * Direction is an enum class that shows the information about direction.
 * 4 consts. LEFT, RIGHT, DOWN, UP.
 * There are some useful static methods when dealing with directions.</p>
 * <p>
 * Direction은 방향 정보를 가리키는 enum 클래스이다.
 * Direction에는 4개의 상수가 있다. LEFT, RIGHT, DOWN, UP
 * Direction에는 방향을 처리할 때 유용한 static methods가 있다.</p>
 * */
public enum Direction {
    LEFT(-1, 0), RIGHT(1, 0), DOWN(0, 1), UP(0, -1);

    public final int x;
    public final int y;

    /**
     * Constructor of Enum Direction.
     * @param x x component of normalized vector <br>
     *          해당 방향을 단위벡터로 나타냈을 때 벡터의 x성분
     * @param y y component of normalized vector <br>
     *          해당 방향을 단위벡터로 나타냈을 때 벡터의 y성분
     */
    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Cast Input value to Direction Enum <br>
     * 입력 값을 Enum 값으로 바꾼다.
     * @param horizontal horizontal axis input <br>
     *                   수평 축 입력
     * @param vertical vertical axis input<br>
     *                 수직 축 입력
     * @return Direction. If horizontal and vertical is both 0, then the return value will be null. <br>
     * 입력 값에 대응되는 Direction을 반환함. horizontal과 vertical이 모두 0이면 null을 반환.
     */
    public static Direction castInput(int horizontal, int vertical){
        Direction ret = null;
        if(horizontal == 1)
            ret = RIGHT;
        else if(horizontal == -1)
            ret = LEFT;
        else if (vertical == -1)
            ret = DOWN;
        else if (vertical == 1)
            ret = UP;
        return ret;
    }

    /**
     * Check if two directions are opposite. <br>
     * 두 방향이 반대인지 확인한다.
     * @param dir1 direction 1
     * @param dir2 direction 2
     * @return true if direction 1 is pointing the opposite side of direction 2. return false in other cases. <br>
     * 두 Direction이 서로 반대 방향이면 true를, 그 외의 경우에는 false를 반환.
     */
    public static boolean isOpposite(Direction dir1, Direction dir2){
        return dir1.x * dir2.x == -1 || dir1.y * dir2.y == -1;
    }

    public static Direction getOpposite(Direction dir){
        return switch (dir){
            case DOWN -> UP;
            case UP -> DOWN;
            case LEFT -> RIGHT;
            case RIGHT -> LEFT;
            default -> null;
        };
    }
}
