package codes.fepi.entity;

import codes.fepi.lib.GameVar;

public class TrueShotProjectile extends Projectile {
    public TrueShotProjectile(int damage, float velocity) {
        super(damage, GameVar.trueshotSize.x, GameVar.trueshotSize.y, 8);
        GameVar.velm.get(this).y = velocity;
    }
}