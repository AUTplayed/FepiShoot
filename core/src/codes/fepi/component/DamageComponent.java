package codes.fepi.component;

import com.badlogic.ashley.core.Component;

public class DamageComponent implements Component {
    public int damage = 0;

    public DamageComponent(int damage) {
        this.damage = damage;
    }
}
