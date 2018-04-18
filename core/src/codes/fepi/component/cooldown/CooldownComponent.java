package codes.fepi.component.cooldown;

import com.badlogic.ashley.core.Component;

public class CooldownComponent implements Component {
    public float time;
    public float tel;
    public int amount;

    public void reset() {
        tel = 0;
        allowed = false;
    }

    public CooldownComponent(float time, int amount) {
        this.time = time;
        this.tel = (float) (Math.random() * time);
        this.amount = amount;
    }

    public boolean allowed = false;
}
