package kr.ac.snu.sbkim28.core;

public class IntVector2 {
    public final int x;
    public final int y;

    public IntVector2() {
        this(0, 0);
    }
    public IntVector2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public IntVector2 add(IntVector2 vector){
        return new IntVector2(x + vector.x, y + vector.y);
    }

    public IntVector2 add(Direction vector){
        return new IntVector2(x + vector.x, y + vector.y);
    }

    public IntVector2 mul(int scalar){
        return new IntVector2(x * scalar, y * scalar);
    }
}
