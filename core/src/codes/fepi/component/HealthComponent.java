package codes.fepi.component;

import codes.fepi.component.field.PositionComponent;
import codes.fepi.entity.Explosion;
import codes.fepi.lib.GameVar;
import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

public class HealthComponent implements Component {
    private int health;
    public int maxhealth;
    private Entity parent;

    public HealthComponent(int health, Entity parent) {
        this.maxhealth = health;
        this.health = maxhealth;
        this.parent = parent;
    }

    public void reduceHealth(int health) {
        this.health -= health;
        if (this.health <= 0) {
            GameVar.engine.removeEntity(parent);
            PositionComponent pos = GameVar.posm.get(parent);
            Explosion explosion = new Explosion(pos.x, pos.y, 2);
            GameVar.engine.addEntity(explosion);
        }
    }

    public int getHealth() {
        return health;
    }
}
