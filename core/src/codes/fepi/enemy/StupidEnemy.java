package codes.fepi.enemy;

import codes.fepi.component.field.CollisionComponent;
import codes.fepi.component.field.VelocityComponent;
import codes.fepi.lib.EnemyType;

public class StupidEnemy extends Enemy {
    public StupidEnemy(int health, EnemyType type, float speed) {
        super(health, type);
        this.add(new VelocityComponent(speed));
        this.add(new CollisionComponent(1));
    }
}