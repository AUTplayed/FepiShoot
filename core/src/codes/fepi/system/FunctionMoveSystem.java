package codes.fepi.system;

import codes.fepi.component.FunctionMoveComponent;
import codes.fepi.component.field.PositionComponent;
import codes.fepi.lib.GameVar;
import codes.fepi.lib.Pos;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;

public class FunctionMoveSystem extends IteratingSystem {
    private ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);
    private ComponentMapper<FunctionMoveComponent> fm = ComponentMapper.getFor(FunctionMoveComponent.class);

    public FunctionMoveSystem() {
        super(Family.all(FunctionMoveComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent pos = pm.get(entity);
        FunctionMoveComponent move = fm.get(entity);
        move.time += deltaTime;
        Pos newpos = move.function.eval(move.time);
        pos.x = move.offset + newpos.x;
        pos.y = newpos.y;
        if(pos.y > Gdx.graphics.getHeight()) {
            GameVar.engine.removeEntity(entity);
        }
    }
}
