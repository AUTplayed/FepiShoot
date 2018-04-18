package codes.fepi.manager;

import codes.fepi.lib.EntityType;
import codes.fepi.lib.GameVar;
import com.badlogic.gdx.graphics.Texture;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;

public class TextureManager {

    private HashMap<EntityType, HashMap<String, Texture>> images;

    public static TextureManager getInstance() {
        if (instance == null) instance = new TextureManager();
        return instance;
    }

    private static TextureManager instance;

    private TextureManager() {
        images = new HashMap<>();
        try {
            loadImages();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Texture get(EntityType type, String name) {
        return images.get(type).get(name);
    }

    public Texture get(EntityType type, String name, int time) {
        String[] split = name.split("\\.");
        return images.get(type).get(String.format("%s%d.%s", split[0], time, split[1]));
    }

    private void loadImages() throws IOException {
        for (EntityType folder : Arrays.asList(EntityType.values())) {
            HashMap<String, Texture> entityImages = new HashMap<>();
            Files.list(Paths.get(folder.name())).forEach(path -> {
                entityImages.put(path.getFileName().toString(), new Texture(path.toString()));
            });
            images.put(folder, entityImages);
        }
    }

    public void dispose() {
        images.forEach((type, map) -> map.forEach((s, tex) -> tex.dispose()));
    }
}
