package main.handlers.CommandsHandlers;

import arc.util.CommandHandler;
import arc.util.Log;
import main.PluginVars;
import mindustry.gen.Groups;

public class ServerCommandsHandler {
    public static void init() {
        Log.info(String.format("[%s]: main.handlers.CommandsHandlers.ServerCommandsHandler initialized.", PluginVars.pluginName));
    }
    public static void register(CommandHandler handler) {
        handler.register("kickall", "<reason>", "Kicks everyone from server", args -> {
            Groups.player.forEach(player -> {
                player.kick(args[0]);
            });
        });
    }
}
