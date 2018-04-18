package codes.fepi.component;

import com.badlogic.ashley.core.Component;

public class PenetrateComponent implements Component {
    public int amount = 1;

    public PenetrateComponent() {
    }

    public PenetrateComponent(int amount) {
        this.amount = amount;
    }
}
