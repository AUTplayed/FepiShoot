package codes.fepi.manager;

import codes.fepi.component.HealthComponent;
import codes.fepi.component.field.PositionComponent;
import codes.fepi.enemy.Enemy;
import codes.fepi.entity.Background;
import codes.fepi.entity.BasicProjectile;
import codes.fepi.entity.Explosion;
import codes.fepi.entity.TrueShotProjectile;
import codes.fepi.lib.EntityType;
import codes.fepi.lib.GameVar;
import codes.fepi.lib.Pos;
import codes.fepi.player.Player;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class DrawManager {

    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private BitmapFont bitmapFont;
    private float elapsed = 0;
    private int stars = 0;

    private static DrawManager instance;

    public static DrawManager getInstance() {
        if (instance == null) instance = new DrawManager();
        return instance;
    }

    private DrawManager() {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        bitmapFont = new BitmapFont();
    }

    public void draw() {
        // Clear
        Gdx.gl.glClearColor(0, 0, 55 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        addStars();
        elapsed += Gdx.graphics.getDeltaTime() * 2;
        batch.begin();
        ImmutableArray<Entity> entities = GameVar.engine.getEntitiesFor(Family.all(PositionComponent.class).get());
        entities.forEach(entity -> drawTex(getTex(entity), GameVar.posm.get(entity)));
        batch.end();
        drawHealthbar();
        drawPause();
        if (GameVar.gameover) gameOver();
    }

    private void addStars() {
        if (Math.random() <= 0.011) {
            Background star = new Background();
            GameVar.engine.addEntity(star);
        }
    }

    private void gameOver() {
        batch.begin();
        bitmapFont.setColor(Color.WHITE);
        bitmapFont.getData().setScale(3, 3);
        bitmapFont.draw(batch, "Game Over", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        batch.end();
    }

    private void drawHealthbar() {
        HealthComponent health = GameVar.heam.get(GameVar.player);
        int width = (int) (((float) health.getHealth() / health.maxhealth) * GameVar.healthbarSize.x);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(1, 1, width, GameVar.healthbarSize.y);
        shapeRenderer.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.rect(1, 1, GameVar.healthbarSize.x, GameVar.healthbarSize.y);
        shapeRenderer.end();
        batch.begin();
        bitmapFont.setColor(Color.WHITE);
        bitmapFont.getData().setScale(1, 1);
        bitmapFont.draw(batch, "Life", 10, 20);
        batch.end();
    }

    private void drawPause() {
        if (GameVar.paused) {
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(Color.CYAN);
            Pos pausePos = GameStateManager.getInstance().getPausePos();
            shapeRenderer.rect(pausePos.x, pausePos.y, 200, 60);
            shapeRenderer.end();
            batch.begin();
            bitmapFont.setColor(Color.BLACK);
            bitmapFont.getData().setScale(3, 3);
            bitmapFont.draw(batch, "Restart", pausePos.x + 10, pausePos.y + 50);
            batch.end();
        }
    }

    private void drawTex(Texture tex, PositionComponent pos) {
        float x = pos.x;
        float y = Gdx.graphics.getHeight() - pos.y;
        batch.draw(tex, x, y);
    }

    private Texture getTex(Entity entity) {
        if (entity instanceof Player) {
            return TextureManager.getInstance().get(EntityType.player, "spaceship.png", (int) (elapsed % 4) + 1);
        } else if (entity instanceof BasicProjectile) {
            if (GameVar.velm.get(entity).y < 0) {
                return TextureManager.getInstance().get(EntityType.projectile, "basic.png");
            } else {
                return TextureManager.getInstance().get(EntityType.projectile, "enemy_basic.png");
            }
        } else if (entity instanceof TrueShotProjectile) {
            if (GameVar.velm.get(entity).y < 0) {
                return TextureManager.getInstance().get(EntityType.projectile, "trueshot.png");
            } else {
                return TextureManager.getInstance().get(EntityType.projectile, "enemy_trueshot.png");
            }
        } else if (entity instanceof Enemy) {
            return TextureManager.getInstance().get(EntityType.enemy, ((Enemy) entity).sprite);
        } else if (entity instanceof Explosion) {
            return TextureManager.getInstance().get(EntityType.enemy, "explosion.png");
        } else if (entity instanceof Background) {
            return TextureManager.getInstance().get(EntityType.world, "star.png");
        }
        return null;
    }

    public void dispose() {
        batch.dispose();
        bitmapFont.dispose();
        shapeRenderer.dispose();
        TextureManager.getInstance().dispose();
    }
}
