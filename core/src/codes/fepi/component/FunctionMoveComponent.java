package codes.fepi.component;

import codes.fepi.lib.MoveFunction;
import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.Gdx;

public class FunctionMoveComponent implements Component {
    public MoveFunction function;
    public float time = 0;
    public float offset = 0;

    public FunctionMoveComponent(MoveFunction function, float offset) {
        this.function = function;
        this.offset = offset;
    }

    public FunctionMoveComponent(MoveFunction function) {
        this.function = function;
        this.offset = (float) (Math.random() * Gdx.graphics.getWidth());
    }
}
