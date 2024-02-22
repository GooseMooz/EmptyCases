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
import org.bukkit.metadata.FixedMetadataValue;
import smp.plugin.GooseMooz.Animations.AnimationsGUI;
import smp.plugin.GooseMooz.Case.Case;
import smp.plugin.GooseMooz.Menu.CreateCaseMenu;
import smp.plugin.GooseMooz.Menu.EditCasesMenu;
import smp.plugin.GooseMooz.SupportFunctions.HelperFunctions;
import smp.plugin.GooseMooz.SupportFunctions.PlayerSignInput;

public class GUIListener implements Listener {
    public static Inventory menu = CreateCaseMenu.initialMenu();

    Case currentCase = new Case("New Case");

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (player.hasMetadata("OpenedGUI")) {
            event.setCancelled(true);

            if (event.getSlot() == 11) {
                menu = EditCasesMenu.initMenu();
                player.openInventory(menu);
                player.setMetadata("CaseEdit", new FixedMetadataValue(EmptyCases.getInstance(), "Editing Cases"));
            } else if (event.getSlot() == 13) {
                menu = CreateCaseMenu.initialMenu();
                player.openInventory(menu);
                currentCase = CreateCaseMenu.caseFromInventory(menu);
                currentCase.makeCurrent();
                HelperFunctions.removeSetMetadata("OpenedGUI", "CreateCaseGUI", "Create Cases Menu", player);
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
                HelperFunctions.removeSetMetadata("CreateCaseGui", "CaseIconChoose", "Choose Case Icon", player);
            } else if (slot == 1) {
                // Change Title
                player.playSound(player.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, (float) 0.25, 0);
                PlayerSignInput.createNameInput(player);
            } else if (slot == 10) {
                // Case Storage
                player.playSound(player.getLocation(), Sound.BLOCK_BARREL_OPEN, (float) 0.25, 0);
                menu = CreateCaseMenu.storageMenu(0, currentCase);
                player.openInventory(menu);
                HelperFunctions.removeSetMetadata("CreateCaseGui", "CaseStorage", "Storing Items", player);
            } else if (slot == 18) {
                // Discard Case
                currentCase.onClose();
                menu = CreateCaseMenu.mainMenu();
                player.openInventory(menu);
                AnimationsGUI animationsGUI = new AnimationsGUI(menu);
                Bukkit.getScheduler().runTaskTimer(EmptyCases.getInstance(), animationsGUI.mainMenu(), 0L, 20L);
                HelperFunctions.removeSetMetadata("CreateCaseGUI", "OpenedGUI", "Preferences Menu", player);
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
                menu = CreateCaseMenu.mainMenu();
                player.openInventory(menu);
                AnimationsGUI animationsGUI = new AnimationsGUI(menu);
                Bukkit.getScheduler().runTaskTimer(EmptyCases.getInstance(), animationsGUI.mainMenu(), 0L, 20L);
                HelperFunctions.removeSetMetadata("CreateCaseGUI", "OpenedGUI", "Preferences Menu", player);
                player.playSound(player.getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, (float) 0.25, 0);
            }
        } else if (player.hasMetadata("CaseIconChoose")) {
            event.setCancelled(true);
            int slot = event.getSlot();
            currentCase.setIcon(slot);
            currentCase.makeCurrent();
            menu = CreateCaseMenu.inventoryFromCase(currentCase);
            player.openInventory(menu);
            HelperFunctions.removeSetMetadata("CaseIconChoose", "CreateCaseGUI", "Create Cases Menu", player);
        } else if (player.hasMetadata("CaseEdit")) {
            event.setCancelled(true);
            int slot = event.getSlot();
            currentCase = HelperFunctions.getCases().get(slot);
            currentCase.makeCurrent();
            menu = CreateCaseMenu.inventoryFromCase(currentCase);
            player.openInventory(menu);
            HelperFunctions.removeSetMetadata("CaseEdit", "EditCaseGUI", "Editing Current Case", player);
        } else if (player.hasMetadata("CaseStorage")) {
            event.setCancelled(true);
            int slot = event.getSlot();
            if (slot == 49) {
                //Add New Item
                menu = CreateCaseMenu.addItemMenu();
                player.openInventory(menu);
                HelperFunctions.removeSetMetadata("CaseStorage", "AddItem", "Adding Item", player);
            } else if (slot == 53) {
                // Next page if available
            } else if (slot == 45) {
                // Prev page if available
            }
        } else if (player.hasMetadata("AddItem")) {
            int slot = event.getSlot();
            if (slot == 4) {
                ItemStack item = event.getCurrentItem();
                currentCase.addItem(item);
                PlayerSignInput.createNameInput(player);
            } else {
                event.setCancelled(true);
            }
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
            HelperFunctions.removeSetMetadata("PrevBlock", "CreateCaseGUI", "Create Cases Menu", player);
        } else if (player.hasMetadata("EditChance")) {
            assert name != null;
            //TODO: Figure out how to manage chances of the items in case
            // I'll do it using Meta Descriptioin of items. This way I can easily store it and staff can see the chances
            // But what if item already has description????
            // Maybe should have another Array in Case with corresponding chances to items :<
        }
    }


    //TODO: PROPERLY CLOSE ALL MENUS
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
            } else if (player.hasMetadata("EditChance")) {
                player.removeMetadata("EditChance", EmptyCases.getInstance());
                return;
            }

            player.removeMetadata("CreateCaseGUI", EmptyCases.getInstance());
            currentCase.onClose();
        }
    }
}
