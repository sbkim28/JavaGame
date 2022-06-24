package kr.ac.snu.sbkim28.tetris.core;

import kr.ac.snu.sbkim28.tetris.core.TetrisState;
import kr.ac.snu.sbkim28.tetris.core.tetromino.IPolyomino;
import kr.ac.snu.sbkim28.tetris.core.tetromino.IPolyominoFactory;
import kr.ac.snu.sbkim28.tetris.core.tetromino.Polyomino;

public enum TetrominoFactory implements IPolyominoFactory {
    L (TetrisState.L){
        private final Polyomino.Builder builder
                = new Polyomino.Builder()
                .append(0, 1).append(1, 1)
                .append(2, 1).append(2, 0);

        @Override
        public Polyomino createPolyomino() {
            return builder.build();
        }
    },
    J (TetrisState.J){
        private final Polyomino.Builder builder
                = new Polyomino.Builder()
                .append(0, 0).append(0, 1)
                .append(1, 1).append(2, 1);

        @Override
        public Polyomino createPolyomino() {
            return builder.build();
        }
    },
    S (TetrisState.S){
        private final Polyomino.Builder builder
                = new Polyomino.Builder()
                .append(0, 1).append(1, 1)
                .append(1, 0).append(2, 0);

        @Override
        public Polyomino createPolyomino() {
            return builder.build();
        }
    },
    Z (TetrisState.Z) {
        private final Polyomino.Builder builder
                = new Polyomino.Builder()
                .append(0, 0).append(1, 0)
                .append(1, 1).append(2, 1);

        @Override
        public Polyomino createPolyomino() {
            return builder.build();
        }
    },
    T (TetrisState.T){
        private final Polyomino.Builder builder
                = new Polyomino.Builder()
                .append(0, 1).append(1, 1)
                .append(1, 0).append(2, 1);

        @Override
        public Polyomino createPolyomino() {
            return builder.build();
        }
    },
    I (TetrisState.I){
        private final Polyomino.Builder builder
                = new Polyomino.Builder()
                .append(0, 1).append(1, 1)
                .append(2, 1).append(3, 1);

        @Override
        public Polyomino createPolyomino() {
            return builder.build();
        }
    },
    O (TetrisState.O){
        private final Polyomino.Builder builder
                = new Polyomino.Builder()
                .append(0, 0).append(0, 1)
                .append(1, 0).append(1, 1);

        @Override
        public Polyomino createPolyomino() {
            return builder.build();
        }
    };

    public final TetrisState state;

    TetrominoFactory(TetrisState state) {
        this.state = state;
    }
}
