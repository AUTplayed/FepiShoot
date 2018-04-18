package codes.fepi.manager;

import codes.fepi.enemy.Enemy;
import codes.fepi.enemy.FunctionMoveEnemy;
import codes.fepi.enemy.StupidEnemy;
import codes.fepi.lib.EnemyType;
import codes.fepi.lib.GameVar;
import codes.fepi.lib.Pos;
import com.badlogic.gdx.Gdx;

public class SpawnManager {

    private int enemyCount = 0;
    private int spawntime = 10;

    private SpawnManager() {
    }

    public static SpawnManager getInstance() {
        if (instance == null) instance = new SpawnManager();
        return instance;
    }

    private static SpawnManager instance;

    public void update() {
        if(!GameVar.paused){
            spawntime -= Gdx.graphics.getDeltaTime();
            spawnEnemies();
        }
    }

    private void spawnEnemies() {
        if (spawntime <= 0) {
            spawntime = (int) (Math.random() * 80);
            GameVar.engine.addEntity(new StupidEnemy(3, EnemyType.Basic, 100));
            /*GameVar.engine.addEntity(new FunctionMoveEnemy(1, EnemyType.TrueShot, t ->
                    new Pos((float) ((Math.sin(t)) * Gdx.graphics.getWidth() / 8), t * 30)));
            GameVar.engine.addEntity(new FunctionMoveEnemy(1, EnemyType.DoubleShotMove, t ->
                    new Pos((float) ((Math.sqrt(t) / t) * Gdx.graphics.getWidth() / 8), t * 30)));
            GameVar.engine.addEntity(new FunctionMoveEnemy(1, EnemyType.DoubleShotMove, t ->
                    new Pos((float) (Math.asin(Math.sin(t)) * Gdx.graphics.getWidth() / 8), t * 30)));*/
            GameVar.engine.addEntity(new StupidEnemy(2, EnemyType.Advanced, 100));
            //GameVar.engine.addEntity(new StupidEnemy(1, EnemyType.DoubleShot, 100));
            enemyCount += 3;
        }
    }
}
