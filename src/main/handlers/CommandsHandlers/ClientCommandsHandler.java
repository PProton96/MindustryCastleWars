package main.handlers.CommandsHandlers;

import arc.util.CommandHandler;
import arc.util.Log;
import main.PluginVars;
import mindustry.gen.Player;

import static main.PluginVars.*;

public class ClientCommandsHandler {
    public static void init() {
        Log.info(String.format("[%s]: main.handlers.CommandsHandlers.ClientCommandsHandler initialized.", pluginName));
    }
    public static void accessDenied(Player player) {
        player.sendMessage(String.format("[purple][[cyan]%s[purple]][white]: иди нахуй", pluginName));
    }
    public static void register(CommandHandler handler) {
        handler.<Player>register("cheat", "Cheats activation command", (args, player) -> {
            if (player.admin) {
                Log.info(String.format("[%s]: Player [%s] activated cheats", PluginVars.pluginName, player.plainName()));
                playersData.put(player.uuid(), new PlayerData(10000000, 100000));
                allItems.forEach(item -> {
                    player.team().core().items().add(item, player.team().core().getMaximumAccepted(item));
                });
            }
            else {accessDenied(player);}
        });
        handler.<Player>register("spawnUnit", "<unit>", "Spawns selected unit at enemy's base.", (args, player) -> {
          if (player.admin) {

          }
          else {accessDenied(player);}
        });
    }
}
