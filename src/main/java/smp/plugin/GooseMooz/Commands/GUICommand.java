package smp.plugin.GooseMooz.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.metadata.FixedMetadataValue;
import org.jetbrains.annotations.NotNull;
import smp.plugin.GooseMooz.Animations.AnimationsGUI;
import smp.plugin.GooseMooz.EmptyCases;
import smp.plugin.GooseMooz.UI.CreateCaseMenu;

public class GUICommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only players can use this command!");

            return true;
        }

        Inventory inventory = CreateCaseMenu.mainMenu();

        player.openInventory(inventory);
        player.setMetadata("OpenedGUI", new FixedMetadataValue(EmptyCases.getInstance(), "Preferences Menu"));

        AnimationsGUI animationsGUI = new AnimationsGUI(inventory);
        Bukkit.getScheduler().runTaskTimer(EmptyCases.getInstance(), animationsGUI.mainMenu(), 0L, 20L);

        return true;
    }
}
