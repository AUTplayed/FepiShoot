package codes.fepi.player;

import codes.fepi.component.HealthComponent;
import codes.fepi.component.cooldown.BasicProjCooldownComponent;
import codes.fepi.component.cooldown.TrueShotCooldownComponent;
import codes.fepi.component.field.PositionComponent;
import codes.fepi.component.field.SizeComponent;
import codes.fepi.component.marker.PlayerComponent;
import codes.fepi.entity.BasicProjectile;
import codes.fepi.entity.Projectile;
import codes.fepi.entity.TrueShotProjectile;
import codes.fepi.lib.GameVar;
import codes.fepi.lib.ProjectileShooter;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Input;

public class Player extends Entity {
    public Player() {
        this.add(new PositionComponent());
        this.add(new BasicProjCooldownComponent(0.2f, 1));
        this.add(new TrueShotCooldownComponent(1f, 1));
        this.add(new HealthComponent(20, this));
        this.add(new SizeComponent(GameVar.playersize.x, GameVar.playersize.y));
        this.add(new PlayerComponent());
    }

    private boolean leftBtn, rightBtn;

    public void startShoot(int key) {
        if (Input.Buttons.LEFT == key) {
            leftBtn = true;
        }
        if (Input.Buttons.RIGHT == key) {
            rightBtn = true;
        }
    }

    public void stopShoot(int key) {
        if (key == -1) {
            leftBtn = rightBtn = false;
        }
        if (Input.Buttons.LEFT == key) {
            leftBtn = false;
        }
        if (Input.Buttons.RIGHT == key) {
            rightBtn = false;
        }
    }

    public void shoot() {
        if (!GameVar.gameover) {
            if (leftBtn) shootBasicProj();
            if (rightBtn) {
                shootAdvancedProj();
            }
        }
    }

    private void shootAdvancedProj() {
        TrueShotCooldownComponent tscd = GameVar.tscm.get(this);
        if (tscd.allowed) {
            tscd.reset();
            ProjectileShooter.shootTrueshot(this, -1000, 2, tscd.amount);
        }
    }

    private void shootBasicProj() {
        BasicProjCooldownComponent bpcd = GameVar.bpcm.get(this);
        if (bpcd.allowed) {
            bpcd.reset();
            ProjectileShooter.shootBasic(this, -500, 1, bpcd.amount);
        }
    }
}
