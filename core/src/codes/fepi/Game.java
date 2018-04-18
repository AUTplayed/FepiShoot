package codes.fepi;

import codes.fepi.component.field.PositionComponent;
import codes.fepi.component.marker.PlayerComponent;
import codes.fepi.lib.EntityType;
import codes.fepi.lib.GameVar;
import codes.fepi.lib.Pos;
import codes.fepi.manager.GameStateManager;
import codes.fepi.manager.TextureManager;
import codes.fepi.manager.DrawManager;
import codes.fepi.manager.SpawnManager;
import codes.fepi.player.Player;
import codes.fepi.system.*;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;

public class Game extends ApplicationAdapter {


    @Override
    public void create() {
        Texture playerTex = TextureManager.getInstance().get(EntityType.player, "spaceship1.png");
        GameVar.playersize.y = playerTex.getHeight();
        GameVar.playersize.x = playerTex.getWidth();
        GameVar.engine = new Engine();
        GameStateManager.getInstance().restart();
        setupSystems();
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                if (!GameVar.paused) {
                    GameVar.player.startShoot(button);
                } else {
                    Pos pausePos = GameStateManager.getInstance().getPausePos();
                    if (screenX > pausePos.x && screenX < pausePos.x + 200 && screenY < pausePos.y && screenY > pausePos.y - 60) {
                        GameStateManager.getInstance().restart();
                    }
                }
                return true;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                if (!GameVar.paused) GameVar.player.stopShoot(button);
                return true;
            }

            @Override
            public boolean keyDown(int keycode) {
                if (keycode == Input.Keys.ESCAPE) {
                    GameVar.paused = !GameVar.paused;
                    if (GameVar.paused) {
                        GameVar.player.stopShoot(-1);
                        GameStateManager.getInstance().stopSystems();
                    } else {
                        GameStateManager.getInstance().restartSystems();
                    }
                }
                return true;
            }
        });
        GameVar.engine.addEntityListener(Family.all(PlayerComponent.class).get(), new EntityListener() {
            @Override
            public void entityAdded(Entity entity) {
            }

            @Override
            public void entityRemoved(Entity entity) {
                GameVar.gameover = true;
                GameVar.engine.getSystem(CollisionSystem.class).setProcessing(false);
            }
        });
    }

    @Override
    public void render() {
        // Input
        PositionComponent posPlayer = GameVar.posm.get(GameVar.player);
        if (!GameVar.paused) {
            posPlayer.x = Gdx.input.getX() - (GameVar.playersize.x / 2);
            posPlayer.y = Gdx.input.getY() + (GameVar.playersize.y / 2);
        }
        if (Gdx.input.isTouched()) {
            GameVar.player.shoot();
        }
        // Spawning
        SpawnManager.getInstance().update();
        // System Ticks
        GameVar.engine.update(Gdx.graphics.getDeltaTime());
        // Drawing
        DrawManager.getInstance().draw();
    }


    @Override
    public void dispose() {
        DrawManager.getInstance().dispose();
    }

    private void setupSystems() {
        MovementSystem movementSystem = new MovementSystem();
        FunctionMoveSystem functionMoveSystem = new FunctionMoveSystem();
        CooldownSystem cooldownSystem = new CooldownSystem();
        ProjectileCollisionSystem projectileCollisionSystem = new ProjectileCollisionSystem();
        DecaySystem decaySystem = new DecaySystem();
        CollisionSystem collisionSystem = new CollisionSystem();
        EnemyAttackSystem enemyAttackSystem = new EnemyAttackSystem();
        GameVar.engine.addSystem(movementSystem);
        GameVar.engine.addSystem(functionMoveSystem);
        GameVar.engine.addSystem(cooldownSystem);
        GameVar.engine.addSystem(projectileCollisionSystem);
        GameVar.engine.addSystem(decaySystem);
        GameVar.engine.addSystem(collisionSystem);
        GameVar.engine.addSystem(enemyAttackSystem);
    }
}
