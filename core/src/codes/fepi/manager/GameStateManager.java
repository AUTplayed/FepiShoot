package codes.fepi.manager;

import codes.fepi.lib.GameVar;
import codes.fepi.lib.Pos;
import codes.fepi.player.Player;
import com.badlogic.gdx.Gdx;

public class GameStateManager {

    private static GameStateManager instance;

    private GameStateManager() {
    }

    public static GameStateManager getInstance() {
        if (instance == null) instance = new GameStateManager();
        return instance;
    }

    public void restart() {
        GameVar.engine.removeAllEntities();
        Player player = new Player();
        GameVar.player = player;
        GameVar.engine.addEntity(player);
        GameVar.paused = false;
        GameVar.gameover = false;
        restartSystems();
    }

    public void stopSystems() {
        GameVar.engine.getSystems().forEach(s -> s.setProcessing(false));
    }

    public void restartSystems() {
        GameVar.engine.getSystems().forEach(s -> s.setProcessing(true));
    }

    public Pos getPausePos() {
        return new Pos((Gdx.graphics.getWidth() / 2), (Gdx.graphics.getHeight() / 2));
    }
}
