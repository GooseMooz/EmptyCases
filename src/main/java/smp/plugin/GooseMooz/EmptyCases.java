package smp.plugin.GooseMooz;

import org.bukkit.plugin.java.JavaPlugin;
import smp.plugin.GooseMooz.commands.GUICommand;

public final class EmptyCases extends JavaPlugin {
    private static EmptyCases instance;
    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
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
