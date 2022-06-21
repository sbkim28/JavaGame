package kr.ac.snu.sbkim28.gui;

import java.awt.*;
import java.util.LinkedList;

/**
 * @author sbkim28
 * RenderCompositeCollection is a class for grouping instance {@link RenderComposite}.
 * The RenderCompositeCollection class extends {@link LinkedList},
 * and can hold {@link RenderComposite} instance.
 * The class itself also implements the {@link RenderComposite}
 * and implements the {@link RenderComposite#render(Graphics) render} method. <br>
 * RenderCompositeCollection은 {@link RenderComposite} 객체를 묶기 위해서 사용되는 클래스이다.
 * RenderCompositeCollection은 {@link LinkedList}를 상속받고 있으며
 * {@link RenderComposite}객체를 담을 수 있다.
 * RenderCompositeCollection은 또한 그 자체로 {@link RenderComposite}를 implements하여
 * {@link RenderComposite#render(Graphics) render} method를 구현하고 있다.
 */
public class RenderCompositeCollection extends LinkedList<RenderComposite> implements RenderComposite{

    /**
     * Renders every instance inside this instance.
     * It calls {@link RenderComposite#render(Graphics) render} methods of its elements. <br>
     * 이 객체 안의 모든 객체를 렌더링한다.
     * 이 객체의 모든 원소의 {@link RenderComposite#render(Graphics) render} method를 호출한다.
     * @param g Graphics
     */
    @Override
    public void render(Graphics g) {
        for (RenderComposite composite:
             this) {
            composite.render(g);
        }
    }
}
