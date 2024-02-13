package smp.plugin.GooseMooz.Case;
//TODO: JSON:
// Has name, all properties of icons, animations, etc., has inventory of items to dispose.
// Create a function that creates an inventory from JSON file of a case
// Structure:
// {
//      Name: *name*
//      Icon: *block + meta*
//      Settings: *BG + ANIMATION + WHEEL*
//      Items: [*items*]
//  }

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.kyori.adventure.text.Component;
import org.bukkit.inventory.ItemStack;
import smp.plugin.GooseMooz.SupportFunctions.InitIcons;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class Case {
    Component name = Component.text("New Case");
    ItemStack icon = InitIcons.initCaseIcon();
    String settings = "000";
    ArrayList<ItemStack> items = new ArrayList<>();

    public Case(Component newName, ItemStack newIcon, String newSettings, ArrayList<ItemStack> newItems) {
        setName(newName);
        setIcon(newIcon);
        setSettings(newSettings);
        setItems(newItems);
    }

    public Case(Component newName, ItemStack newIcon, String newSettings) {
        setName(newName);
        setIcon(newIcon);
        setSettings(newSettings);
    }

    public Case(Component newName, ItemStack newIcon) {
        setName(newName);
        setIcon(newIcon);
    }

    public Case(Component newName) {
        setName(newName);
    }

    public Component getName() {
        return name;
    }

    public void setName(Component newName) {
        this.name = newName;
    }

    public ItemStack getIcon() {
        return icon;
    }

    public void setIcon(ItemStack newIcon) {
        this.icon = newIcon;
    }

    public String getSettings() {
        return settings;
    }

    public void setSettings(String newSettings) {
        this.settings = newSettings;
    }

    public ArrayList<ItemStack> getItems() {
        return items;
    }

    public void setItems(ArrayList<ItemStack> newItems) {
        this.items = newItems;
    }

    public void makeCurrent() {
        Gson gson = new Gson();
        String json = gson.toJson(this);
        String path = "emptycases/current.json";
        try {
            Files.write(Paths.get(path), json.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onClose() {
        String blank = "";
        String path = "emptycases/current.json";
        try {
            Files.write(Paths.get(path), blank.getBytes());
            System.out.println("CHE GDE YA");
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveCase() {
        onClose();
        Gson gson = new Gson();
        try {
            FileReader fileReader = new FileReader("emptycases/cases.json");
            ArrayList<Case> cases = gson.fromJson(fileReader, new TypeToken<ArrayList<Case>>(){}.getType());
            if (cases == null) {
                cases = new ArrayList<>();
            }
            cases.add(this);
            String save = gson.toJson(cases);
            String path = "emptycases/cases.json";
            try { // TODO: Test, maybe should change to parsing to list, append, rewrite
                Files.write(Paths.get(path), save.getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}