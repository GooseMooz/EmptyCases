package smp.plugin.GooseMooz.UI;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import smp.plugin.GooseMooz.Models.Case;
import smp.plugin.GooseMooz.SupportFunctions.HelperFunctions;

import java.util.ArrayList;
import java.util.Arrays;

public class OpenCaseMenu {
    public static Inventory initMenu(Case openCase) {
        Inventory menu = Bukkit.createInventory(null, 9*3, Component.text(openCase.getName()));
        ArrayList<Integer> slots = new ArrayList<>(Arrays.asList(9, 10, 11, 12, 13, 14, 15, 16, 17));
        ArrayList<ItemStack> randItems = HelperFunctions.randomItemsArray(openCase, slots.size(), null);
        for (int i = 0; i < slots.size(); i++) {
            menu.setItem(slots.get(i), randItems.get(i));
        }

        //TODO: CHANGE WHOLE STRUCTURE ACCORDING TO OOP

        return menu;
    }


}
