package codes.fepi.component.field;

import com.badlogic.ashley.core.Component;

public class VelocityComponent implements Component {
    public float x = 0;
    public float y = 0;

    public VelocityComponent() {
    }

    public VelocityComponent(float y) {
        this.y = y;
    }
}
