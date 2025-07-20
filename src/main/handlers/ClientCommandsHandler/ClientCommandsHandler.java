package main.handlers.ClientCommandsHandler;

import arc.util.CommandHandler;
import arc.util.Log;
import main.PluginVars;
import mindustry.gen.Player;

import static main.PluginVars.allItems;
import static main.PluginVars.pluginName;

public class ClientCommandsHandler {
    public static void init() {
        Log.info(String.format("[%s]: ClientCommandsHandler initialized.", pluginName));
    }
    public static void register(CommandHandler handler) {
        handler.<Player>register("cheat", "Cheats activation command", (args, player) -> {
            Log.info(String.format("[%s]: Player [%s] activated cheats", PluginVars.pluginName, player.plainName()));
            allItems.forEach(item -> {
                player.team().core().items().add(item, player.team().core().getMaximumAccepted(item));
            });
        });
    }
}
