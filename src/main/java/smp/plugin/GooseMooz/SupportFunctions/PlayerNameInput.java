package smp.plugin.GooseMooz.SupportFunctions;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import smp.plugin.GooseMooz.EmptyCases;

public class PlayerNameInput {
    public static void createNameInput (Player player) {
        //TODO: Use BOOK HERE
        // Create a written book
        ItemStack book = new ItemStack(Material.WRITABLE_BOOK);

        // Get the BookMeta
        BookMeta meta = (BookMeta) book.getItemMeta();

        // Add some pages
        meta.addPage("Page 1 text", "Page 2 text", "Page 3 text");

        // Apply the BookMeta to the book
        book.setItemMeta(meta);

        // Give the book to the player
        player.getInventory().addItem(book);

        player.openBook(book);
        System.out.println(book.getItemMeta());
    }
}
