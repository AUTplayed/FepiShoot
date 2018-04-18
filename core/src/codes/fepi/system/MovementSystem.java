package codes.fepi.system;

import codes.fepi.component.field.PositionComponent;
import codes.fepi.component.field.VelocityComponent;
import codes.fepi.lib.GameVar;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;

public class MovementSystem extends IteratingSystem {
    private ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);
    private ComponentMapper<VelocityComponent> vm = ComponentMapper.getFor(VelocityComponent.class);

    public MovementSystem() {
        super(Family.all(PositionComponent.class, VelocityComponent.class).get());
    }

    public void processEntity(Entity entity, float deltaTime) {
        PositionComponent position = pm.get(entity);
        VelocityComponent velocity = vm.get(entity);
        //System.out.println(position.x + "/" + position.y);
        position.x += velocity.x * deltaTime;
        position.y += velocity.y * deltaTime;
        if(position.x < 0 || position.x > Gdx.graphics.getWidth() ||
                position.y < 0 || position.y > Gdx.graphics.getHeight()) {
            GameVar.engine.removeEntity(entity);
        }

    }
}
