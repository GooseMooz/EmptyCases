package smp.plugin.GooseMooz.Case;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.bukkit.inventory.ItemStack;
import smp.plugin.GooseMooz.SimpleItem.SimpleItem;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Case {
    String name = "New Case";
    int icon = 0;
    String settings = "000";
    ArrayList<SimpleItem> items = new ArrayList<>();

    public Case(String newName, int newIcon, String newSettings, ArrayList<SimpleItem> newItems) {
        setName(newName);
        setIcon(newIcon);
        setSettings(newSettings);
        setItems(newItems);
    }

    public Case(String newName, int newIcon, String newSettings) {
        setName(newName);
        setIcon(newIcon);
        setSettings(newSettings);
    }

    public Case(String newName, int newIcon) {
        setName(newName);
        setIcon(newIcon);
    }

    public Case(String newName) {
        setName(newName);
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int newIcon) {
        this.icon = newIcon;
    }

    public String getSettings() {
        return settings;
    }

    public void setSettings(String newSettings) {
        this.settings = newSettings;
    }

    public ArrayList<SimpleItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<SimpleItem> newItems) {
        this.items = newItems;
    }

    public void addItem(ItemStack item) {
        SimpleItem conversion = new SimpleItem();
        conversion.stackToSimple(item);
        this.items.add(conversion);
    }

    public void setChance(int itemIndex, Double chance) {
        this.items.get(itemIndex).setChance(chance);
    }

    public double chanceRange() {
        double sum = 0;
        for (SimpleItem item : this.items) {
            sum += item.getChance();
        }

        return sum;
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveCase(int option) {
        Gson gson = new Gson();
        try {
            FileReader fileReader;
            String path;
            if (option == 1) {
                fileReader = new FileReader("emptycases/cases.json");
                path = "emptycases/cases.json";
                onClose();
            } else {
                fileReader = new FileReader("emptycases/templates.json");
                path = "emptycases/templates.json";
            }
            Type caseArrayType = new TypeToken<ArrayList<Case>>() {}.getType();
            ArrayList<Case> cases = gson.fromJson(fileReader, caseArrayType);
            if (cases == null) {
                cases = new ArrayList<>();
            }
            cases.add(this);
            String save = gson.toJson(cases);
            try {

                Files.write(Paths.get(path), save.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}