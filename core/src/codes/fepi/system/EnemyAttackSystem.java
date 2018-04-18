package codes.fepi.system;

import codes.fepi.component.cooldown.BasicProjCooldownComponent;
import codes.fepi.component.cooldown.CooldownComponent;
import codes.fepi.component.cooldown.TrueShotCooldownComponent;
import codes.fepi.component.field.PositionComponent;
import codes.fepi.component.field.SizeComponent;
import codes.fepi.component.marker.EnemyComponent;
import codes.fepi.enemy.Enemy;
import codes.fepi.entity.BasicProjectile;
import codes.fepi.entity.Projectile;
import codes.fepi.entity.TrueShotProjectile;
import codes.fepi.lib.GameVar;
import codes.fepi.lib.ProjectileShooter;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

public class EnemyAttackSystem extends IteratingSystem {
    private ComponentMapper<BasicProjCooldownComponent> bpcm = ComponentMapper.getFor(BasicProjCooldownComponent.class);
    private ComponentMapper<TrueShotCooldownComponent> tscm = ComponentMapper.getFor(TrueShotCooldownComponent.class);
    private ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);
    private ComponentMapper<SizeComponent> sm = ComponentMapper.getFor(SizeComponent.class);

    public EnemyAttackSystem() {
        super(Family.all(EnemyComponent.class).one(BasicProjCooldownComponent.class, TrueShotCooldownComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        BasicProjCooldownComponent bpcd = bpcm.get(entity);
        if (bpcd != null && bpcd.allowed) {
            bpcd.reset();
            ProjectileShooter.shootBasic(entity, 500, 1, bpcd.amount);
        }
        TrueShotCooldownComponent tscd = tscm.get(entity);
        if (tscd != null && tscd.allowed) {
            tscd.reset();
            ProjectileShooter.shootTrueshot(entity, 1000, 2, tscd.amount);
        }
    }
}
