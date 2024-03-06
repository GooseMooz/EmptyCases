package smp.plugin.GooseMooz.Animations;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import smp.plugin.GooseMooz.Models.Case;
import smp.plugin.GooseMooz.SupportFunctions.HelperFunctions;

import java.util.ArrayList;

public class OpenCaseGUI {
    Case openingCase;
    int size;
    ArrayList<ItemStack> prevFrame;
    Inventory inventory;

    public OpenCaseGUI(Case openingCase, int size, ArrayList<ItemStack> prevFrame, Inventory inventory) {
        this.openingCase = openingCase;
        this.size = size;
        this.prevFrame = prevFrame;
        this.inventory = inventory;
    }

    public Runnable openAnimation() {
        return () -> {
            ArrayList<ItemStack> frame = HelperFunctions.randomItemsArray(openingCase, size, prevFrame);

        };
    }
}
