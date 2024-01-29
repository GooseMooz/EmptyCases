package smp.plugin.GooseMooz.Menu;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class CreateCaseMenu {
    public static Inventory initialMenu () {
        Inventory menu = Bukkit.createInventory(null, 9 * 3, Component.text("New Case"));
        
        //Case Icon
        ItemStack caseIcon = new ItemStack(Material.CHEST);
        ItemMeta metaCaseIcon = caseIcon.getItemMeta();
        metaCaseIcon.displayName(Component.text("Case Icon").color(TextColor.color(99, 211, 255)));
        caseIcon.setItemMeta(metaCaseIcon);
        
        //Change Name Function
        ItemStack caseName = new ItemStack(Material.OAK_SIGN);
        ItemMeta metaCaseName = caseName.getItemMeta();
        metaCaseName.displayName(Component.text("Case").color(TextColor.color(255, 255, 255)));
        List<Component> description = new ArrayList<>();
        description.add(Component.text(""));
        description.add(Component.text("Click to change the case name").color(TextColor.color(70, 70, 70)));
        description.add(Component.text(""));
        metaCaseName.lore(description);
        caseName.setItemMeta(metaCaseName);

        //Apply Template
        ItemStack templateIcon = new ItemStack(Material.BOOK);
        ItemMeta metaTemplateIcon = templateIcon.getItemMeta();
        metaTemplateIcon.displayName(Component.text("Apply Template").color(TextColor.color(255, 123, 0)));
        metaTemplateIcon.addEnchant(Enchantment.LURE, 1, false);
        metaTemplateIcon.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        templateIcon.setItemMeta(metaTemplateIcon);

        //Settings Icon
        ItemStack settingsIcon = new ItemStack(Material.COMPARATOR);
        ItemMeta metaSettingsIcon = settingsIcon.getItemMeta();
        metaSettingsIcon.displayName(Component.text("Settings").color(TextColor.color(110, 135, 255)));
        settingsIcon.setItemMeta(metaSettingsIcon);

        //Contains
        ItemStack containsIcon = new ItemStack(Material.BARREL);
        ItemMeta metaContainsIcon = containsIcon.getItemMeta();
        metaContainsIcon.displayName(Component.text("Items").color(TextColor.color(185, 133, 136)));
        containsIcon.setItemMeta(metaContainsIcon);

        //Discard
        ItemStack discardIcon = new ItemStack(Material.BARRIER);
        ItemMeta metaDiscardIcon = discardIcon.getItemMeta();
        metaDiscardIcon.displayName(Component.text("Discard").color(TextColor.color(255, 0, 8)));
        discardIcon.setItemMeta(metaDiscardIcon);

        //Save Template
        ItemStack saveTemplateIcon = new ItemStack(Material.WRITABLE_BOOK);
        ItemMeta metaSaveTemplateIconIcon = saveTemplateIcon.getItemMeta();
        metaSaveTemplateIconIcon.displayName(Component.text("Save Template").color(TextColor.color(255, 240, 0)));
        saveTemplateIcon.setItemMeta(metaSaveTemplateIconIcon);

        //Save Case
        ItemStack saveCaseIcon = new ItemStack(Material.EMERALD);
        ItemMeta metaSaveCaseIcon = saveCaseIcon.getItemMeta();
        metaSaveCaseIcon.displayName(Component.text("Save Case").color(TextColor.color(0, 255, 80)));
        saveCaseIcon.setItemMeta(metaSaveCaseIcon);

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
        Inventory menu = Bukkit.createInventory(null, 9 * 3, Component.text("Choose Case Icon"));

        //Chest Icon
        ItemStack chestIcon = new ItemStack(Material.CHEST);
        ItemMeta metaChestIcon = chestIcon.getItemMeta();
        metaChestIcon.displayName(Component.text("Chest Icon").color(TextColor.color(255, 132, 89)));
        chestIcon.setItemMeta(metaChestIcon);
        menu.setItem(0, chestIcon);

        //Ender Chest Icon
        ItemStack enderChestIcon = new ItemStack(Material.ENDER_CHEST);
        ItemMeta metaEnderChestIcon = enderChestIcon.getItemMeta();
        metaEnderChestIcon.displayName(Component.text("Ender Chest Icon").color(TextColor.color(135, 2, 255)));
        enderChestIcon.setItemMeta(metaEnderChestIcon);
        menu.setItem(1, enderChestIcon);
        return menu;
    }
}
