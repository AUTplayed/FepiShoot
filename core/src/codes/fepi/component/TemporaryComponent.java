package codes.fepi.component;

import com.badlogic.ashley.core.Component;

public class TemporaryComponent implements Component {
    public float time;
    public TemporaryComponent(float time) {
        this.time = time;
    }
}
