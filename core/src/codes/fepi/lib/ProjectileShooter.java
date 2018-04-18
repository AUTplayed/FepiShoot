package codes.fepi.lib;

import codes.fepi.component.field.PositionComponent;
import codes.fepi.component.field.SizeComponent;
import codes.fepi.entity.BasicProjectile;
import codes.fepi.entity.Projectile;
import codes.fepi.entity.TrueShotProjectile;
import com.badlogic.ashley.core.Entity;


public class ProjectileShooter {
    public static void shootBasic(Entity entity, float velocity, int damage, int amount) {
        shootProj(entity, amount, GameVar.basicProjSize.x, () -> new BasicProjectile(damage, velocity));
    }

    public static void shootTrueshot(Entity entity, float velocity, int damage, int amount) {
        shootProj(entity, amount, GameVar.trueshotSize.x, () -> new TrueShotProjectile(damage, velocity));
    }

    private static void shootProj(Entity entity, int amount, float sizex, ProjectileConst proj) {
        PositionComponent pos = GameVar.posm.get(entity);
        SizeComponent size = GameVar.sizm.get(entity);
        float widthOffset = (sizex * amount) / 2;
        float basex = (pos.x + (size.x / 2));
        for (int i = 0; i < amount; i++) {
            createProjectile((basex - widthOffset) + i * sizex, pos.y, proj.constr());
        }
    }

    private static void createProjectile(float x, float y, Projectile projectile) {
        PositionComponent pos = GameVar.posm.get(projectile);
        pos.x = x;
        pos.y = y;
        GameVar.engine.addEntity(projectile);
    }

    private interface ProjectileConst {
        Projectile constr();
    }

}
