package kr.ac.snu.sbkim28.game;

import kr.ac.snu.sbkim28.gui.game.GameGUI;
import kr.ac.snu.sbkim28.gui.RenderCompositeCollection;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author sbkim28
 * GameManager class accounts for handling the basic process of game.
 * It implements {@link Runnable} interface and {@link GameManager#run} methods where the basic process of game is calculated and executed.
 * GameManager class holds some time related data so that other classes can easily find the time data related to the game. <br>
 * GameManager 클래스는 게임의 기본 과정을 관리하는 것을 담당한다.
 * GameManager 클래스는 {@link Runnable} interface와 {@link GameManager#run} method를 implements 하였다.
 * {@link GameManager#run}에서 게임의 기본적인 과정이 실행되고 계산된다.
 * GameManager 클래스는 게임의 시간과 관련된 데이터를 담고 있다. 다른 클래스는 이 클래스를 통해 게임의 시간과 관련된 데이터를 쉽게 얻을 수 있다.
 */
public class GameManager implements Runnable{

    /**
     * it means whether game is running.
     * true when game is running, false when game is not started or finished. <br>
     * 게임이 실행 중인지를 나타내는 변수. 게임이 실행 중이면 true, 게임이 시작되지 않았거나 종료되었으면 false.
     */
    private boolean run;
    /**
     * variable used to calculate the elapsedTime <br>
     * 경과한 시간을 계산하기 위해 사용되는 변수
     */
    private long prevFrame;
    /**
     * duration time.
     * Milliseconds.
     * <br>
     * 대기 시간.
     * 밀리초.
     */
    private long durationTime;

    /**
     * elapsed time between two frames.
     * Milliseconds. <br>
     * 프레임 사이 경과한 시간. 밀리초.
     */
    private long elapsedTime;

    /**
     * how many frames have been passed. <br>
     * 지금까지의 총 프레임 수
     */
    private long tick;

    /**
     * instance that holds the input of users.
     * GameManager handles and control it so when we want to use the input of users,
     * we can use the one in GameManager. <br>
     * 유저의 입력을 담는 객체.
     * GameManager 객체가 keyHolder 객체를 관리하고 조절해준다.
     * 유저의 입력을 사용하고 싶을 때, GameManager 객체의 것을 사용할 수 있다.
     */
    public final InputHolder keyHolder;
    /**
     * Instance that can change the value of {@link GameManager#keyHolder}. <br>
     * {@link GameManager#keyHolder}의 값을 변경하는 Instance
     */
    private final InputHolder.Setter setter;

    /**
     * GUI instance of this game. <br>
     * 이 게임의 GUI 객체.
     */
    private final GameGUI gui;

    /**
     * Collection of all objects to be rendered.
     * By adding {@link kr.ac.snu.sbkim28.gui.RenderComposite RenderComposite}
     * instance to this field,
     * you can render game object to gui.
     * <br>
     * 렌더링되어야 하는 모든 객체의 Collection.
     * {@link kr.ac.snu.sbkim28.gui.RenderComposite RenderComposite}를
     * 이 멤버 변수에 추가함으로써 게임 오브젝트를 gui에 렌더링할 수 있다.
     */
    protected RenderCompositeCollection renderBody;

    /**
     * Set {@link GameManager#durationTime} to default 40.
     * Add keyEventListeners and MouseEventListeners to {@link GameManager#gui}.
     * <br>
     * {@link GameManager#durationTime}을 기본 40으로 설정한다.
     * 키와 마우스 eventlistener을 {@link GameManager#gui}에 추가한다.
     * @param gui gui of this game
     */
    public GameManager(GameGUI gui) {
        this.gui = gui;
        run = false;
        durationTime = 40;
        keyHolder = new InputHolder();
        setter = keyHolder.new Setter();
        renderBody = new RenderCompositeCollection();
        bindEventListeners();
    }

    private void bindEventListeners(){
        gui.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                setter.keyDown(e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                setter.keyUp(e.getKeyCode());
            }
        });
        gui.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setter.mouseDown();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                setter.mouseUp();
            }
        });
    }

    /**
     * Execute the game.
     * It calls {@link GameManager#initialize()} before starting once.
     * After start, it repeatedly calls {@link GameManager#update()}
     * and {@link GameGUI#render()} of {@link GameManager#gui}.
     * The interval of updating and rendering is determined by {@link GameManager#durationTime}.
     * If {@link GameManager#terminate()} is called, the game halts.
     * <br>
     * 게임을 실행한다.
     * {@link GameManager#initialize()}를 시작 전에 호출한다.
     * 시작 후, {@link GameManager#update()}와 {@link GameManager#gui}의
     * {@link GameGUI#render()}를 반복해서 호출한다. 이때 반복해서 호출하기까지의
     * 시간 간격은 {@link GameManager#durationTime}에 의해서 결정된다.
     * {@link GameManager#terminate()}가 호출되면 게임을 종료한다.
     */
    @Override
    public final void run() {
        run = true;
        prevFrame = Integer.MIN_VALUE;
        initialize();

        long time;
        while (run){
            time = System.currentTimeMillis();
            elapsedTime = time - prevFrame;
            if(elapsedTime >= durationTime){
                prevFrame = time;
                ++tick;
                update();
                setter.flash();
                gui.render();
            }
        }
    }

    /**
     * Initialize game.
     * make ready for gui to render game objects.
     * <br>
     * 게임을 초기화한다.
     * gui에서 게임 오브젝트를 렌더링할 수 있도록 준비한다.
     */
    public void initialize(){
        gui.setRenderComposite(renderBody);
    }

    /**
     * Method called every single frame of the game. <br>
     * 게임의 모든 프레임마다 호출되는 method.
     */
    public void update(){

    }

    /**
     * Getter of {@link GameManager#durationTime}
     * @return {@link GameManager#durationTime}
     */
    public long getDurationTime() {
        return durationTime;
    }

    /**
     * Getter of {@link GameManager#elapsedTime}
     * @return {@link GameManager#elapsedTime}
     */
    public long getElapsedTime() {
        return elapsedTime;
    }

    /**
     * Getter of {@link GameManager#tick}
     * @return {@link GameManager#tick}
     */
    public long getTick() {
        return tick;
    }

    /**
     * Terminate the running game. <br>
     * 실행 중인 게임을 종료한다.
     */
    public void terminate(){
        run = false;
    }
}
