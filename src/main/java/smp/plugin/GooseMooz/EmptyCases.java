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
import java.util.ArrayList;

public final class EmptyCases extends JavaPlugin {
    private static EmptyCases instance;
    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        String path = "cases.json";
        File file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            FileReader fileReader = new FileReader(path);
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Case>>(){}.getType();
            gson.fromJson(fileReader, listType);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
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
}
