package codes.fepi.system;

import codes.fepi.component.TemporaryComponent;
import codes.fepi.lib.GameVar;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

public class DecaySystem extends IteratingSystem {
    private ComponentMapper<TemporaryComponent> tcm = ComponentMapper.getFor(TemporaryComponent.class);

    public DecaySystem() {
        super(Family.all(TemporaryComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TemporaryComponent temporaryComponent = tcm.get(entity);
        temporaryComponent.time -= deltaTime;
        if (temporaryComponent.time <= 0) {
            GameVar.engine.removeEntity(entity);
        }
    }
}
