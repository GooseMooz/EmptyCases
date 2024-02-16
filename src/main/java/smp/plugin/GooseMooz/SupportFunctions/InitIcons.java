package smp.plugin.GooseMooz.SupportFunctions;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class InitIcons {

    public static ItemStack initCaseIcon() {
        ItemStack caseIcon = new ItemStack(Material.CHEST);
        ItemMeta metaCaseIcon = caseIcon.getItemMeta();
        metaCaseIcon.displayName(Component.text("Case Icon").color(TextColor.color(99, 211, 255)));
        caseIcon.setItemMeta(metaCaseIcon);
        return caseIcon;
    }

    public static ItemStack initNameIcon(String name) {
        ItemStack caseName = new ItemStack(Material.OAK_SIGN);
        ItemMeta metaCaseName = caseName.getItemMeta();
        metaCaseName.displayName(Component.text(name).color(TextColor.color(255, 255, 255)));
        List<Component> description = new ArrayList<>();
        description.add(Component.text(""));
        description.add(Component.text("Click to change the case name").color(TextColor.color(70, 70, 70)));
        description.add(Component.text(""));
        metaCaseName.lore(description);
        caseName.setItemMeta(metaCaseName);
        return caseName;
    }

    public static ItemStack initTemplateIcon() {
        ItemStack templateIcon = new ItemStack(Material.BOOK);
        ItemMeta metaTemplateIcon = templateIcon.getItemMeta();
        metaTemplateIcon.displayName(Component.text("Apply Template").color(TextColor.color(255, 123, 0)));
        metaTemplateIcon.addEnchant(Enchantment.LURE, 1, false);
        metaTemplateIcon.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        templateIcon.setItemMeta(metaTemplateIcon);
        return templateIcon;
    }

    public static ItemStack initSettingsIcon() {
        ItemStack settingsIcon = new ItemStack(Material.COMPARATOR);
        ItemMeta metaSettingsIcon = settingsIcon.getItemMeta();
        metaSettingsIcon.displayName(Component.text("Settings").color(TextColor.color(110, 135, 255)));
        settingsIcon.setItemMeta(metaSettingsIcon);
        return settingsIcon;
    }

    public static ItemStack initContainsIcon() {
        ItemStack containsIcon = new ItemStack(Material.BARREL);
        ItemMeta metaContainsIcon = containsIcon.getItemMeta();
        metaContainsIcon.displayName(Component.text("Items").color(TextColor.color(185, 133, 136)));
        containsIcon.setItemMeta(metaContainsIcon);
        return containsIcon;
    }

    public static ItemStack initDiscardIcon() {
        ItemStack discardIcon = new ItemStack(Material.BARRIER);
        ItemMeta metaDiscardIcon = discardIcon.getItemMeta();
        metaDiscardIcon.displayName(Component.text("Discard").color(TextColor.color(255, 0, 8)));
        discardIcon.setItemMeta(metaDiscardIcon);
        return discardIcon;
    }

    public static ItemStack initSaveTemplateIcon() {
        ItemStack saveTemplateIcon = new ItemStack(Material.WRITABLE_BOOK);
        ItemMeta metaSaveTemplateIconIcon = saveTemplateIcon.getItemMeta();
        metaSaveTemplateIconIcon.displayName(Component.text("Save Template").color(TextColor.color(255, 240, 0)));
        saveTemplateIcon.setItemMeta(metaSaveTemplateIconIcon);
        return saveTemplateIcon;
    }

    public static ItemStack initSaveCaseIcon() {
        ItemStack saveCaseIcon = new ItemStack(Material.EMERALD);
        ItemMeta metaSaveCaseIcon = saveCaseIcon.getItemMeta();
        metaSaveCaseIcon.displayName(Component.text("Save Case").color(TextColor.color(0, 255, 80)));
        saveCaseIcon.setItemMeta(metaSaveCaseIcon);
        return saveCaseIcon;
    }

    public static ItemStack enderChestIcon() {
        ItemStack enderChestIcon = new ItemStack(Material.ENDER_CHEST);
        ItemMeta metaEnderChestIcon = enderChestIcon.getItemMeta();
        metaEnderChestIcon.displayName(Component.text("Ender Icon").color(TextColor.color(132, 0, 255)));
        enderChestIcon.setItemMeta(metaEnderChestIcon);
        return enderChestIcon;
    }

    public static ItemStack bundleIcon() {
        ItemStack bundleIcon = new ItemStack(Material.BUNDLE);
        ItemMeta metaBundleIcon = bundleIcon.getItemMeta();
        metaBundleIcon.displayName(Component.text("Bundle Icon").color(TextColor.color(255, 164, 89)));
        bundleIcon.setItemMeta(metaBundleIcon);
        return bundleIcon;
    }
}
