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
import org.bukkit.metadata.FixedMetadataValue;
import smp.plugin.GooseMooz.Animations.AnimationsGUI;
import smp.plugin.GooseMooz.Case.Case;
import smp.plugin.GooseMooz.Menu.CreateCaseMenu;
import smp.plugin.GooseMooz.SupportFunctions.HelperFunctions;
import smp.plugin.GooseMooz.SupportFunctions.PlayerNameInput;

public class GUIListener implements Listener {
    public static Inventory menu = CreateCaseMenu.initialMenu();

    Case currentCase = new Case("New Case");

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (player.hasMetadata("OpenedGUI")) {
            event.setCancelled(true);

            if (event.getSlot() == 11) {
                //player.openInventory();
            } else if (event.getSlot() == 13) {
                menu = CreateCaseMenu.initialMenu();
                player.openInventory(menu);
                currentCase = CreateCaseMenu.caseFromInventory(menu);
                currentCase.makeCurrent();
                player.setMetadata("CreateCaseGUI", new FixedMetadataValue(EmptyCases.getInstance(), "Create Cases Menu"));
                player.removeMetadata("OpenedGUI", EmptyCases.getInstance());
            } else if (event.getSlot() == 15) {
                //player.openInventory();
            }
        } else if (player.hasMetadata("CreateCaseGUI")) {
            event.setCancelled(true);
            int slot = event.getSlot();
            if (slot == 0) {
                // Change Icon
                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, (float) 0.25, 0);
                menu = CreateCaseMenu.iconChooseMenu();
                player.openInventory(menu);
                player.removeMetadata("CreateCaseGui", EmptyCases.getInstance());
                player.setMetadata("CaseIconChoose", new FixedMetadataValue(EmptyCases.getInstance(), "Choose Case Icon"));
            } else if (slot == 1) {
                // Change Title
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
                menu = CreateCaseMenu.mainMenu();
                player.openInventory(menu);
                AnimationsGUI animationsGUI = new AnimationsGUI(menu);
                Bukkit.getScheduler().runTaskTimer(EmptyCases.getInstance(), animationsGUI.mainMenu(), 0L, 20L);
                player.setMetadata("OpenedGUI", new FixedMetadataValue(EmptyCases.getInstance(), "Preferences Menu"));
                player.playSound(player.getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, (float) 0.25, 0);
            }
        } else if (player.hasMetadata("CaseIconChoose")) {
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
    public void onSignEdit(SignChangeEvent event) {
        Component name = event.line(0);
        Player player = event.getPlayer();
        if (player.hasMetadata("EditName")) {
            assert name != null;
            currentCase.setName(HelperFunctions.componentToString(name));
            currentCase.makeCurrent();
            menu = CreateCaseMenu.inventoryFromCase(currentCase);
            player.openInventory(menu);
            Location playerLocation = player.getLocation();
            Block restore = player.getWorld().getBlockAt(playerLocation.getBlockX(), playerLocation.getBlockY() - 4, playerLocation.getBlockZ());
            Material prev = Material.valueOf(restore.getMetadata("PrevBlock").get(0).asString());
            restore.setType(prev);
            restore.removeMetadata("PrevBlock", EmptyCases.getInstance());
            player.setMetadata("CreateCaseGUI", new FixedMetadataValue(EmptyCases.getInstance(), "Create Cases Menu"));
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();

        if (player.hasMetadata("OpenedGUI")) {
            player.removeMetadata("OpenedGUI", EmptyCases.getInstance());
        }

        if (player.hasMetadata("CaseIconChoose")) {
            player.removeMetadata("CaseIconChoose", EmptyCases.getInstance());
        }

        if (player.hasMetadata("CreateCaseGUI")) {
            if (player.hasMetadata("EditName")) {
                player.removeMetadata("EditName", EmptyCases.getInstance());
                return;
            }

            player.removeMetadata("CreateCaseGUI", EmptyCases.getInstance());
            currentCase.onClose();
        }
    }
}
