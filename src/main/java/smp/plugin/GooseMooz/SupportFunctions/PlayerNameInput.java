package smp.plugin.GooseMooz.SupportFunctions;

import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientboundOpenSignEditorPacket;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.craftbukkit.v1_20_R2.entity.CraftPlayer;
import net.minecraft.server.level.ServerPlayer;

public class PlayerNameInput {
    public static void createNameInput (Player player) {
        //TODO: USE void sendBlockUpdate();
        player.sendBlockUpdate(new Location(player.getWorld(), 0, 0, 0), player.sendBlockChange(new Location(player.getWorld(), 0, 0, 0), ));
    }
}
