package smp.plugin.GooseMooz.SupportFunctions;

import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.block.sign.Side;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import smp.plugin.GooseMooz.EmptyCases;

public class PlayerNameInput {
    public static void createNameInput (Player player) {
        Material oakSign = Material.OAK_SIGN;
        Location playerLocation = player.getLocation();
        Block block = player.getWorld().getBlockAt(playerLocation.getBlockX(), playerLocation.getBlockY() - 4, playerLocation.getBlockZ());
        block.setMetadata("PrevBlock", new FixedMetadataValue(EmptyCases.getInstance(), block.getType().name()));
        block.setType(oakSign);
        //BlockData data = block.createBlockData();
        //TileState tileState = (TileState) data.createBlockState();
        //player.sendBlockChange(new Location(player.getWorld(), 39, 63, 10), data);
        //player.sendBlockUpdate(new Location(player.getWorld(), 39, 63, 10), tileState);
        //tileState = (TileState) player.getWorld().getBlockAt(new Location(player.getWorld(), 39, 63, 10)).getBlockData().createBlockState();
        //Sign sign = (Sign) tileState;
        //player.openSign(sign, Side.FRONT);
        //TODO: MAKE THE SOLUTION NOT LAME
        Sign sign = (Sign) block.getState();
        sign.getSide(Side.FRONT).line(2, Component.text("Write name above"));
        sign.getSide(Side.FRONT).line(1, Component.text("======="));
        sign.getSide(Side.FRONT).line(3, Component.text("======="));
        sign.update();

        new BukkitRunnable() {
            @Override
            public void run() {
                player.openSign(sign, Side.FRONT);
            }
        }.runTaskLater(EmptyCases.getInstance(), 3);
        player.setMetadata("EditName", new FixedMetadataValue(EmptyCases.getInstance(), "Editing Case Name"));
    }
}
