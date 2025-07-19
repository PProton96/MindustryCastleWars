package main.handlers.ClientCommandsHandler;

import arc.util.CommandHandler;
import arc.util.Log;
import main.PluginVars;
import mindustry.gen.Player;

import static mindustry.Vars.mods;

public class ClientCommandsHandler {
    public void registerClientCommands (CommandHandler handler) {
        handler.<Player>register("cheat", "Cheats activation command", (args, player) -> {
            Log.info(String.format("[%s]: Player [%s] activated cheats", PluginVars.pluginName, player.name));
            mods.getScripts().runConsole("/fillitems sharded");
            //TODO
        });
    }
}
