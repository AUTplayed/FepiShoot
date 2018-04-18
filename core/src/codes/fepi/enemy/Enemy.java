package codes.fepi.enemy;

import codes.fepi.component.cooldown.BasicProjCooldownComponent;
import codes.fepi.component.cooldown.TrueShotCooldownComponent;
import codes.fepi.component.marker.EnemyComponent;
import codes.fepi.component.HealthComponent;
import codes.fepi.component.field.PositionComponent;
import codes.fepi.component.field.SizeComponent;
import codes.fepi.lib.EnemyType;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;

public class Enemy extends Entity {
    public String sprite;

    public Enemy(int health, EnemyType type) {
        this.sprite = type.getProps().sprite;
        PositionComponent pos = new PositionComponent();
        pos.y = 0;
        pos.x = (float) (Math.random() * Gdx.graphics.getWidth());
        this.add(pos);
        this.add(new HealthComponent(health, this));
        this.add(new EnemyComponent());
        this.add(new SizeComponent(type.getProps().x, type.getProps().y));

        switch (type) {
            case Advanced: {
                this.add(new BasicProjCooldownComponent(2, 1));
                break;
            }
            case TrueShot: {
                this.add(new TrueShotCooldownComponent(4, 1));
                break;
            }
            case DoubleShotMove: {
                this.add(new BasicProjCooldownComponent(2, 2));
                break;
            }
            case DoubleShot: {
                this.add(new BasicProjCooldownComponent(1.5f, 2));
            }
        }
    }
}