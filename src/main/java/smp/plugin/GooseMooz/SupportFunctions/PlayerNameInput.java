package smp.plugin.GooseMooz.SupportFunctions;

import org.bukkit.entity.Player;
import org.bukkit.craftbukkit.v1_20_R2.entity.CraftPlayer;
import net.minecraft.server.level.ServerPlayer;

public class PlayerNameInput {
    public static void createNameInput (Player player) {
        //TODO: Use NMS... HERE
        CraftPlayer craftPlayer = (CraftPlayer) player;
        ServerPlayer serverPlayer = craftPlayer.getHandle();

    }
}
