package smp.plugin.GooseMooz;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.entity.Player;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.metadata.FixedMetadataValue;
import smp.plugin.GooseMooz.Menu.CreateCaseMenu;
import smp.plugin.GooseMooz.SupportFunctions.PlayerSignInput;

public class GUIListener implements Listener {
    public static Inventory menu = CreateCaseMenu.initialMenu();

    @EventHandler
    public void onInventoryClick (InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (player.hasMetadata("OpenedGUI")) {
            event.setCancelled(true);

            if (event.getSlot() == 11) {
                //player.openInventory();
            } else if (event.getSlot() == 13) {
                player.openInventory(menu);
                player.setMetadata("CreateCaseGUI", new FixedMetadataValue(EmptyCases.getInstance(), "Create Cases Menu"));
                player.removeMetadata("OpenedGUI", EmptyCases.getInstance());
            } else if (event.getSlot() == 15) {
                //player.openInventory();
            }
        }

        if (player.hasMetadata("CreateCaseGUI")) {
            event.setCancelled(true);

            int slot = event.getSlot();
            if (slot == 0) {
                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, (float) 0.25, 0);
            } else if (slot == 1) {
                // Open Title Editing Sign
            }
        }
    }

    @EventHandler
    public static void onSignChange(SignChangeEvent event) {
        Player player = event.getPlayer();
        if (player.hasMetadata("Input")) {
            player.setMetadata("Input", new FixedMetadataValue(EmptyCases.getInstance(), event.line(1)));
            if (event.line(1) != null) {
                Inventory changedMenu = Bukkit.createInventory(menu.getHolder(), menu.getSize(), event.line(1));
                changedMenu.setContents(menu.getContents());
                player.openInventory(changedMenu);
                player.setMetadata("CreateCaseGUI", new FixedMetadataValue(EmptyCases.getInstance(), "Create Cases Menu"));
                player.removeMetadata("Input", EmptyCases.getInstance());
            } else {
                // TODO: MAKE THE ELSE LOGIC
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
