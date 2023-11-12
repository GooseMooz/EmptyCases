package smp.plugin.GooseMooz.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.jetbrains.annotations.NotNull;
import smp.plugin.GooseMooz.EmptyCases;

public class GUICommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only players can use this command!");

            return true;
        }

        Inventory inventory = Bukkit.createInventory(player, 9 * 3, Component.text("Preferences Menu").color(TextColor.color(25, 27, 123)));

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

        player.openInventory(inventory);
        player.setMetadata("OpenedGUI", new FixedMetadataValue(EmptyCases.getInstance(), "Preferences Menu"));

        Bukkit.getScheduler().runTaskTimer(EmptyCases.getInstance(), new Runnable() {
            boolean flag = true;
            @Override
            public void run() {
                for (int i = 0; i < 10; i += 2) {
                    inventory.setItem(i, flag ? new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE) : new ItemStack(Material.PURPLE_STAINED_GLASS_PANE));
                    inventory.setItem(i + 1, !flag ? new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE) : new ItemStack(Material.PURPLE_STAINED_GLASS_PANE));
                    inventory.setItem(i + 17, !flag ? new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE) : new ItemStack(Material.PURPLE_STAINED_GLASS_PANE));
                    inventory.setItem(i + 18, flag ? new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE) : new ItemStack(Material.PURPLE_STAINED_GLASS_PANE));
                }
                flag = !flag;
            }
        }, 0L, 20L);

        return true;
    }
}
