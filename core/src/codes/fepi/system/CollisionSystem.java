package codes.fepi.system;

import codes.fepi.component.field.CollisionComponent;
import codes.fepi.component.HealthComponent;
import codes.fepi.component.field.PositionComponent;
import codes.fepi.component.field.SizeComponent;
import codes.fepi.lib.GameVar;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

public class CollisionSystem extends IteratingSystem {
    private ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);
    private ComponentMapper<SizeComponent> sm = ComponentMapper.getFor(SizeComponent.class);
    private ComponentMapper<HealthComponent> hm = ComponentMapper.getFor(HealthComponent.class);
    private ComponentMapper<CollisionComponent> cm = ComponentMapper.getFor(CollisionComponent.class);

    public CollisionSystem() {
        super(Family.all(CollisionComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent playerPos = pm.get(GameVar.player);
        PositionComponent enemyPos = pm.get(entity);
        SizeComponent enemySize = sm.get(entity);
        SizeComponent playerSize = sm.get(GameVar.player);
        if (enemyPos.x <= (playerPos.x + playerSize.x) && (enemyPos.x + enemySize.x) >= playerPos.x &&
                enemyPos.y <= (playerPos.y + playerSize.y) && (enemyPos.y + enemySize.y) >= playerPos.y) {
            hm.get(entity).reduceHealth(10);
            hm.get(GameVar.player).reduceHealth(cm.get(entity).damage);
        }
    }
}
