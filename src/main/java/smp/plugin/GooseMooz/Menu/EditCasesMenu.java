package smp.plugin.GooseMooz.Menu;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import smp.plugin.GooseMooz.Case.Case;
import smp.plugin.GooseMooz.SupportFunctions.HelperFunctions;

import java.util.ArrayList;

public class EditCasesMenu {
    public static Inventory initMenu() {
        ArrayList<Case> cases = HelperFunctions.getCases();
        Inventory inventory = Bukkit.createInventory(null, 9*3, Component.text("Your Cases"));
        for (int i = 0; i < cases.size(); i++) {
            inventory.setItem(i, HelperFunctions.intToIcon(cases.get(i).getIcon()));
        }
        return inventory;
    }
}