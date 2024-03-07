package smp.plugin.GooseMooz.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import smp.plugin.GooseMooz.Models.Case;
import smp.plugin.GooseMooz.SupportFunctions.HelperFunctions;

import java.util.ArrayList;
import java.util.List;

public class CompleterCommand implements TabCompleter {
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        List<String> completions = new ArrayList<>();

        if (strings.length == 1) {
            completions.add("open");

            return completions;
        } else if (strings.length == 2) {
            ArrayList<Case> cases = HelperFunctions.getCases();
            for (int i = 0; i < cases.size(); i++) {
                completions.add(Integer.toString(i + 1));
            }

            return completions;
        }

        return null;
    }
}
