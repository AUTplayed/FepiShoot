package codes.fepi.lib;

import codes.fepi.component.*;
import codes.fepi.component.cooldown.BasicProjCooldownComponent;
import codes.fepi.component.cooldown.TrueShotCooldownComponent;
import codes.fepi.component.field.PositionComponent;
import codes.fepi.component.field.SizeComponent;
import codes.fepi.component.field.VelocityComponent;
import codes.fepi.player.Player;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;

public class GameVar {
    public static ComponentMapper<PositionComponent> posm = ComponentMapper.getFor(PositionComponent.class);
    public static ComponentMapper<VelocityComponent> velm = ComponentMapper.getFor(VelocityComponent.class);
    public static ComponentMapper<BasicProjCooldownComponent> bpcm = ComponentMapper.getFor(BasicProjCooldownComponent.class);
    public static ComponentMapper<HealthComponent> heam = ComponentMapper.getFor(HealthComponent.class);
    public static ComponentMapper<TrueShotCooldownComponent> tscm = ComponentMapper.getFor(TrueShotCooldownComponent.class);
    public static ComponentMapper<SizeComponent> sizm = ComponentMapper.getFor(SizeComponent.class);
    public static Pos playersize = new Pos();
    public static Pos basicProjSize = new Pos(16, 16);
    public static Pos trueshotSize = new Pos(32, 32);
    public static Engine engine;
    public static Pos healthbarSize = new Pos(200, 30);
    public static Player player;
    public static boolean gameover;
    public static boolean paused = false;
}
