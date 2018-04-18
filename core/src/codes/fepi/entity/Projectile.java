package codes.fepi.entity;

import codes.fepi.component.*;
import codes.fepi.component.field.PositionComponent;
import codes.fepi.component.field.SizeComponent;
import codes.fepi.component.field.VelocityComponent;
import com.badlogic.ashley.core.Entity;

public class Projectile extends Entity {
    public Projectile(int damage, float width, float height, int penetrate) {
        this.add(new PositionComponent());
        this.add(new VelocityComponent());
        this.add(new DamageComponent(damage));
        this.add(new SizeComponent(width, height));
        this.add(new PenetrateComponent(penetrate));
    }
}
