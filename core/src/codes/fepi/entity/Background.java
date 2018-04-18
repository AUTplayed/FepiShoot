package codes.fepi.entity;

import codes.fepi.component.field.PositionComponent;
import codes.fepi.component.field.VelocityComponent;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;

public class Background extends Entity {
    public Background() {
        this.add(new VelocityComponent(40));
        this.add(new PositionComponent((float) (Math.random() * Gdx.graphics.getWidth()), 0));
    }
}
