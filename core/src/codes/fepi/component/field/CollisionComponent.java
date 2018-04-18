package codes.fepi.component.field;

import com.badlogic.ashley.core.Component;

public class CollisionComponent implements Component {
    public int damage = 1;

    public CollisionComponent(int damage) {
        this.damage = damage;
    }
}
