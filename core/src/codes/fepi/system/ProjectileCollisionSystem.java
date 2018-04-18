package codes.fepi.system;

import codes.fepi.component.*;
import codes.fepi.component.field.PositionComponent;
import codes.fepi.component.field.SizeComponent;
import codes.fepi.component.field.VelocityComponent;
import codes.fepi.component.marker.PlayerComponent;
import codes.fepi.enemy.Enemy;
import codes.fepi.lib.GameVar;
import codes.fepi.component.marker.EnemyComponent;
import codes.fepi.player.Player;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;

public class ProjectileCollisionSystem extends IteratingSystem {
    private ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);
    private ComponentMapper<DamageComponent> dm = ComponentMapper.getFor(DamageComponent.class);
    private ComponentMapper<HealthComponent> hm = ComponentMapper.getFor(HealthComponent.class);
    private ComponentMapper<SizeComponent> sm = ComponentMapper.getFor(SizeComponent.class);
    private ComponentMapper<PenetrateComponent> pem = ComponentMapper.getFor(PenetrateComponent.class);
    private ComponentMapper<VelocityComponent> vm = ComponentMapper.getFor(VelocityComponent.class);

    public ProjectileCollisionSystem() {
        super(Family.all(DamageComponent.class, PositionComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent pos = pm.get(entity);
        SizeComponent size = sm.get(entity);
        ImmutableArray<Entity> enemies = GameVar.engine.getEntitiesFor(Family.one(EnemyComponent.class, PlayerComponent.class).get());
        enemies.forEach(enemy -> {
            PositionComponent enemyPos = pm.get(enemy);
            SizeComponent enemySize = sm.get(enemy);
            if (enemyPos.x <= (pos.x + size.x) && (enemyPos.x + enemySize.x) >= pos.x &&
                    enemyPos.y <= (pos.y + size.y) && (enemyPos.y + enemySize.y) >= pos.y) {
                float vel = vm.get(entity).y;
                if ((!(enemy instanceof Player) && vel < 0) || enemy instanceof Player && vel > 0) {
                    hm.get(enemy).reduceHealth(dm.get(entity).damage);
                    PenetrateComponent penetrate = pem.get(entity);
                    penetrate.amount--;
                    if (penetrate.amount <= 0) {
                        GameVar.engine.removeEntity(entity);
                    }
                }
            }
        });
    }
}
