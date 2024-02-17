package smp.plugin.GooseMooz;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import smp.plugin.GooseMooz.Animations.AnimationsGUI;
import smp.plugin.GooseMooz.Case.Case;
import smp.plugin.GooseMooz.Menu.CreateCaseMenu;
import smp.plugin.GooseMooz.SupportFunctions.PlayerNameInput;

public class GUIListener implements Listener {
    public static Inventory menu = CreateCaseMenu.initialMenu();

    Case currentCase = new Case("New Case");

    @EventHandler
    public void onInventoryClick (InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (player.hasMetadata("OpenedGUI")) {
            event.setCancelled(true);

            if (event.getSlot() == 11) {
                //player.openInventory();
            } else if (event.getSlot() == 13) {
                currentCase.onClose();
                menu = CreateCaseMenu.initialMenu();
                player.openInventory(menu);
                player.setMetadata("CreateCaseGUI", new FixedMetadataValue(EmptyCases.getInstance(), "Create Cases Menu"));
                player.removeMetadata("OpenedGUI", EmptyCases.getInstance());
            } else if (event.getSlot() == 15) {
                //player.openInventory();
            }
        }

        if (player.hasMetadata("CreateCaseGUI")) {
            event.setCancelled(true);
            currentCase.makeCurrent();
            int slot = event.getSlot();
            if (slot == 0) {
                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, (float) 0.25, 0);
                player.openInventory(CreateCaseMenu.iconChooseMenu());
                player.removeMetadata("CreateCaseGui", EmptyCases.getInstance());
                player.setMetadata("CaseIconChoose", new FixedMetadataValue(EmptyCases.getInstance(), "Choose Case Icon"));
            } else if (slot == 1) {
                // Open Title Editing Sign
                player.playSound(player.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, (float) 0.25, 0);
                PlayerNameInput.createNameInput(player);
            } else if (slot == 18) {
                // Discard Case
                currentCase.onClose();
                menu = CreateCaseMenu.mainMenu();
                player.openInventory(menu);
                AnimationsGUI animationsGUI = new AnimationsGUI(menu);
                Bukkit.getScheduler().runTaskTimer(EmptyCases.getInstance(), animationsGUI.mainMenu(), 0L, 20L);
                player.setMetadata("OpenedGUI", new FixedMetadataValue(EmptyCases.getInstance(), "Preferences Menu"));
                player.removeMetadata("CreateCaseGUI", EmptyCases.getInstance());
                player.playSound(player.getLocation(), Sound.ENTITY_CREEPER_DEATH, (float) 0.25, 0);
            } else if (slot == 22) {
                // Save The Case to the templates
                currentCase = CreateCaseMenu.caseFromInventory(menu);
                currentCase.saveCase(2);
                player.playSound(player.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_CHIME, (float) 0.25, 0);
            } else if (slot == 26) {
                // Save The Case
                currentCase = CreateCaseMenu.caseFromInventory(menu);
                currentCase.saveCase(1);
                player.closeInventory();
                menu = CreateCaseMenu.initialMenu();
                player.removeMetadata("CreateCaseGUI", EmptyCases.getInstance());
            }
        }

        if (player.hasMetadata("CaseIconChoose")) {
            event.setCancelled(true);
            int slot = event.getSlot();
            currentCase.setIcon(slot);
            currentCase.makeCurrent();
            menu = CreateCaseMenu.inventoryFromCase(currentCase);
            player.openInventory(menu);
            player.removeMetadata("CaseIconChoose", EmptyCases.getInstance());
            player.setMetadata("CreateCaseGUI", new FixedMetadataValue(EmptyCases.getInstance(), "Create Cases Menu"));
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
                ItemStack item = menu.getItem(i);

                if (item != null) {
                    if (i == 1) {
                        ItemMeta signName = item.getItemMeta(); //TODO: DO IT TROUGH JSON
                        signName.displayName(name);
                        item.setItemMeta(signName);
                    }
                    temp.setItem(i, item.clone());
                }
            }
            menu = temp;
            currentCase = CreateCaseMenu.caseFromInventory(menu);
            currentCase.makeCurrent(); // TODO: FIGURE OUT WHY DOESN'T WORK
            player.openInventory(menu);
            player.removeMetadata("EditName", EmptyCases.getInstance());
            Location playerLocation = player.getLocation();
            Block restore = player.getWorld().getBlockAt(playerLocation.getBlockX(), playerLocation.getBlockY() - 4, playerLocation.getBlockZ());
            Material prev = Material.valueOf(restore.getMetadata("PrevBlock").get(0).asString());
            restore.setType(prev);
            restore.removeMetadata("PrevBlock", EmptyCases.getInstance());
            player.setMetadata("CreateCaseGUI", new FixedMetadataValue(EmptyCases.getInstance(), "Create Cases Menu"));
        }
    }

    @EventHandler
    public void onClose (InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();

        if (player.hasMetadata("OpenedGUI")) {
            player.removeMetadata("OpenedGUI", EmptyCases.getInstance());
        }

        if (player.hasMetadata("CaseIconChoose")) {
            player.removeMetadata("CaseIconChoose", EmptyCases.getInstance());
        }

        if (player.hasMetadata("CreateCaseGUI")) {
            player.removeMetadata("CreateCaseGUI", EmptyCases.getInstance());
            currentCase.onClose();
        }
    }
}
