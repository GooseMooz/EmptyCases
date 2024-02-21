package smp.plugin.GooseMooz.SupportFunctions;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import smp.plugin.GooseMooz.Case.Case;
import smp.plugin.GooseMooz.EmptyCases;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
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

    public static void removeSetMetadata(String remove, String metaName, String metaDesc, Player player) {
        player.setMetadata(metaName, new FixedMetadataValue(EmptyCases.getInstance(), metaDesc));
        player.removeMetadata(remove, EmptyCases.getInstance());
    }

    public static ArrayList<Case> getCases() {
        Gson gson = new Gson();
        ArrayList<Case> cases;
        try {
            FileReader fileReader;
            fileReader = new FileReader("emptycases/cases.json");
            Type caseArrayType = new TypeToken<ArrayList<Case>>() {}.getType();
            cases = gson.fromJson(fileReader, caseArrayType);
            if (cases == null) {
                cases = new ArrayList<>();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return cases;
    }
    
    public static ItemStack createItem(Material material, String name) {
        ItemStack newItem  = new ItemStack(material);
        ItemMeta metaNewItem = newItem.getItemMeta();
        metaNewItem.displayName(Component.text(name));
        newItem.setItemMeta(metaNewItem);

        return newItem;
    }
    
    public static ItemStack createItem(Material material, String name, TextColor color) {
        ItemStack newItem  = new ItemStack(material);
        ItemMeta metaNewItem = newItem.getItemMeta();
        metaNewItem.displayName(Component.text(name).color(color));
        newItem.setItemMeta(metaNewItem);

        return newItem;
    }
}
