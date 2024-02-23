package smp.plugin.GooseMooz;

import net.kyori.adventure.text.Component;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import smp.plugin.GooseMooz.Animations.AnimationsGUI;
import smp.plugin.GooseMooz.Case.Case;
import smp.plugin.GooseMooz.Menu.CreateCaseMenu;
import smp.plugin.GooseMooz.Menu.EditCasesMenu;
import smp.plugin.GooseMooz.Menu.OptionsMenu;
import smp.plugin.GooseMooz.SupportFunctions.HelperFunctions;
import smp.plugin.GooseMooz.SupportFunctions.MetadataKeys;
import smp.plugin.GooseMooz.SupportFunctions.PlayerSignInput;

import java.util.Objects;

public class GUIListener implements Listener {

    public static Inventory menu = CreateCaseMenu.initialMenu();

    Case currentCase = new Case("New Case");

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (player.hasMetadata(MetadataKeys.OPENED_GUI)) {
            event.setCancelled(true);

            if (event.getSlot() == 11) {
                menu = EditCasesMenu.initMenu();
                player.openInventory(menu);
                player.setMetadata(MetadataKeys.CASE_EDIT, new FixedMetadataValue(EmptyCases.getInstance(), "Editing Cases"));
            } else if (event.getSlot() == 13) {
                menu = CreateCaseMenu.initialMenu();
                player.openInventory(menu);
                currentCase = CreateCaseMenu.caseFromInventory(menu);
                currentCase.makeCurrent();
                HelperFunctions.removeSetMetadata(MetadataKeys.OPENED_GUI, MetadataKeys.CREATE_CASE, "Create Cases Menu", player);
            } else if (event.getSlot() == 15) {
                //player.openInventory();
                menu = OptionsMenu.optionsMenu();
            }
        } else if (player.hasMetadata(MetadataKeys.CREATE_CASE)) {
            event.setCancelled(true);
            int slot = event.getSlot();
            if (slot == 0) {
                // Change Icon
                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, (float) 0.25, 0);
                menu = CreateCaseMenu.iconChooseMenu();
                player.openInventory(menu);
                HelperFunctions.removeSetMetadata(MetadataKeys.CREATE_CASE, MetadataKeys.ICON_CHOOSE, "Choose Case Icon", player);
            } else if (slot == 1) {
                // Change Title
                player.playSound(player.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, (float) 0.25, 0);
                PlayerSignInput.createNameInput(player);
            } else if (slot == 10) {
                // Case Storage
                player.playSound(player.getLocation(), Sound.BLOCK_BARREL_OPEN, (float) 0.25, 0);
                menu = CreateCaseMenu.storageMenu(0, currentCase);
                player.openInventory(menu);
                HelperFunctions.removeSetMetadata(MetadataKeys.CREATE_CASE, MetadataKeys.CASE_STORAGE, "Storing Items", player);
            } else if (slot == 18) {
                // Discard Case
                currentCase.onClose();
                menu = CreateCaseMenu.mainMenu();
                player.openInventory(menu);
                AnimationsGUI animationsGUI = new AnimationsGUI(menu);
                Bukkit.getScheduler().runTaskTimer(EmptyCases.getInstance(), animationsGUI.mainMenu(), 0L, 20L);
                HelperFunctions.removeSetMetadata(MetadataKeys.CREATE_CASE, MetadataKeys.OPENED_GUI, "Preferences Menu", player);
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
                HelperFunctions.removeSetMetadata(MetadataKeys.CREATE_CASE, MetadataKeys.OPENED_GUI, "Preferences Menu", player);
                player.playSound(player.getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, (float) 0.25, 0);
            }
        } else if (player.hasMetadata(MetadataKeys.ICON_CHOOSE)) {
            event.setCancelled(true);
            int slot = event.getSlot();
            currentCase.setIcon(slot);
            currentCase.makeCurrent();
            menu = CreateCaseMenu.inventoryFromCase(currentCase);
            player.openInventory(menu);
            HelperFunctions.removeSetMetadata(MetadataKeys.ICON_CHOOSE, MetadataKeys.CREATE_CASE, "Create Cases Menu", player);
        } else if (player.hasMetadata(MetadataKeys.CASE_EDIT)) {
            event.setCancelled(true);
            int slot = event.getSlot();
            currentCase = HelperFunctions.getCases().get(slot);
            currentCase.makeCurrent();
            menu = CreateCaseMenu.inventoryFromCase(currentCase);
            player.openInventory(menu);
            HelperFunctions.removeSetMetadata(MetadataKeys.CASE_EDIT, MetadataKeys.CASE_EDIT_GUI, "Editing Current Case", player);
        } else if (player.hasMetadata(MetadataKeys.CASE_STORAGE)) {
            event.setCancelled(true);
            int slot = event.getSlot();
            if (slot == 49) {
                //Add New Item
                menu = CreateCaseMenu.addItemMenu();
                player.openInventory(menu);
                HelperFunctions.removeSetMetadata(MetadataKeys.CASE_STORAGE, MetadataKeys.ADD_ITEM, "Adding Item", player);
            } else if (slot == 53) {
                // Next page if available
                if (Objects.requireNonNull(event.getCurrentItem()).getType() == Material.LIME_DYE) {
                    menu = CreateCaseMenu.storageMenu(Objects.requireNonNull(event.getInventory().getItem(4)).getAmount(), currentCase);
                    player.openInventory(menu);
                }
            } else if (slot == 45) {
                // Prev page if available
                if (Objects.requireNonNull(event.getCurrentItem()).getType() == Material.LIME_DYE) {
                    menu = CreateCaseMenu.storageMenu(Objects.requireNonNull(event.getInventory().getItem(4)).getAmount() - 2, currentCase);
                    player.openInventory(menu);
                }
            }
        } else if (player.hasMetadata(MetadataKeys.ADD_ITEM)) {
            int slot = event.getSlot();
            ItemStack glassFill = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
            ItemMeta metaGlassFill = glassFill.getItemMeta();
            metaGlassFill.displayName(Component.text(""));
            glassFill.setItemMeta(metaGlassFill);

            ItemStack cursorItem = event.getCursor();
            boolean isPlaced = event.getAction() == InventoryAction.PLACE_ALL ||
                    event.getAction() == InventoryAction.PLACE_ONE ||
                    event.getAction() == InventoryAction.PLACE_SOME;

            boolean isPicked = event.getAction() == InventoryAction.PICKUP_ALL ||
                    event.getAction() == InventoryAction.PICKUP_ONE ||
                    event.getAction() == InventoryAction.PICKUP_SOME ||
                    event.getAction() == InventoryAction.PICKUP_HALF;

            if (slot == 4 && isPlaced) {
                currentCase.addItem(cursorItem);
                PlayerSignInput.createChanceInput(player, 0);
            } else if (isPicked && cursorItem == glassFill) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onSignEdit(SignChangeEvent event) {
        Component name = event.line(0);
        Player player = event.getPlayer();
        if (player.hasMetadata(MetadataKeys.EDIT_NAME)) {
            assert name != null;
            currentCase.setName(HelperFunctions.componentToString(name));
            currentCase.makeCurrent();
            menu = CreateCaseMenu.inventoryFromCase(currentCase);
            player.openInventory(menu);
            Location playerLocation = player.getLocation();
            Block restore = player.getWorld().getBlockAt(playerLocation.getBlockX(), playerLocation.getBlockY() - 4, playerLocation.getBlockZ());
            Material prev = Material.valueOf(restore.getMetadata("PrevBlock").get(0).asString());
            restore.setType(prev);
            HelperFunctions.removeSetMetadata("PrevBlock", MetadataKeys.CREATE_CASE, "Create Cases Menu", player);
        } else if (player.hasMetadata(MetadataKeys.EDIT_CHANCE)) {
            assert name != null;
            currentCase.setChance(player.getMetadata(MetadataKeys.EDIT_CHANCE).get(0).asInt(), Double.parseDouble(HelperFunctions.componentToString(name)));
            currentCase.makeCurrent();
            menu = CreateCaseMenu.storageMenu(0, currentCase);
            player.openInventory(menu);
            Location playerLocation = player.getLocation();
            Block restore = player.getWorld().getBlockAt(playerLocation.getBlockX(), playerLocation.getBlockY() - 4, playerLocation.getBlockZ());
            Material prev = Material.valueOf(restore.getMetadata("PrevBlock").get(0).asString());
            restore.setType(prev);
            HelperFunctions.removeSetMetadata("PrevBlock", MetadataKeys.CASE_STORAGE, "Editing Current Case", player);
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();

        if (player.hasMetadata(MetadataKeys.OPENED_GUI)) {
            player.removeMetadata(MetadataKeys.OPENED_GUI, EmptyCases.getInstance());
        }

        if (player.hasMetadata(MetadataKeys.ICON_CHOOSE)) {
            player.removeMetadata(MetadataKeys.ICON_CHOOSE, EmptyCases.getInstance());
        }

        if (player.hasMetadata(MetadataKeys.CREATE_CASE)) {
            if (player.hasMetadata(MetadataKeys.EDIT_NAME)) {
                player.removeMetadata(MetadataKeys.EDIT_NAME, EmptyCases.getInstance());
                return;
            } else if (player.hasMetadata(MetadataKeys.EDIT_CHANCE)) {
                player.removeMetadata(MetadataKeys.EDIT_CHANCE, EmptyCases.getInstance());
                return;
            }

            player.removeMetadata(MetadataKeys.CREATE_CASE, EmptyCases.getInstance());
            currentCase.onClose();
        }

        if (player.hasMetadata(MetadataKeys.CASE_EDIT)) {
            player.removeMetadata(MetadataKeys.CASE_EDIT, EmptyCases.getInstance());
        }

        if (player.hasMetadata(MetadataKeys.ADD_ITEM)) {
            player.removeMetadata(MetadataKeys.ADD_ITEM, EmptyCases.getInstance());
        }

        if (player.hasMetadata(MetadataKeys.CASE_EDIT_GUI)) {
            player.removeMetadata(MetadataKeys.CASE_EDIT_GUI, EmptyCases.getInstance());
        }

        if (player.hasMetadata(MetadataKeys.CASE_STORAGE)) {
            player.removeMetadata(MetadataKeys.CASE_STORAGE, EmptyCases.getInstance());
        }
    }
}
