package smp.plugin.GooseMooz.SupportFunctions;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.TileState;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;

public class PlayerNameInput {
    public static void createNameInput (Player player) {
        //TODO: USE void sendBlockUpdate();
        Material block = Material.OAK_SIGN;
        Block bl = player.getWorld().getBlockAt(0, 0, 0);
        BlockData data = Bukkit.createBlockData(block);
        TileState tileState = (TileState) bl.getState();
        player.sendBlockChange(new Location(player.getWorld(), 0, 0, 0), data);
        player.sendBlockUpdate(new Location(player.getWorld(), 0, 0, 0), tileState);
    }
}
