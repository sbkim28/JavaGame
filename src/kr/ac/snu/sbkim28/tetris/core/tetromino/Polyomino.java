package kr.ac.snu.sbkim28.tetris.core.tetromino;

import kr.ac.snu.sbkim28.core.Direction;
import kr.ac.snu.sbkim28.core.IntVector2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Polyomino implements IPolyomino {
    private IntVector2[] blocks;
    private int length;
    private IntVector2 pivot;
    private int size;
    private Polyomino() {pivot = new IntVector2();}

    protected Polyomino(IntVector2[] blocks, int size) {
        length = blocks.length;
        this.blocks = new IntVector2[length];

        for (int i = 0; i<length; ++i){
            if(blocks[i].x >= size || blocks[i].y >= size)
                throw new IllegalArgumentException("x, y out of bounds");
            this.blocks[i] = blocks[i];
        }
        this.size = size;
        pivot = new IntVector2();
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void move(Direction dir) {
        pivot = pivot.add(dir);
        for (int i = 0; i<length; ++i){
            blocks[i] = blocks[i].add(dir);
        }
    }

    @Override
    public void move(IntVector2 vector2) {
        pivot = pivot.add(vector2);
        for (int i = 0; i<length; ++i){
            blocks[i] = blocks[i].add(vector2);
        }
    }

    @Override
    public void rotate(Direction dir){
        if(dir != Direction.RIGHT && dir != Direction.LEFT){
            throw new IllegalArgumentException("Invalid Direction");
        }

        for (int i = 0; i<length; ++i) {
            int oriX = blocks[i].x - pivot.x;
            int oriY = blocks[i].y - pivot.y;

            if (dir == Direction.RIGHT) {
                blocks[i] = new IntVector2(size - 1 - oriY + pivot.x, oriX + pivot.y);
            } else {
                blocks[i] = new IntVector2(oriY + pivot.x, size - 1 - oriX + pivot.y);
            }
        }
    }

    @Override
    public Iterator<IntVector2> iterator() {
        return new Iterator<>() {
            private int cursor = 0;
            @Override
            public boolean hasNext() {
                return cursor < length;
            }

            @Override
            public IntVector2 next() {
                return blocks[cursor++];
            }
        };
    }

    @Override
    public String toString() {
        return "BasicPolyomino{" +
                "blocks=" + Arrays.toString(blocks) +
                ", length=" + length +
                '}';
    }

    public static class Builder{
        private final List<IntVector2> blocks;
        private int size;

        public Builder(){
            blocks = new ArrayList<>();
        }

        public Builder append(int x, int y){
            if(x < 0 || y < 0)
                throw new IllegalArgumentException("negative x, y");
            blocks.add(new IntVector2(x, y));
            return this;
        }

        public Builder setSize(int size) {
            this.size = size;
            return this;
        }

        public Polyomino build(){
            Polyomino p = new Polyomino();
            p.length = blocks.size();
            p.blocks = new IntVector2[blocks.size()];
            int i = 0;
            for(IntVector2 pos : blocks){
                p.blocks[i++] = pos;
            }
            if(size != 0) {
                p.size = size;
                for (IntVector2 pos : blocks){
                    if(size <= pos.x)
                        size = pos.x + 1;
                    if(size <= pos.y + 1)
                        size = pos.y;
                }
            } else {
                int max = 0;
                for (IntVector2 pos : blocks){
                    if(max < pos.x)
                        max = pos.x;
                    if(max < pos.y)
                        max = pos.y;
                }
                p.size = max + 1;
            }
            return p;
        }

        public Builder clear(){
            blocks.clear();
            return this;
        }
    }
}
