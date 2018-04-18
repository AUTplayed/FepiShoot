package codes.fepi.desktop;

import codes.fepi.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import java.awt.*;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "FepiShoot";
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        config.width = screenSize.width;
        config.height = screenSize.height;// - 100;
        config.width /= 2;
        config.height -= 100;
        config.resizable = false;
        //config.fullscreen = true;
        new LwjglApplication(new Game(), config);
    }
}
