package main.handlers.EventHandler.Events;

import arc.util.Log;
import main.PluginVars;
import main.handlers.MenuHandler.MenuHandler;
import mindustry.game.EventType;
import mindustry.gen.*;


public class PlayerEvents {
    public static void init() {
        Log.info(String.format("[%s]: EventHandler.Events.PlayerEvents initialized.", PluginVars.pluginName));
    }
    public static void JoinEvent(EventType.PlayerJoin event) {
        Player player = event.player;
        String uuid = player.uuid();
        PluginVars.getOrCreateData(uuid);
        MenuHandler.welcomeMenu(player.con);
    }
}