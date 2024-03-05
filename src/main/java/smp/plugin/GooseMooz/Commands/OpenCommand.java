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
import smp.plugin.GooseMooz.Models.Case;
import smp.plugin.GooseMooz.SupportFunctions.HelperFunctions;
import smp.plugin.GooseMooz.UI.OpenCaseMenu;

public class OpenCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!(commandSender instanceof Player player)) {
            commandSender.sendMessage("Only players can use this command!");

            return true;
        }

        Case opening = HelperFunctions.getCases().get(Integer.parseInt(args[0]));
        Inventory inventory = OpenCaseMenu.initMenu(opening);

        player.openInventory(inventory);
        player.setMetadata("OpenCase", new FixedMetadataValue(EmptyCases.getInstance(), "Opening Case"));

        AnimationsGUI animationsGUI = new AnimationsGUI(inventory);
        Bukkit.getScheduler().runTaskTimer(EmptyCases.getInstance(), animationsGUI.openAnimation(), 0L, 20L);

        return true;
    }
}
