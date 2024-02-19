package smp.plugin.GooseMooz.SupportFunctions;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;

public class HelperFunctions {
    static ArrayList<ItemStack> icons = new ArrayList<>(Arrays.asList(
            InitIcons.initCaseIcon(), //Chest Icon
            InitIcons.enderChestIcon(), //Ender Icon
            InitIcons.bundleIcon() //Bundle Icon
    ));
    public static String componentToString (Component component) {
        PlainTextComponentSerializer plainTextComponentSerializer = PlainTextComponentSerializer.plainText();

        return plainTextComponentSerializer.serialize(component);
    }

    public static int iconToInt (ItemStack icon) {
        return icons.indexOf(icon);
    }

    public static ItemStack intToIcon (int icon) {
        return icons.get(icon);
    }
}
