package smp.plugin.GooseMooz.SupportFunctions;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.block.sign.Side;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import smp.plugin.GooseMooz.EmptyCases;

public class PlayerSignInput {
    public static void createSignInput (Player player) {
        Location location = new Location(player.getWorld(), 1000000, 1000000, 200);
        Sign sign = (Sign) new //SIGN GUI MAYBE;

        player.closeInventory();
        player.openSign(sign, Side.FRONT);
        player.sendMessage("jopa");
        player.setMetadata("Input", new FixedMetadataValue(EmptyCases.getInstance(), "Inputting")); //TODO: MAKE IT WORK
    }
}
