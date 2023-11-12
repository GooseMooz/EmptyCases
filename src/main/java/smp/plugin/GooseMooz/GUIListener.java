package smp.plugin.GooseMooz;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class GUIListener implements Listener {

    @EventHandler
    public void onInventoryClick (InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (player.hasMetadata("OpenedGUI")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onClose (InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();

        if (player.hasMetadata("OpenedGUI")) {
            player.removeMetadata("OpenedGUI", EmptyCases.getInstance());
        }
    }
}
