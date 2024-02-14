package smp.plugin.GooseMooz.Menu;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import smp.plugin.GooseMooz.Case.Case;
import smp.plugin.GooseMooz.SupportFunctions.InitIcons;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class CreateCaseMenu {
    static ArrayList<ItemStack> icons = new ArrayList<>(Arrays.asList(InitIcons.initCaseIcon(), InitIcons.initContainsIcon()));

    public static Case caseFromInventory(Inventory inventory) {
        assert inventory.getItem(1) != null;
        String name = PlainTextComponentSerializer.plainText().serialize(Objects.requireNonNull(Objects.requireNonNull(inventory.getItem(1)).getItemMeta().displayName()));
        return new Case(name);
    }
    public static Inventory inventoryFromCase(Case template) {
        Inventory newInv = Bukkit.createInventory(null, 9*3, Component.text(template.getName()));
        newInv.setItem(0, icons.get(template.getIcon()));

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
