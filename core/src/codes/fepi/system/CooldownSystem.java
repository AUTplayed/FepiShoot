package codes.fepi.system;

import codes.fepi.component.cooldown.BasicProjCooldownComponent;
import codes.fepi.component.cooldown.CooldownComponent;
import codes.fepi.component.cooldown.TrueShotCooldownComponent;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

public class CooldownSystem extends IteratingSystem {
    private ComponentMapper<BasicProjCooldownComponent> bpcm = ComponentMapper.getFor(BasicProjCooldownComponent.class);
    private ComponentMapper<TrueShotCooldownComponent> tscm = ComponentMapper.getFor(TrueShotCooldownComponent.class);

    public CooldownSystem() {
        super(Family.one(BasicProjCooldownComponent.class, TrueShotCooldownComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        processComp(bpcm.get(entity), deltaTime);
        processComp(tscm.get(entity), deltaTime);
    }

    private void processComp(CooldownComponent cd, float deltaTime) {
        if (cd != null) {
            cd.tel += deltaTime;
            if (cd.tel >= cd.time) {
                cd.allowed = true;
            }
        }
    }
}
