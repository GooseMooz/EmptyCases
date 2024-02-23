package smp.plugin.GooseMooz.Menu;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

public class OptionsMenu {
    public static Inventory optionsMenu() {
        Inventory inventory = Bukkit.createInventory(null, 9 * 4, Component.text("Options"));


        return inventory;
    }
}
