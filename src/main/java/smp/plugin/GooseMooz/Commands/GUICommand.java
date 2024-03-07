package smp.plugin.GooseMooz.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.jetbrains.annotations.NotNull;
import smp.plugin.GooseMooz.Animations.MenuGUI;
import smp.plugin.GooseMooz.Animations.OpenCaseGUI;
import smp.plugin.GooseMooz.EmptyCases;
import smp.plugin.GooseMooz.Models.Case;
import smp.plugin.GooseMooz.SupportFunctions.HelperFunctions;
import smp.plugin.GooseMooz.UI.CreateCaseMenu;
import smp.plugin.GooseMooz.UI.OpenCaseMenu;

import java.util.ArrayList;

public class GUICommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only players can use this command!");

            return true;
        }

        if (args.length == 0) {
            Inventory inventory = CreateCaseMenu.mainMenu();

            player.openInventory(inventory);
            player.setMetadata("OpenedGUI", new FixedMetadataValue(EmptyCases.getInstance(), "Preferences Menu"));

            MenuGUI menuGUI = new MenuGUI(inventory);
            Bukkit.getScheduler().runTaskTimer(EmptyCases.getInstance(), menuGUI.mainMenu(), 0L, 20L);

        } else {
            Case opening = HelperFunctions.getCases().get(Integer.parseInt(args[1]) - 1);
            Inventory inventory = OpenCaseMenu.initMenu(opening);

            player.openInventory(inventory);
            player.setMetadata("OpenCase", new FixedMetadataValue(EmptyCases.getInstance(), "Opening Case"));
            ArrayList<ItemStack> openingFrame = HelperFunctions.openingItemsArray(inventory);

            OpenCaseGUI openCaseGUI = new OpenCaseGUI(opening, openingFrame.size(), openingFrame, inventory);
            Bukkit.getScheduler().runTaskTimer(EmptyCases.getInstance(), openCaseGUI.openAnimation(), 0L, 20L);

        }
        return true;
    }
}
