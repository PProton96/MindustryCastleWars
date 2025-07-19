package main.handlers.EventHandler.Events;

import arc.util.Timer;
import main.PluginVars;
import mindustry.game.EventType;
import mindustry.gen.*;


import static main.PluginVars.*;


public class PlayerEvents {
    public static void JoinEvent(EventType.PlayerJoin event) {
        Player player = event.player;
        String uuid = player.uuid();
        PluginVars.getOrCreateData(uuid);
    }
}