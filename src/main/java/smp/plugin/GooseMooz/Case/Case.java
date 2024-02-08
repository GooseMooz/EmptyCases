package smp.plugin.GooseMooz.Case;
//TODO: JSON:
// Has name, all properties of icons, animations, etc., has inventory of items to dispose.
// Create a function that creates an inventory from JSON file of a case
// Structure:
// {
//      Name: *name*
//      Icon: *block + meta*
//      Settings: *BG + ANIMATION + WHEEL*
//      Items: [*items*]
//  }

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class Case {
    String name;
    int icon;
    String settings;
    ArrayList<ItemStack> items;
}
