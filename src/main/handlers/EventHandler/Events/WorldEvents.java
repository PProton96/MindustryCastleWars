package main.handlers.EventHandler.Events;

import arc.util.Log;
import main.PluginVars;
import mindustry.game.EventType;

public class WorldEvents {
    public static void init() {
        Log.info(String.format("[%s]: EventHandler.Events.WorldEvents initialized.", PluginVars.pluginName));
    }
    public static void WorldLoadEndEvent(EventType.WorldLoadEndEvent event) {
        Log.info(String.format("[%s]: WorldLoadEndEvent activation.", PluginVars.pluginName));
    }
}
