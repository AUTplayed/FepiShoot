package codes.fepi.component.field;

import com.badlogic.ashley.core.Component;

public class SizeComponent implements Component {
    public float x,y;

    public SizeComponent(float x,float y) {
        this.x = x;
        this.y = y;
    }
}
