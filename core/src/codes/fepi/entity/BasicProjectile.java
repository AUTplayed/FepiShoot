package codes.fepi.entity;

import codes.fepi.lib.GameVar;

public class BasicProjectile extends Projectile {

    public BasicProjectile(int damage, float velocity) {
        super(damage, GameVar.basicProjSize.x, GameVar.basicProjSize.y, 1);
        GameVar.velm.get(this).y = velocity;
    }
}
