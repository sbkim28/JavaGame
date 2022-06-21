package kr.ac.snu.sbkim28.gui;

import kr.ac.snu.sbkim28.core.GameCell;
import kr.ac.snu.sbkim28.core.GamePlate;
import kr.ac.snu.sbkim28.core.IState;

import java.awt.*;

/**
 * @author sbkim28
 * Class used to render {@link GamePlate} instance.
 * It implements {@link RenderComposite} interface.
 * <br>
 * PlateRenderer rendeers {@link GamePlate} by filling the background and filling the individual cells.
 * cells can have margins, the space between the adjacent cells.
 * <br>
 * {@link GamePlate} 객체를 렌더링하기 위해 사용하는 class.
 * {@link RenderComposite} 인터페이스를 implements 한다.
 * <br>
 * PlateRenderer은 전체 배경을 채우고 각각의 cell을 채움으로써 {@link GamePlate}을 렌더링한다.
 * Cell은 인접한 셀 사이의 간격인 margin을 가질 수 있다.
 */
public class PlateRenderer implements RenderComposite{

    private final GamePlate<?> plate;
    private final int cellcntX;
    private final int cellcntY;
    private int cellWidth;
    private int cellHeight;
    private int paddingX;
    private int paddingY;
    private int sizeX;
    private int sizeY;
    private Color background;

    private ColorAdapter adapter;

    /**
     * Constructor. <br>
     * Set background color to default value black.
     * Set {@link ColorAdapter} to default value black. <br>
     * 배경색을 기본값 검정으로, {@link ColorAdapter}을 기본값 검정으로 설정한다.
     * @param plate GamePlate. Not null.
     */
    public PlateRenderer(GamePlate<?> plate) {
        this(plate, new Color(0, 0,0), stateValue -> new Color(0, 0, 0));
    }

    /**
     * Constructor
     * @param plate GamePlate. Not null
     * @param background the color of background. Not null.
     * @param adapter Not null.
     */
    public PlateRenderer(GamePlate<?> plate, Color background, ColorAdapter adapter) {
        if(plate == null)
            throw new IllegalArgumentException("Null value: plate");
        if(background == null)
            throw new IllegalArgumentException("Null value: background");
        if(adapter == null)
            throw new IllegalArgumentException("Null value: adapter");

        this.plate = plate;
        cellcntX = plate.sizeX;
        cellcntY = plate.sizeY;
        this.background = background;
        this.adapter = adapter;
    }

    /**
     * returns the total size x renderer accounts for. <br>
     * 렌더러가 차지할 총 크기의 x 값을 반환한다.
     */
    public int getSizeX() {
        return sizeX;
    }

    /**
     * returns the total size y renderer accounts for. <br>
     * 렌더러가 차지할 총 크기의 y 값을 반환한다.
     */
    public int getSizeY() {
        return sizeY;
    }

    /**
     * calculate the total size renderer accounts for.
     */
    public void adjustSize(){
        sizeX = cellWidth * (cellcntX) + paddingX * (cellcntX + 1);
        sizeY = cellHeight * (cellcntY) + paddingY * (cellcntY + 1);
    }

    /**
     * Renders the plates.
     * @param g Graphics
     */
    @Override
    public void render(Graphics g) {
        int index = 0, indexX, indexY;
        g.setColor(background);
        g.fillRect(0, 0, sizeX, sizeY);

        for(GameCell<?> cell : plate){
            indexX = cell.x;
            indexY = cell.y;
            g.setColor(adapter.getColorOfCell(cell.getState()));

            g.fillRect(paddingX * (indexX + 1) + cellWidth * indexX, paddingY * (indexY + 1) + cellHeight * indexY,
                    cellWidth, cellHeight);

            ++index;
        }
    }

    /**
     * calculate the total size renderer accounts for.
     */
    public void setCellSize(int cellWidth, int cellHeight){
        this.cellWidth = cellWidth;
        this.cellHeight = cellHeight;
        adjustSize();
    }

    public void setPadding(int paddingX, int paddingY){
        this.paddingX = paddingX;
        this.paddingY = paddingY;
        adjustSize();
    }


    public void setBackground(Color background) {
        if (background == null)
            throw new IllegalArgumentException("Null value");
        this.background = background;
    }

    public void setAdapter(ColorAdapter adapter) {
        if(adapter == null)
            throw new IllegalArgumentException("Null value");
        this.adapter = adapter;
    }
}
