package main.handlers.EventHandler.Events;

import arc.util.Log;
import main.PluginVars;

public class PluginEvents {
    public static void init() {
        Log.info(String.format("[%s]: main.handlers.EventHandler.Events.PluginEvents initialized.", PluginVars.pluginName));
    }
}
