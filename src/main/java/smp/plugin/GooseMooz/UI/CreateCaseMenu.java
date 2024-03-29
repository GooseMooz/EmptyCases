package smp.plugin.GooseMooz.UI;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import smp.plugin.GooseMooz.Models.Case;
import smp.plugin.GooseMooz.SupportFunctions.HelperFunctions;
import smp.plugin.GooseMooz.Variables.InitIcons;

import java.util.Objects;

public class CreateCaseMenu {

    public static Case caseFromInventory(Inventory inventory) {
        assert inventory.getItem(1) != null;
        String name = HelperFunctions.componentToString((Objects.requireNonNull(Objects.requireNonNull(inventory.getItem(1)).getItemMeta().displayName())));
        int icon = HelperFunctions.iconToInt(inventory.getItem(0));
        return new Case(name, icon);
    }
    public static Inventory inventoryFromCase(Case template) {
        Inventory newInv = Bukkit.createInventory(null, 9*3, Component.text(template.getName()));
        ItemStack icon = HelperFunctions.intToIcon(template.getIcon());
        ItemMeta metaCaseIcon = icon.getItemMeta();
        Style style = Objects.requireNonNull(metaCaseIcon.displayName()).style();
        metaCaseIcon.displayName(Component.text("Case Icon").style(style));
        icon.setItemMeta(metaCaseIcon);
        newInv.setItem(0, icon);

        ItemStack caseName = InitIcons.initNameIcon(template.getName());
        newInv.setItem(1, caseName);

        //Apply Template
        ItemStack templateIcon = InitIcons.initTemplateIcon();

        //Settings Icon
        ItemStack settingsIcon = InitIcons.initSettingsIcon();

        //Contains
        ItemStack containsIcon = InitIcons.initContainsIcon();

        //Discard
        ItemStack discardIcon = InitIcons.initDiscardIcon();

        //Save Template
        ItemStack saveTemplateIcon = InitIcons.initSaveTemplateIcon();

        //Save Case
        ItemStack saveCaseIcon = InitIcons.initSaveCaseIcon();

        newInv.setItem(1, caseName); //Change Name Function
        newInv.setItem(8, templateIcon); //Apply Template
        newInv.setItem(9, settingsIcon); //Settings Icon
        newInv.setItem(10, containsIcon); //Settings
        newInv.setItem(18, discardIcon); //Discard
        newInv.setItem(22, saveTemplateIcon); //Save Template
        newInv.setItem(26, saveCaseIcon); //Save Case
        return newInv;
    }

    public static Inventory initialMenu () {
        Inventory menu = Bukkit.createInventory(null, 9 * 3, Component.text("New Case"));
        
        //Case Icon
        ItemStack caseIcon = InitIcons.initCaseIcon();
        //Change Name Function
        ItemStack caseName = InitIcons.initNameIcon("New Case");

        //Apply Template
        ItemStack templateIcon = InitIcons.initTemplateIcon();

        //Settings Icon
        ItemStack settingsIcon = InitIcons.initSettingsIcon();

        //Contains
        ItemStack containsIcon = InitIcons.initContainsIcon();

        //Discard
        ItemStack discardIcon = InitIcons.initDiscardIcon();

        //Save Template
        ItemStack saveTemplateIcon = InitIcons.initSaveTemplateIcon();

        //Save Case
        ItemStack saveCaseIcon = InitIcons.initSaveCaseIcon();

        menu.setItem(0, caseIcon); //Case Icon
        menu.setItem(1, caseName); //Change Name Function
        menu.setItem(8, templateIcon); //Apply Template
        menu.setItem(9, settingsIcon); //Settings Icon
        menu.setItem(10, containsIcon); //Settings
        //Settings continue
        //TODO: SETTINGS:
        // Change background    MERGE THESE
        // Change animation     MERGE THESE
        // Change item moving pattern
        // Add items that can drop
        // Chances of drops <- Creates list of items that will spin...
        // What opens the case?
        // Sounds on winning
        // Effects in chat, etc.
        menu.setItem(18, discardIcon); //Discard
        menu.setItem(22, saveTemplateIcon); //Save Template
        menu.setItem(26, saveCaseIcon); //Save Case

        return menu;
    }

    public static Inventory iconChooseMenu () {
        Inventory menu = Bukkit.createInventory(null, 9, Component.text("Choose Case Icon"));
        ItemStack caseIcon = InitIcons.initCaseIcon();
        ItemStack enderIcon = InitIcons.enderChestIcon();
        ItemStack bundleIcon = InitIcons.bundleIcon();
        menu.setItem(0, caseIcon);
        menu.setItem(1, enderIcon);
        menu.setItem(2, bundleIcon);
        return menu;
    }

    public static Inventory mainMenu() {
        Inventory inventory = Bukkit.createInventory(null, 9 * 3, Component.text("Preferences Menu").color(TextColor.color(25, 27, 123)));

        ItemStack createNewCase = new ItemStack(Material.CHEST);
        ItemMeta metaNewCase = createNewCase.getItemMeta();
        metaNewCase.addEnchant(Enchantment.LURE, 1, false);
        metaNewCase.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        metaNewCase.displayName(Component.text("Create").color(TextColor.color(255, 122, 213)));
        createNewCase.setItemMeta(metaNewCase);

        ItemStack editCases = new ItemStack(Material.CHEST_MINECART);
        ItemMeta metaEditCases = editCases.getItemMeta();
        metaEditCases.displayName(Component.text("Edit").color(TextColor.color(99, 211, 255)));
        editCases.setItemMeta(metaEditCases);

        ItemStack additionalOptions = new ItemStack(Material.MINECART);
        ItemMeta metaAdditionalOptions = additionalOptions.getItemMeta();
        metaAdditionalOptions.displayName(Component.text("Options").color(TextColor.color(255, 181, 152)));
        additionalOptions.setItemMeta(metaAdditionalOptions);

        inventory.setItem(11, editCases);
        inventory.setItem(13, createNewCase);
        inventory.setItem(15, additionalOptions);

        return inventory;
    }

    public static Inventory storageMenu(int page, Case currentCase) {
        Inventory inventory = Bukkit.createInventory(null, 9 * 6, Component.text("Storage"));

        int itemIndex = 0;
        int pageItemsLeft = currentCase.getItems().size() - (page * 36);
        int pageItemsAmount = pageItemsLeft > 35 ? 36 : pageItemsLeft;

        while (itemIndex < pageItemsAmount) {
            inventory.setItem(itemIndex + 9, currentCase.getItems().get(page * 36 + itemIndex).simpleToStack());
            itemIndex++;
        }

        ItemStack glassFill = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta metaGlassFill = glassFill.getItemMeta();
        metaGlassFill.displayName(Component.text(""));
        glassFill.setItemMeta(metaGlassFill);
        for (int i = 0; i < 9; i++) {
            inventory.setItem(i, glassFill);
            inventory.setItem(i + 45, glassFill);
        }

        ItemStack pageItem = HelperFunctions.createItem(Material.GRAY_STAINED_GLASS_PANE, "Page" + page, TextColor.color(255, 255, 255));
        pageItem.setAmount(page + 1);
        inventory.setItem(4, pageItem);

        ItemStack addItem = HelperFunctions.createItem(Material.NETHER_STAR, "Add Item", TextColor.color(166, 60, 255));
        inventory.setItem(49, addItem);

        ItemStack nextPage;
        if (page < currentCase.getItems().size() - 1) {
            nextPage = HelperFunctions.createItem(Material.LIME_DYE, "Next Page", TextColor.color(22, 211, 0));
        } else {
            nextPage = HelperFunctions.createItem(Material.GRAY_DYE, "Next Page", TextColor.color(112, 112, 112));
        }
        inventory.setItem(53, nextPage);

        ItemStack prevPage;
        if (page > 0) {
            prevPage = HelperFunctions.createItem(Material.LIME_DYE, "Prev Page", TextColor.color(22, 211, 0));
        } else {
            prevPage = HelperFunctions.createItem(Material.GRAY_DYE, "Prev Page", TextColor.color(112, 112, 112));
        }
        inventory.setItem(45, prevPage);

        return inventory;
    }

    public static Inventory addItemMenu() {
        Inventory inventory = Bukkit.createInventory(null, 9, Component.text("Add Item"));
        ItemStack glassFill = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta metaGlassFill = glassFill.getItemMeta();
        metaGlassFill.displayName(Component.text(""));
        glassFill.setItemMeta(metaGlassFill);
        for (int i = 0; i < 9; i++) {
            inventory.setItem(i, glassFill);
        }

        inventory.clear(4);
        return inventory;
    }
}