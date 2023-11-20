package smp.plugin.GooseMooz;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import smp.plugin.GooseMooz.Menu.CreateCaseMenu;
import smp.plugin.GooseMooz.SupportFunctions.PlayerNameInput;

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
                BlockState save = PlayerNameInput.createNameInput(player);
            }
        }
    }

    @EventHandler
    public void onSignEdit (SignChangeEvent event) {
        Component name = event.line(0);
        Player player = event.getPlayer();
        if (player.hasMetadata("EditName")) {
            assert name != null;
            Inventory temp = Bukkit.createInventory(null, 9*3, name);
            for (int i = 0; i < 27; i++) {
                ItemStack item = menu.getItem(i);                           //TODO: Add Metadata, add block replacement
                if (item != null) {
                    temp.setItem(i, item.clone());
                }
            }
            menu = temp;
            player.openInventory(menu);
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
