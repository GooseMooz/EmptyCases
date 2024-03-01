package smp.plugin.GooseMooz.Animations;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class AnimationsGUI{
    boolean flag = true;
    private final Inventory inventory;

    public AnimationsGUI(Inventory inventory) {
        this.inventory = inventory;
    }

    public Runnable mainMenu () {
        return () -> {
            for (int i = 0; i < 10; i += 2) {
                inventory.setItem(i, flag ? new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE) : new ItemStack(Material.PURPLE_STAINED_GLASS_PANE));
                inventory.setItem(i + 1, !flag ? new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE) : new ItemStack(Material.PURPLE_STAINED_GLASS_PANE));
                inventory.setItem(i + 17, !flag ? new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE) : new ItemStack(Material.PURPLE_STAINED_GLASS_PANE));
                inventory.setItem(i + 18, flag ? new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE) : new ItemStack(Material.PURPLE_STAINED_GLASS_PANE));
            }
            flag = !flag;
        };
    }

    public Runnable openAnimation() {
        return () -> {

        };
    }
}
