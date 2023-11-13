package smp.plugin.GooseMooz;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import smp.plugin.GooseMooz.Menu.CreateCaseMenu;

public class GUIListener implements Listener {

    @EventHandler
    public void onInventoryClick (InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (player.hasMetadata("OpenedGUI")) {
            event.setCancelled(true);

            if (event.getSlot() == 11) {
                //player.openInventory();
            } else if (event.getSlot() == 13) {
                Inventory menu = CreateCaseMenu.initialMenu(player);
                player.openInventory(menu);
            } else if (event.getSlot() == 15) {
                //player.openInventory();
            }
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
