package smp.plugin.GooseMooz.SimpleItem;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SimpleItem {
    Material type = Material.AIR;
    int amount = 0;
    ItemMeta meta;
    Double chance = 0.0;

    public ItemStack simpleToStack() {
        ItemStack stack = new ItemStack(type, amount);
        stack.setItemMeta(meta);
        return stack;
    }

    public void stackToSimple(ItemStack itemStack) {
        this.type = itemStack.getType();
        this.amount = itemStack.getAmount();
        this.meta = itemStack.getItemMeta();
    }

    public void setType(Material type) {
        this.type = type;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setMeta(ItemMeta meta) {
        this.meta = meta;
    }

    public void setChance(Double chance) {
        this.chance = chance;
    }

    public Double getChance() {
        return this.chance;
    }
}
