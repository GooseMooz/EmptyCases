package smp.plugin.GooseMooz.SupportFunctions;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.TileState;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.Sign;
import org.bukkit.block.sign.Side;
import org.bukkit.entity.Player;

public class PlayerNameInput {
    public static void createNameInput (Player player) {
        Material oakSign = Material.OAK_SIGN;
        Block block = player.getWorld().getBlockAt(player.getLocation());
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
        player.openSign(sign, Side.FRONT);
    }
}
