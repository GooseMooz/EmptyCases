package smp.plugin.GooseMooz;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.bukkit.plugin.java.JavaPlugin;
import smp.plugin.GooseMooz.Case.Case;
import smp.plugin.GooseMooz.commands.GUICommand;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public final class EmptyCases extends JavaPlugin {
    private static EmptyCases instance;
    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        jsonInit("emptycases/cases.json");
        jsonInit("emptycases/current.json");
        jsonInit("emptycases/templates.json");
        getServer().getPluginManager().registerEvents(new GUIListener(), this);
        getCommand("emptycases").setExecutor(new GUICommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static EmptyCases getInstance() {
        return instance;
    }

    private static void jsonInit(String path) {
        File file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                Path dirCreate = Path.of("emptycases");
                try {
                    Files.createDirectories(dirCreate);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
}
