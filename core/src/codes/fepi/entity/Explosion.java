package codes.fepi.entity;

import codes.fepi.component.field.PositionComponent;
import codes.fepi.component.TemporaryComponent;
import com.badlogic.ashley.core.Entity;

public class Explosion extends Entity {
    public Explosion(float x, float y, float time) {
        this.add(new PositionComponent(x, y));
        this.add(new TemporaryComponent(time));
    }
}
